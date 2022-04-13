package com.fish.community.demo.controller;

import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;
import com.fish.community.demo.service.FileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/upload/{kind}")
	@ApiOperation("上传文件")
	public Object uploadFile(@ApiParam("文件") @RequestParam("file") MultipartFile file,
							 @ApiParam("分类:img/article") @PathVariable("kind") String kind,
							 HttpServletRequest request
	) throws IOException {
		if(file.isEmpty())
			throw new BusinessException(BusinessExceptionCode.UPLOAD_FILE_NOT_EMPTY);
		return fileService.uploadFile(file, kind, request);
	}



}
