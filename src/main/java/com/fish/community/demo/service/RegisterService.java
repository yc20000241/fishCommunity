package com.fish.community.demo.service;

import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.mapper.RegisterVerificationMapper;
import com.fish.community.demo.mapper.UserMapper;
import com.fish.community.demo.model.RegisterVerification;
import com.fish.community.demo.model.RegisterVerificationExample;
import com.fish.community.demo.model.User;
import com.fish.community.demo.model.UserExample;
import com.fish.community.demo.req.RegisiterReq;
import com.fish.community.demo.resp.RegisterGetVerificationResp;
import com.fish.community.demo.util.CopyUtil;
import com.fish.community.demo.util.SendQQEmailUtil;
import com.fish.community.demo.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class RegisterService {

	@Autowired
	private RegisterVerificationMapper registerVerificationMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SendQQEmailUtil sendQQEmailUtil;

	public RegisterGetVerificationResp getQQVerification(String email) {
		//查看邮箱是否被注册过
		UserExample userExample = new UserExample();
		userExample.createCriteria().andEmailEqualTo(email);
		List<User> users = userMapper.selectByExample(userExample);
		if(!users.isEmpty())
			throw new BusinessException(BusinessExceptionCode.EMAIL_HAVEN_REGISTERED);

		final int REGISTER = 0;
		try {
			RegisterVerification registerVerification = sendQQEmailUtil.sendQQEmail(email, REGISTER);
			//插入email_verification
			registerVerification.setCurrentTimeMillis(System.currentTimeMillis()+"");
			registerVerificationMapper.insert(registerVerification);
			//返回携带emailtoken的响应对象
			RegisterGetVerificationResp registerGetVerificationResp = new RegisterGetVerificationResp();
			registerGetVerificationResp.setEmailToken(registerVerification.getEmailToken());
			return registerGetVerificationResp;
		}catch (Exception e){
			throw new BusinessException(BusinessExceptionCode.VERIFICATION_SEND_FAILED);
		}



	}

	public void registerUser(RegisiterReq regisiterResp) {
		//根据验证码token在register_verification里查验证码是否匹配
		RegisterVerificationExample registerVerificationExample = new RegisterVerificationExample();
		registerVerificationExample.createCriteria().andEmailTokenEqualTo(regisiterResp.getEmailToken());
		List<RegisterVerification> registerVerifications = registerVerificationMapper.selectByExample(registerVerificationExample);
		if(registerVerifications.isEmpty())
			//验证码token错误
			throw new BusinessException(BusinessExceptionCode.QQ_VERIFICATION_TOKEN_WRONG);
		RegisterVerification registerVerification = registerVerifications.get(0);
		//如果从数据库查出的验证码与传入的验证码一致则注册用户
		if(regisiterResp.getEmailVerification().equals(registerVerification.getEmailVerification())){
			//判断验证码是否过期
			long currentTimeMillis = System.currentTimeMillis();
			long emailVerificationTime = Long.valueOf(registerVerification.getCurrentTimeMillis());
			if(currentTimeMillis - emailVerificationTime> 1000*60*5){
				//验证码过期
				throw new BusinessException(BusinessExceptionCode.QQ_VERIFICATION_EXPIRED);
			}
			//查看邮箱是否存在
			UserExample userExample = new UserExample();
			userExample.createCriteria().andEmailEqualTo(regisiterResp.getEmail());
			List<User> users = userMapper.selectByExample(userExample);
			if(!users.isEmpty())
				//邮箱名已被注册
				throw new BusinessException(BusinessExceptionCode.EMAIL_HAVEN_REGISTERED);

			User user = CopyUtil.copy(regisiterResp, User.class);
			//雪花算法生成usertoken
			SnowFlake snowFlake = new SnowFlake(1, 1);
			String userToken = snowFlake.nextId()+"";
			user.setToken(userToken);
			String defaultPassword = "123456";
			user.setPassword(DigestUtils.md5DigestAsHex(defaultPassword.getBytes()));
			user.setGmtCreate(System.currentTimeMillis()+"");
			//插入user注册信息
			userMapper.insert(user);
		}else
			//验证码错误
			throw new BusinessException(BusinessExceptionCode.QQ_VERIFICATION_WRONG);
	}
}
