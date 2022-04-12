package com.fish.community.demo.controller;

import com.fish.community.demo.service.FileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping("/upload/{kind}/{id}")
	@ApiOperation("上传文件")
	public Object uploadFile(@ApiParam("文件") @RequestParam("file") MultipartFile file,
							 @ApiParam("图片分类") @PathVariable("kind") String kind,
							 @ApiParam("用户id") @PathVariable("id") long id
	) throws IOException {
		return fileService.uploadFile(file, kind, id);
	}



}
