package com.fish.community.demo.service;

import com.fish.community.demo.mapper.UserMapper;
import com.fish.community.demo.model.User;
import com.fish.community.demo.model.UserExample;
import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class FileService {

	@Autowired
	private FileUtil fileUtil;

	@Autowired
	private UserMapper userMapper;

	public Object uploadFile(MultipartFile file, String kind, HttpServletRequest request) throws IOException {
		//根据usertokn查id
		String token = request.getHeader("token");
		UserExample userExample = new UserExample();
		userExample.createCriteria().andTokenEqualTo(token);
		User user = userMapper.selectByExample(userExample).get(0);

		String fileName = fileUtil.downFile(file, kind, user.getId());
		CommonResp<String> stringCommonResp = new CommonResp<>();
		stringCommonResp.setContent("/"+kind+"/"+fileName);
		stringCommonResp.setMessage("上传成功");
		return stringCommonResp;
	}


}
