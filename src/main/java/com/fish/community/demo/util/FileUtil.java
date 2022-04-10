package com.fish.community.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileUtil {

	public boolean downFile(MultipartFile file) throws IOException {
		String filePath = new File("./file").getAbsolutePath();
		File fileUpload = new File(filePath);
		if (!fileUpload.exists()) {
			fileUpload.mkdirs();
		}
		fileUpload = new File(filePath, file.getOriginalFilename());
		if(!fileUpload.exists()) {
			file.transferTo(fileUpload);
			return true;
		}
		return false;
	}
}
