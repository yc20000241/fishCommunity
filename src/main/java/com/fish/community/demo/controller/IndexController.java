package com.fish.community.demo.controller;

import com.fish.community.demo.mapper.UserMapper;
import com.fish.community.demo.model.User;
import com.fish.community.demo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@ResponseBody
public class IndexController {


	@Autowired
	private StringRedisTemplate redisTemplate;

	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public String test(){
//		UserExample userExample = new UserExample();
//		UserExample.Criteria criteria = userExample.createCriteria();
//		criteria.andIdEqualTo((long) 1);
//		List<User> users = userMapper.selectByExample(userExample);
//		redisTemplate.opsForValue().set("key2","yc002");
//		System.out.println(redisTemplate.opsForValue().get("key1"));
//		return users.get(0);
		return "test";
	}

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String test1(){
//		System.out.println(redisTemplate.opsForValue().get("key2"));
		System.out.println(System.currentTimeMillis());
		return "index";
	}

}
