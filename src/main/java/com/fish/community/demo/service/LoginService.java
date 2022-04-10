package com.fish.community.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.mapper.RegisterVerificationMapper;
import com.fish.community.demo.mapper.UserMapper;
import com.fish.community.demo.model.RegisterVerification;
import com.fish.community.demo.model.RegisterVerificationExample;
import com.fish.community.demo.model.User;
import com.fish.community.demo.model.UserExample;
import com.fish.community.demo.req.UserReq;
import com.fish.community.demo.resp.LoginResp;
import com.fish.community.demo.util.SendQQEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RegisterVerificationMapper registerVerificationMapper;

	@Autowired
	private SendQQEmailUtil sendQQEmailUtil;


	public LoginResp loginWithPassword(UserReq userReq) {
		LoginResp loginResp = new LoginResp();

		//根据邮箱获取用户信息
		User user = getUserByEmail(userReq.getEmail());
		if(user == null){
			loginResp.setLoginSuccess(false);
			return loginResp;
		}

		if(!user.getPassword().equals(userReq.getPassword())){
			loginResp.setLoginSuccess(false);
			return loginResp;
		}

		loginResp.setLoginSuccess(true);
		loginResp.setToken(user.getToken());
		//将token-user放入redis缓存
		redisTemplate.opsForValue().set(user.getToken(), JSONObject.toJSONString(user), 3600 * 24, TimeUnit.SECONDS);

		return loginResp;
	}

	//判断验证码是否正确
	private void getVerification(String emailToken, String emailVerification) {
		RegisterVerificationExample registerVerificationExample = new RegisterVerificationExample();
		registerVerificationExample.createCriteria().andEmailTokenEqualTo(emailToken);
		List<RegisterVerification> registerVerifications = registerVerificationMapper.selectByExample(registerVerificationExample);
		//验证码token不存在
		if(registerVerifications.isEmpty())
			throw new BusinessException(BusinessExceptionCode.QQ_VERIFICATION_TOKEN_WRONG);
		//验证码不匹配
		RegisterVerification registerVerification = registerVerifications.get(0);
		String realVerification = registerVerification.getEmailVerification();
		if(!realVerification.equals(emailVerification))
			throw new BusinessException(BusinessExceptionCode.QQ_VERIFICATION_WRONG);
		//验证码过期
		long time = System.currentTimeMillis() - Long.valueOf(registerVerification.getCurrentTimeMillis());
		if(time > 1000*60*5)
			throw new BusinessException(BusinessExceptionCode.QQ_VERIFICATION_EXPIRED);

	}

	//发送qq验证码
	public RegisterVerification getQQVerification(String email) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andEmailEqualTo(email);
		List<User> users = userMapper.selectByExample(userExample);
		if(users.isEmpty())
			return null;

		final int LOGIN = 1;

		//如果发送成功则插入email_verification
		RegisterVerification registerVerification = null;
		try{
			registerVerification = sendQQEmailUtil.sendQQEmail(email, LOGIN);
			registerVerification.setCurrentTimeMillis(System.currentTimeMillis() + "");
			registerVerificationMapper.insert(registerVerification);
			return registerVerification;
		}catch (Exception e){
			throw new BusinessException(BusinessExceptionCode.VERIFICATION_SEND_FAILED);
		}
	}

	public LoginResp loginWithEmail(UserReq userReq) {
		LoginResp loginResp = new LoginResp();
		//根据emailtoken查看验证码是否正确
		getVerification(userReq.getEmailToken(), userReq.getEmailVerification());

		//根据邮箱获取用户信息
		User user = getUserByEmail(userReq.getEmail());
		if(user == null)
			throw new BusinessException(BusinessExceptionCode.USER_NOT_REGISTER);

		loginResp.setLoginSuccess(true);
		loginResp.setToken(user.getToken());
		//将token-user放入redis缓存
		redisTemplate.opsForValue().set(user.getToken(), JSONObject.toJSONString(user), 3600 * 24, TimeUnit.SECONDS);

		return loginResp;
	}

	//通过email获取用户信息
	public User getUserByEmail(String email){
		UserExample userExample = new UserExample();
		userExample.createCriteria().andEmailEqualTo(email);
		List<User> users = userMapper.selectByExample(userExample);

		if(users.isEmpty()){
			return null;
		}else
			return users.get(0);
	}
}
