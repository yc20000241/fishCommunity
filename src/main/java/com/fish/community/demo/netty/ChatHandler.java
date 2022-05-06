package com.fish.community.demo.netty;

import com.fish.community.demo.SpringUtil;
import com.fish.community.demo.aspect.LogAspect;
import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.mapper.*;
import com.fish.community.demo.model.*;
import com.fish.community.demo.resp.ChatResp;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.util.CopyUtil;
import com.fish.community.demo.util.JsonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 用于处理消息的handler
 * 由于它的传输数据的载体是frame，这个frame 在netty中，是用于为websocket专门处理文本对象的，frame是消息的载体，此类叫：TextWebSocketFrame
 */
@DependsOn("springUtil")
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //用于记录和管理所有客户端的channel
    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg){
        //获取客户端所传输的消息
        String content = msg.text();
        LOG.info("客户端所传输的消息"+content);
        //1.获取客户端发来的消息
        DataContent dataContent = null;
        try{
			dataContent = JsonUtils.jsonToPojo(content, DataContent.class);
		}catch (Exception e){
        	throw new BusinessException(BusinessExceptionCode.CHAT_MESSAGE_FORMAT_WRONG);
		}
		Integer action = dataContent.getAction();
        Channel channel =  ctx.channel();

       if(action == 1){//第一次创建群聊
			Long uuid = null;
        	if(dataContent.getUuid() != (long)-1){
				//查看之前是否创建过，如有则用之前的uuid，没有再创建并插入数据库
				Long createrId = dataContent.getCreaterId();
				ChatUserExample chatUserExample = new ChatUserExample();
				chatUserExample.createCriteria().andCreaterIdEqualTo(createrId);
				ChatUserMapper chatUserMapper = SpringUtil.getBean(ChatUserMapper.class);
				List<ChatUser> chatUsers = chatUserMapper.selectByExample(chatUserExample);
				if(chatUsers.isEmpty()){//以前没创建过会议，无uuid
					uuid = System.currentTimeMillis();
					//记录此次uuid插入数据库
					ChatUser chatUser = new ChatUser();
					chatUser.setCreaterId(createrId);
					chatUser.setUuid(uuid);
					chatUserMapper.insertSelective(chatUser);
				}else{
					uuid = chatUsers.get(0).getUuid();
				}
			}else{
        		uuid = dataContent.getUuid();
			}
		   UserChanelRel userChanelRel1 = RelUUIDRel.get(uuid);
		   Long senderId = dataContent.getChat().getSenderId();
        	if(userChanelRel1 == null){//无此组则创建
				// 当websocket 第一次open的时候，初始化channel，把用的channel 和 userid 关联起来，
				userChanelRel1 = new UserChanelRel();
				userChanelRel1.put(senderId,channel);
				// 并生成单独聊天号，将userChanelRel与会议号关联起来
				RelUUIDRel.put(uuid, userChanelRel1);
			}else{//有组则加入该组
				userChanelRel1.put(senderId,channel);
			}

            ChatMsg chatMsg = CopyUtil.copy(dataContent.getChat(), ChatMsg.class);
            chatMsg.setUuid(uuid);
            //返回之前的50条记录
			ChatMsgExtMapper chatMsgExtMapper = SpringUtil.getBean(ChatMsgExtMapper.class);
			List<ChatMsg> chatList = chatMsgExtMapper.selectCommonChats(uuid);
			Collections.reverse(chatList);
			//获取chatList里所有的senderId
		   List<Long> ids = chatList.stream().map(u-> u.getSenderId()).collect(Collectors.toList());
		   UserinfoExtMapper userinfoExtMapper = SpringUtil.getBean(UserinfoExtMapper.class);
		   List<Userinfo> userinfoList = userinfoExtMapper.selectIdIn((ArrayList<Long>) ids);
		   //封装为ChatListResp
		   HashMap<Long, Userinfo> longUserinfoMap = new HashMap<>();
		   for (Userinfo userinfo : userinfoList) {//将usrid与userinfo关联
			   longUserinfoMap.put(userinfo.getUserId(), userinfo);
		   }
		   List<ChatResp> chatResps = CopyUtil.copyList(chatList, ChatResp.class);
		   for (ChatResp chatResp : chatResps) {//为每个聊天添加用户信息
			   Userinfo userinfo = longUserinfoMap.get(chatResp.getSenderId());
			   chatResp.setImg_url(userinfo.getImgUrl());
			   chatResp.setNick(userinfo.getNick());
		   }
		   CommonResp<List<ChatResp>> listCommonResp = new CommonResp<>();
		   listCommonResp.setContent(chatResps);
		   channel.writeAndFlush(new TextWebSocketFrame(
                    JsonUtils.objectToJson(listCommonResp)
            ));

		   ChanelUser chanelUser = new ChanelUser();
		   chanelUser.setSenderId(dataContent.getChat().getSenderId());
		   chanelUser.setUuid(uuid);
		   ChanelUUIDRel.put(ctx.channel(), chanelUser);
		   sendJoinOrExit(1, "加入聊天成功", chanelUser);
        } else if(action == 2){//第一次加入群聊
            //根据uuid找到对应的userChanelRel
            UserChanelRel userChanelRel = RelUUIDRel.get(dataContent.getUuid());
            //当websocket 第一次open的时候，初始化channel，把用的channel 和 userid 关联起来
            Long senderId = dataContent.getChat().getSenderId();
            userChanelRel.put(senderId, channel);
		   ChanelUser chanelUser = new ChanelUser();
		   chanelUser.setUuid(dataContent.getUuid());
		   chanelUser.setSenderId(dataContent.getChat().getSenderId());
		   ChanelUUIDRel.put(channel,chanelUser);
		   sendRemoveUserInfo(ctx, 1 , "加入聊天室");

        }else if(action == 3){//聊天类型的消息，把聊天记录保存到数据库
		    ChatMsgExtMapper chatMsgExtMapper = SpringUtil.getBean(ChatMsgExtMapper.class);
            //插入数据
            Chat chat = dataContent.getChat();
            ChatMsg chatMsg = CopyUtil.copy(chat, ChatMsg.class);
            chatMsg.setUuid(dataContent.getUuid());
            chatMsg.setGmtCreate(System.currentTimeMillis()+"");
            chatMsgExtMapper.insert(chatMsg);
            LOG.info("聊天记录插入"+chatMsg);
            //根据uuid取出相应userChanelRel， 再将userChanelRel里的每个channel都发送消息
            UserChanelRel userChanelRel = RelUUIDRel.get(dataContent.getUuid());
            //根据senderId查出userInfo里的用户信息
		   UserinfoExample userinfoExample = new UserinfoExample();
		   userinfoExample.createCriteria().andUserIdEqualTo(chat.getSenderId());
		   UserinfoMapper userinfoMapper = SpringUtil.getBean(UserinfoMapper.class);
		   Userinfo userinfo = userinfoMapper.selectByExample(userinfoExample).get(0);
		   ChatResp chatResp = CopyUtil.copy(chatMsg, ChatResp.class);
		   chatResp.setImg_url(userinfo.getImgUrl());
		   chatResp.setNick(userinfo.getNick());

		   CommonResp<ChatResp> chatRespCommonResp = new CommonResp<>();
		   chatRespCommonResp.setCode(3);
		   chatRespCommonResp.setMessage("消息发送成功");
		   chatRespCommonResp.setContent(chatResp);

		   if(userChanelRel == null)
                throw new BusinessException(BusinessExceptionCode.JOIN_A_GROUP_BEFORE_CHAT);
            for(Map.Entry<Long, Channel> entry: userChanelRel.getManage().entrySet()){
                entry.getValue().writeAndFlush(new TextWebSocketFrame(
                        JsonUtils.objectToJson(chatRespCommonResp)
                ));
            }
        } else if(action == 0){
		   CommonResp<Object> objectCommonResp = new CommonResp<>();
		   objectCommonResp.setCode(0);
		   objectCommonResp.setMessage("心跳包发送成功");
		   ctx.channel().writeAndFlush(new TextWebSocketFrame(
		   		JsonUtils.objectToJson(objectCommonResp)
		   ));
		   System.out.print("收到来自channel:"+ctx.channel()+"的心跳包");
//		   LOG.info("收到来自channel:"+ctx.channel()+"的心跳包");
	   }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
        LOG.info("加入channel_id为"+ctx.channel().id());
	}

	@Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String chanelId = ctx.channel().id().asShortText();
		sendRemoveUserInfo(ctx, 2 , "退出聊天室");
		System.out.println("客户端被移除：channel id 为："+chanelId);
		LOG.info("客户端被移除：channel id 为："+chanelId);

		users.remove(ctx.channel());

    }

	private void sendRemoveUserInfo(ChannelHandlerContext ctx, Integer code, String message) {
		ChanelUser chanelUser = ChanelUUIDRel.get(ctx.channel());
		if(chanelUser != null ){
			UserChanelRel userChanelRel1 = RelUUIDRel.get(chanelUser.getUuid());
			if(userChanelRel1 != null) {
				UserinfoMapper bean = SpringUtil.getBean(UserinfoMapper.class);
				UserinfoExample userinfoExample = new UserinfoExample();
				userinfoExample.createCriteria().andUserIdEqualTo(chanelUser.getSenderId());
				Userinfo userinfo = bean.selectByExample(userinfoExample).get(0);
				CommonResp<Userinfo> userinfoCommonResp = new CommonResp<>();
				userinfoCommonResp.setCode(code);
				userinfoCommonResp.setMessage(userinfo.getNick()+message);
				userinfoCommonResp.setContent(userinfo);

				for(Map.Entry<Long, Channel> entry: userChanelRel1.getManage().entrySet()){
					entry.getValue().writeAndFlush(new TextWebSocketFrame(
							JsonUtils.objectToJson(userinfoCommonResp)
					));
				}
			}
		}

	}

	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
		if(cause instanceof BusinessException){
			BusinessExceptionCode code = ((BusinessException) cause).getCode();
			ctx.channel().writeAndFlush(JsonUtils.objectToJson(code));
			LOG.info("netty异常"+code);
		}else {
			//发生了异常后关闭连接，同时从channelgroup移除
			LOG.info("netty异常"+cause);
			ctx.channel().close();
			users.remove(ctx.channel());
		}
    }


	private List<Userinfo> getInlineUserInfos(UserChanelRel userChanelRel1) {
		List<Userinfo> userinfos = null;

		if(userChanelRel1 != null){
			ArrayList<Long> longs = new ArrayList<>();
			for(Map.Entry<Long, Channel> entry: userChanelRel1.getManage().entrySet()){
				if(entry.getValue() == null){
					userChanelRel1.getManage().remove(entry.getKey());
				}else{
					if(entry.getKey() != null)
						longs.add(entry.getKey());
				}
			}
			UserinfoExtMapper bean = SpringUtil.getBean(UserinfoExtMapper.class);
			userinfos = bean.selectIdIn(longs);
		}
		return userinfos;
	}

	private void sendJoinOrExit(Integer code, String message, ChanelUser chanelUser){
    	//查出退出人的昵称
		UserinfoExample userinfoExample = new UserinfoExample();
		userinfoExample.createCriteria().andUserIdEqualTo(chanelUser.getSenderId());
		UserinfoMapper bean = SpringUtil.getBean(UserinfoMapper.class);
		Userinfo userinfo = bean.selectByExample(userinfoExample).get(0);
		//封装通用类
		CommonResp<List<Userinfo>> objectCommonResp = new CommonResp<>();
		objectCommonResp.setCode(code);
		objectCommonResp.setMessage(userinfo.getNick()+message);
		UserChanelRel userChanelRel1 = RelUUIDRel.get(chanelUser.getUuid());
		List<Userinfo> userInfoList = getInlineUserInfos(userChanelRel1);
		objectCommonResp.setContent(userInfoList);
		//给在这个聊天室里的人发退出消息
		if(userChanelRel1 != null)
			for(Map.Entry<Long, Channel> entry: userChanelRel1.getManage().entrySet()){
				entry.getValue().writeAndFlush(new TextWebSocketFrame(
						JsonUtils.objectToJson(objectCommonResp)
				));
		}
	}

}
