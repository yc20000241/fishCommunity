package com.fish.community.demo.util;

import com.fish.community.demo.exception.BusinessException;
import com.fish.community.demo.exception.BusinessExceptionCode;

public class KindUtil {

	static String[] fileKind = {"article", "image", "userImage"};

	public static void isFileKind(String kind){
		for (String s : fileKind) {
			if (s.equals(kind))
				return;
		}
		throw new BusinessException(BusinessExceptionCode.FILE_KIND_NOT_EXIST);
	}
}
