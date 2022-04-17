package com.kkt.demo.tools;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileTools {

	/*
	 * file 저장 공통화
	 * return : fileName
	 */
	public static String insertFile(MultipartFile file) throws Exception {
		String saveFileNm = "";
		if(!file.isEmpty()) {
			String fileNm = file.getOriginalFilename().replaceAll(" ", "");	// 파일 명에서 공백 제거
			String regex = "^([\\S]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)";		// 파일 정규식 체크

			if(!fileNm.matches(regex)) {
				throw new Exception("이미지만 등록해주세요");
			}

			int idx = fileNm.lastIndexOf(".");
			String extNm = fileNm.substring(idx);

			UUID uuid = UUID.randomUUID();	// 파일명이 겹치지 않도록 난수생성하여 파일명 만들어준다.
			saveFileNm = uuid + extNm;


			File f = new File("C://upload");
			if(!f.exists()) f.mkdirs(); // 폴더가 없다면 폴더 생성

			// File.separator / , \ 둘중 하나를 생성
			File filepath = new File("c://upload" + File.separator + saveFileNm);

			file.transferTo(filepath);
		}

		return saveFileNm;
	}

}
