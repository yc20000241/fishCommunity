package com.fish.community.demo.util;

import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

@Component
public class FileUtil {

	public static String downFile(MultipartFile file, String kind, Long userId) throws IOException {
		String filePath = new File("./file/"+kind).getAbsolutePath();
		File fileUpload = createFolder(kind);
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

	public static File createFolder(String kind){
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

		return fileUpload;
	}

	public static boolean fileIsExist(String path){
		String filePath = new File(path).getAbsolutePath();
		File fileUpload = new File(filePath);
		if (!fileUpload.exists()) {
			return false;
		}
		return true;
	}

	public static void writeToFile(String content, String kind, String fileName) throws FileNotFoundException {
		{
			BufferedReader bufferedReader = null;
			BufferedWriter bufferedWriter = null;
			File distFile=null;
			try {
				distFile= new File("./file/"+kind+"/"+fileName);
				if (!distFile.getParentFile().exists())
					distFile.getParentFile().mkdirs();
				bufferedReader = new BufferedReader(new StringReader(content));
				bufferedWriter = new BufferedWriter(new FileWriter(distFile));
				char buf[] = new char[1024]; //字符缓冲区
				int len;
				while ((len = bufferedReader.read(buf)) != -1) {
					bufferedWriter.write(buf, 0, len);
				}
				bufferedWriter.flush();
				bufferedReader.close();
				bufferedWriter.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String ReadFile(String content) {
		StringBuffer sb = null;
		try (BufferedReader br = new BufferedReader(new FileReader(content))) {
			sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String ReadPartContentFromFile(String content) {
		StringBuffer sb = null;
		try (BufferedReader br = new BufferedReader(new FileReader(content))) {
			sb = new StringBuffer();
			String line = "";
			int count = 0;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				count++;
				if(count % 10 == 0 && sb.toString().length() > 300)
					break;;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
