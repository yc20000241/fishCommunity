package com.fish.community.demo.util;

import com.fish.community.demo.model.RegisterVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class SendQQEmailUtil {

	@Value("${spring.mail.username}")
	private String mailSendFrom;

	@Autowired
	private JavaMailSenderImpl mailSender;

	public RegisterVerification sendQQEmail(String email, int message){
		RegisterVerification registerVerification = new RegisterVerification();
		//雪花算法生成唯一标识
		SnowFlake snowFlake = new SnowFlake(1, 1);
		String emailToken = snowFlake.nextId()+"";
		registerVerification.setEmailToken(emailToken);

		//随机生成6位数字的字符串
		String emailVerification = RandomNum.getRandomNum();
		registerVerification.setEmailVerification(emailVerification);

		//将验证码发送到邮箱
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		if(message == 0){
			simpleMailMessage.setSubject("咸鱼社区注册验证码");
			simpleMailMessage.setText("欢迎注册咸鱼社区，验证码为"+emailVerification+"，有效时间为5分钟");
		}else if(message == 1){
			simpleMailMessage.setSubject("咸鱼社区登录验证码");
			simpleMailMessage.setText("欢迎登录咸鱼社区，验证码为"+emailVerification+"，此验证码只用于短信登录，有效时间为5分钟");
		}

		simpleMailMessage.setTo(email);
		simpleMailMessage.setFrom(mailSendFrom);

		try {
			mailSender.send(simpleMailMessage);
			return registerVerification;
		}catch (Exception e){
			return null;
		}

	}
}
