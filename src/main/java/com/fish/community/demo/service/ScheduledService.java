package com.fish.community.demo.service;

import com.fish.community.demo.mapper.RegisterVerificationExtMapper;
import com.fish.community.demo.mapper.RegisterVerificationMapper;
import com.fish.community.demo.model.RegisterVerification;
import com.fish.community.demo.model.RegisterVerificationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ScheduledService {

	@Autowired
	private RegisterVerificationExtMapper registerVerificationExtMapper;


//	@Scheduled(cron = "30 49 21 * * ?")//定时删除无效验证码
//	public void deleteExpiredEmailVerification(){
//		List<Long> longs = new LinkedList<>();
//		List<RegisterVerification> registerVerifications = registerVerificationExtMapper.selectAll();
//		Long currentTime = System.currentTimeMillis();
//
//		for (RegisterVerification registerVerification : registerVerifications) {
//			if( currentTime - Long.valueOf(registerVerification.getCurrentTimeMillis()) > 1000*60*5)
//				longs.add(registerVerification.getId());
//		}
//
//		registerVerificationExtMapper.deleteInIds(longs);
//	}
}
