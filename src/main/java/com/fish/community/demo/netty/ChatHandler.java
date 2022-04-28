package com.fish.community.demo.netty;

import com.fish.community.demo.SpringUtil;
import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.mapper.ChatMsgMapper;
import com.fish.community.demo.model.ChatMsg;
import com.fish.community.demo.util.CopyUtil;
import com.fish.community.demo.util.JsonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.context.annotation.DependsOn;

import java.util.Map;


/**
 * 用于处理消息的handler
 * 由于它的传输数据的载体是frame，这个frame 在netty中，是用于为websocket专门处理文本对象的，frame是消息的载体，此类叫：TextWebSocketFrame
 */
@DependsOn("springUtil")
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //用于记录和管理所有客户端的channel
    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg){
        //获取客户端所传输的消息
        String content = msg.text();
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
            // 当websocket 第一次open的时候，初始化channel，把用的channel 和 userid 关联起来，
            Long senderId = dataContent.getChat().getSenderId();
            UserChanelRel userChanelRel = new UserChanelRel();
            userChanelRel.put(senderId,channel);
            // 并生成单独聊天号，将userChanelRel与会议号关联起来
            Long uuid = System.currentTimeMillis();
            ChannelUUIDRel.put(uuid, userChanelRel);
            ChatMsg chatMsg = CopyUtil.copy(dataContent.getChat(), ChatMsg.class);
            chatMsg.setUuid(uuid);
            //发送
            channel.writeAndFlush(new TextWebSocketFrame(
                    JsonUtils.objectToJson(chatMsg)
            ));

        } else if(action == 2){//第一次加入群聊
            //根据uuid找到对应的userChanelRel
            UserChanelRel userChanelRel = ChannelUUIDRel.get(dataContent.getUuid());
            //当websocket 第一次open的时候，初始化channel，把用的channel 和 userid 关联起来
            Long senderId = dataContent.getChat().getSenderId();
            userChanelRel.put(senderId, channel);

        }else if(action == 3){//聊天类型的消息，把聊天记录保存到数据库
            //插入数据
            Chat chat = dataContent.getChat();
            ChatMsgMapper chatMsgMapper = SpringUtil.getBean(ChatMsgMapper.class);
            ChatMsg chatMsg = CopyUtil.copy(chat, ChatMsg.class);
            chatMsg.setUuid(dataContent.getUuid());
            chatMsgMapper.insert(chatMsg);
            //根据uuid取出相应userChanelRel， 再将userChanelRel里的每个channel都发送消息
            UserChanelRel userChanelRel = ChannelUUIDRel.get(dataContent.getUuid());
            if(userChanelRel == null)
                throw new BusinessException(BusinessExceptionCode.JOIN_A_GROUP_BEFORE_CHAT);
            for(Map.Entry<Long, Channel> entry: userChanelRel.getManage().entrySet()){
                entry.getValue().writeAndFlush(new TextWebSocketFrame(
                        JsonUtils.objectToJson(chatMsg)
                ));
            }
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String chanelId = ctx.channel().id().asShortText();
        System.out.println("客户端被移除：channel id 为："+chanelId);

        users.remove(ctx.channel());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生了异常后关闭连接，同时从channelgroup移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}
