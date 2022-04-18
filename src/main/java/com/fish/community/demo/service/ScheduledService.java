package com.fish.community.demo.service;

import com.fish.community.demo.mapper.RegisterVerificationExtMapper;
import com.fish.community.demo.mapper.RegisterVerificationMapper;
import com.fish.community.demo.model.RegisterVerification;
import com.fish.community.demo.model.RegisterVerificationExample;
import com.fish.community.demo.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ScheduledService {

	@Autowired
	private RegisterVerificationExtMapper registerVerificationExtMapper;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	private static String BLANAK = "\n";

	@Scheduled(cron = "0 0 12 * * ?")
	public void scheduledRecordToken() throws FileNotFoundException {
		//删除所有过期的key
		Set<String> keys1 = stringRedisTemplate.keys("backup[0-9]*");
		if(!CollectionUtils.isEmpty(keys1))
			stringRedisTemplate.delete(keys1);

		//查出所有key value 用\n连接写入文件
		Set<String> keys = stringRedisTemplate.keys("*");
		StringBuilder content = new StringBuilder();
		for (String key : keys) {
			content.append(key+BLANAK);
			content.append(stringRedisTemplate.opsForValue().get(key)+BLANAK);
		}

		FileUtil.writeToFile(content.toString(), "userToken","userToken.txt");
	}

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
