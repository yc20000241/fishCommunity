package com.fish.community.demo.controller;

import com.fish.community.demo.model.Articles;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@ResponseBody
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private IndexService indexService;

	@GetMapping("/hotArticle/{count}")
	public CommonResp hotArticle(@PathVariable("count") Integer count, HttpServletRequest request){
		List<Articles> articles = indexService.hotArticle(count, request);
		CommonResp<List<Articles>> listCommonResp = new CommonResp<>();
		listCommonResp.setContent(articles);
		return listCommonResp;
	}

	@GetMapping("/todayVisit")
	public CommonResp todayVisit(){
		Integer todayVisit = indexService.todayVisit();
		CommonResp<Integer> integerCommonResp = new CommonResp<>();
		integerCommonResp.setContent(todayVisit);
		return integerCommonResp;
	}

}
