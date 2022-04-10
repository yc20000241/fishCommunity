package com.fish.community.demo.service;

import com.fish.community.demo.resp.CommonResp;
import com.fish.community.demo.util.FileUtil;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

	@Autowired
	private FileUtil fileUtil;

	public Object uploadFile(MultipartFile file) throws IOException {
		boolean flag = fileUtil.downFile(file);
		CommonResp<String> stringCommonResp = new CommonResp<>();


		if(flag){
			stringCommonResp.setSuccess(true);
			stringCommonResp.setContent(file.getOriginalFilename());
		}else
			stringCommonResp.setSuccess(false);

		return stringCommonResp;
	}


}
