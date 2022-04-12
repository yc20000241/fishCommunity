package com.fish.community.demo.util;

import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class FileUtil {

	public static String downFile(MultipartFile file, String kind, Long userId) throws IOException {
		String filePath = new File("./file").getAbsolutePath();
		File fileUpload = new File(filePath);
		if (!fileUpload.exists()) {
			fileUpload.mkdirs();
		}

		filePath = new File("./file/"+kind).getAbsolutePath();
		fileUpload = new File(filePath);
		if (!fileUpload.exists()) {
			fileUpload.mkdirs();
		}
		//file.getOriginalFilename()
		String fileName = userId.toString()+"_"+file.getOriginalFilename();
		fileUpload = new File(filePath, fileName);
		try{
			if(!fileUpload.exists())
				file.transferTo(fileUpload);
		}catch (Exception e){
			throw new BusinessException(BusinessExceptionCode.FILE_SAVE_FILED);
		}

		return fileName;
	}

}
