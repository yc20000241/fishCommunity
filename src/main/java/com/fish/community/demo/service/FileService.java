package com.fish.community.demo.service;

import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

	@Autowired
	private FileUtil fileUtil;

	public Object uploadFile(MultipartFile file, String kind, long id) throws IOException {
		String fileName = fileUtil.downFile(file, kind, id);
		CommonResp<String> stringCommonResp = new CommonResp<>();
		stringCommonResp.setContent(fileName);
		stringCommonResp.setMessage("上传成功");
		return stringCommonResp;
	}


}
