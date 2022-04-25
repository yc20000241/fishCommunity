package com.fish.community.demo.service;

import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.mapper.ArticlesExtMapper;
import com.fish.community.demo.mapper.UserMapper;
import com.fish.community.demo.mapper.UserRelationMapper;
import com.fish.community.demo.mapper.UserinfoMapper;
import com.fish.community.demo.model.*;
import com.fish.community.demo.req.FocusUserReq;
import com.fish.community.demo.req.UserInfoReq;
import com.fish.community.demo.resp.UserInfoResp;
import com.fish.community.demo.util.CopyUtil;
import com.fish.community.demo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserInfoService {

	@Autowired
	private ArticlesExtMapper articlesExtMapper;

	@Autowired
	private UserinfoMapper userinfoMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRelationMapper userRelationMapper;

	public UserInfoResp getUserInfo(long userId, HttpServletRequest request) {
		//根据token查看关注者的id
		String token = request.getHeader("token");
		UserExample userExample = new UserExample();
		userExample.createCriteria().andTokenEqualTo(token);
		User user = userMapper.selectByExample(userExample).get(0);

		//查看userId是否存在
		UserinfoExample userinfoExample = new UserinfoExample();
		userinfoExample.createCriteria().andUserIdEqualTo(userId);
		List<Userinfo> userinfos = userinfoMapper.selectByExample(userinfoExample);
		if(userinfos.isEmpty())
			throw new BusinessException(BusinessExceptionCode.USERID_OF_GET_INFO_NOT_EXIST);

		Userinfo userinfo = userinfos.get(0);
		Integer count = articlesExtMapper.getCountByAuthor(userinfo.getUserId());

		UserInfoResp copy = CopyUtil.copy(userinfo, UserInfoResp.class);
		copy.setArticleCount(count);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		copy.setGmtCreate(dateFormat.format(Long.valueOf(copy.getGmtCreate())));

		//查看是否是已点赞状态
		UserRelationExample userRelationExample = new UserRelationExample();
		userRelationExample.createCriteria().andFocusOnUserEqualTo(user.getId()).andFollowedUserEqualTo(copy.getUserId());
		List<UserRelation> userRelations = userRelationMapper.selectByExample(userRelationExample);
		if(userRelations.isEmpty() || userRelations.get(0).getIsFocus() == 0)
			copy.setFocus(false);
		else
			copy.setFocus(true);
		return copy;
	}

	public void updateUserInfo(UserInfoReq userInfoReq) {
		//查看userId是否存在
		UserExample userExample = new UserExample();
		userExample.createCriteria().andIdEqualTo(userInfoReq.getUserId());
		List<User> users = userMapper.selectByExample(userExample);
		if(users.isEmpty())
			throw new BusinessException(BusinessExceptionCode.USERID_OF_MOIDFY_INFO);

		//查看原密码是否正确
		if(userInfoReq.getPassword() != null && !userInfoReq.getPassword().equals("")){
			User user = users.get(0);
			if(!userInfoReq.getPassword().equals(user.getPassword()))
				throw new BusinessException(BusinessExceptionCode.USERID_OF_MOIDFY_INFO);
			//查看新密码是否为空
			if(userInfoReq.getNewPassword() == null || userInfoReq.getNewPassword().equals(""))
				throw new BusinessException(BusinessExceptionCode.MODIFY_USERINFO_NEW_PASSOWRD_EMPTY);
			//加密并更新密码
			User user1 = new User();
			user1.setPassword(userInfoReq.getNewPassword());
			userMapper.updateByExampleSelective(user1, userExample);
		}

		if(userInfoReq.getImgUrl() != null){
			//查看头像图片是否存在
			String imgUrl = userInfoReq.getImgUrl();
			if (imgUrl!=null && !imgUrl.isEmpty() && !FileUtil.fileIsExist(imgUrl))
				throw new BusinessException(BusinessExceptionCode.USER_IMAGE_URL_NOT_EXIST);
		}

		Userinfo userinfo = CopyUtil.copy(userInfoReq, Userinfo.class);
		UserinfoExample userinfoExample = new UserinfoExample();
		userinfoExample.createCriteria().andUserIdEqualTo(userinfo.getUserId());
		userinfoMapper.updateByExampleSelective(userinfo,userinfoExample);
	}

	@Transactional
	public boolean focusUser(FocusUserReq focusUserReq) {
		//查看关注者和被关注者id是否存在
		UserinfoExample userinfoExample = new UserinfoExample();
		userinfoExample.createCriteria().andUserIdEqualTo(focusUserReq.getFocusOnUser());
		List<Userinfo> focusUserinfos = userinfoMapper.selectByExample(userinfoExample);
		if(focusUserinfos.isEmpty())
			throw new BusinessException(BusinessExceptionCode.FOCUS_ON_USER_NOT_EXIST);

		UserinfoExample userinfoExample1 = new UserinfoExample();
		userinfoExample1.createCriteria().andUserIdEqualTo(focusUserReq.getFollowedUser());
		List<Userinfo> followedUserinfos1 = userinfoMapper.selectByExample(userinfoExample1);
		if(followedUserinfos1.isEmpty())
			throw new BusinessException(BusinessExceptionCode.FOLLOWED_USER_NOT_EXIST);

		//查询此关系是否已经存在
		UserRelationExample userRelationExample = new UserRelationExample();
		userRelationExample.createCriteria().andFocusOnUserEqualTo(focusUserReq.getFocusOnUser()).andFollowedUserEqualTo(focusUserReq.getFollowedUser());
		List<UserRelation> userRelations = userRelationMapper.selectByExample(userRelationExample);
		boolean flag = false;
		if(userRelations.isEmpty()){//为空则之前没关注过，直接插入
			UserRelation userRelation = new UserRelation();
			userRelation.setFollowedUser(focusUserReq.getFollowedUser());
			userRelation.setFocusOnUser(focusUserReq.getFocusOnUser());
			userRelation.setIsFocus(1);
			userRelationMapper.insert(userRelation);
			flag = true;
		}else{//此前已关注过
			UserRelation userRelation = userRelations.get(0);
			flag = !(userRelation.getIsFocus() == 1);
			UserRelation userRelation1 = new UserRelation();
			userRelation1.setFollowedUser(focusUserReq.getFollowedUser());
			userRelation1.setFocusOnUser(focusUserReq.getFocusOnUser());
			userRelation1.setIsFocus((userRelation.getIsFocus() == 1 ? 0 : 1));
			userRelationMapper.updateByExampleSelective(userRelation1, userRelationExample);
		}

		Userinfo userinfo = new Userinfo();
		if(flag){//增加关注
			//关注的人的关注数量+1
			userinfo.setFocusOnCount(focusUserinfos.get(0).getFocusOnCount()+1);
			userinfoMapper.updateByExampleSelective(userinfo, userinfoExample);
			//被关注的人的被关注数+1
			userinfo = new Userinfo();
			userinfo.setFollowedCount(followedUserinfos1.get(0).getFollowedCount()+1);
			userinfoMapper.updateByExampleSelective(userinfo, userinfoExample1);
		}else{
			//关注的人的关注数量+1
			userinfo.setFocusOnCount(focusUserinfos.get(0).getFocusOnCount()-1);
			userinfoMapper.updateByExampleSelective(userinfo, userinfoExample);
			//被关注的人的被关注数+1
			userinfo = new Userinfo();
			userinfo.setFollowedCount(followedUserinfos1.get(0).getFollowedCount()-1);
			userinfoMapper.updateByExampleSelective(userinfo, userinfoExample1);
		}

		return flag;
	}
}
