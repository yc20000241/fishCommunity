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
import com.fish.community.demo.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

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

		user = putTokenToRedis(user);

		loginResp.setLoginSuccess(true);
		loginResp.setToken(user.getToken());

		return loginResp;
	}

	public LoginResp loginWithEmail(UserReq userReq) {
		LoginResp loginResp = new LoginResp();
		//根据emailtoken查看验证码是否正确
		getVerification(userReq.getEmailToken(), userReq.getEmailVerification());

		//根据邮箱获取用户信息
		User user = getUserByEmail(userReq.getEmail());
		if(user == null)
			throw new BusinessException(BusinessExceptionCode.USER_NOT_REGISTER);

		user = putTokenToRedis(user);
		loginResp.setLoginSuccess(true);
		loginResp.setToken(user.getToken());

		return loginResp;
	}

	public User putTokenToRedis(User user) {
		String userToken;

		//查看reids中token是否存在

		Object o = stringRedisTemplate.opsForValue().get(user.getToken());
		if(o != null){
			//移去原来token
			System.out.println("移除"+ stringRedisTemplate.delete(user.getToken()));
			System.out.println(stringRedisTemplate.opsForValue().get(user.getToken()));
			//雪花算法生成新的用户token
			SnowFlake snowFlake = new SnowFlake(1, 1);
			userToken = snowFlake.nextId()+"";
			//更新用户token
			updateUserToken(userToken, user.getEmail());
		}else
			userToken = user.getToken();
		user = getUserByEmail(user.getEmail());
		//将token-user放入redis缓存
		stringRedisTemplate.opsForValue().set(userToken, JSONObject.toJSONString(user), 3600 * 24 * 30, TimeUnit.SECONDS);

		return user;
	}

	private void updateUserToken(String token, String email) {
		User user = new User();
		user.setToken(token);

		UserExample userExample = new UserExample();
		userExample.createCriteria().andEmailEqualTo(email);
		userMapper.updateByExampleSelective(user, userExample);
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
		User user = getUserByEmail(email);
		if(user == null)
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
