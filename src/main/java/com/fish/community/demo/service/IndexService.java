package com.fish.community.demo.service;

import com.fish.community.demo.mapper.ArticlesExtMapper;
import com.fish.community.demo.mapper.VisitsTodayExtMapper;
import com.fish.community.demo.mapper.VisitsTodayMapper;
import com.fish.community.demo.model.Articles;
import com.fish.community.demo.model.VisitsToday;
import com.fish.community.demo.model.VisitsTodayExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class IndexService {

	@Autowired
	private ArticlesExtMapper articlesExtMapper;

	@Autowired
	private VisitsTodayMapper visitsTodayMapper;

	@Autowired
	private VisitsTodayExtMapper visitsTodayExtMapper;

	@Transactional
	public List<Articles> hotArticle(Integer count, HttpServletRequest request) {
		PageHelper.startPage(0, count);
		List<Articles> articles = articlesExtMapper.selectHotArticle();

		//增加今日访问记录
		Date date = new Date(System.currentTimeMillis());
		VisitsTodayExample visitsTodayExample = new VisitsTodayExample();
		visitsTodayExample.createCriteria().andDateEqualTo(date).andUserAgentEqualTo(request.getHeader("User-Agent"));
		List<VisitsToday> visitsTodays = visitsTodayMapper.selectByExample(visitsTodayExample);
		if(!visitsTodays.isEmpty()){//非空结束
			return articles;
		}else{//空则表示今天没登录
			VisitsToday visitsToday = new VisitsToday();
			visitsToday.setUserAgent(request.getHeader("User-Agent"));
			visitsToday.setDate(date);
			visitsTodayMapper.insert(visitsToday);
		}

		return articles;
	}

	public Integer todayVisit() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String d = dateFormat.format(date);
		return visitsTodayExtMapper.todayVisit(d);
	}
}
