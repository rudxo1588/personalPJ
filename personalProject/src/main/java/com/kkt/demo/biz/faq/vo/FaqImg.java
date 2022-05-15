package com.kkt.demo.biz.faq.vo;

import org.springframework.web.multipart.MultipartFile;

import com.kkt.demo.util.vo.BaseVo;

import lombok.Data;

@Data
public class FaqImg extends BaseVo {

    private int imgSeq;
	private int faqSeq;
	private String faqImg;
	private String delYn;
	private String imgNm;
	private String imgTitle;

	private MultipartFile imgFile;
}
