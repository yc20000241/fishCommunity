package com.fish.community.demo.event;

import com.fish.community.demo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		if(FileUtil.fileIsExist("./file/userToken/userToken.txt")){
			String s = FileUtil.ReadFile("./file/userToken/userToken.txt");
			String[] split = s.split("\\n");
			for (int i = 0; i < split.length; i += 2){
				stringRedisTemplate.opsForValue().set(split[i],split[i+1]);
			}
		}


	}
}
