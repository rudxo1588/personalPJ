package com.kkt.demo.biz.faq.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FaqImg {

    private int imgSeq;
	private int faqSeq;
	private String faqImg;
	private String delYn;
	private String imgNm;

	private MultipartFile imgFile;
}
