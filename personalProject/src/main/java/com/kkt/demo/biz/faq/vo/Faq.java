package com.kkt.demo.biz.faq.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Faq {

	private int FaqSeq;

	@NotBlank(message = "제목은 필수값 입니다.")
	private String FaqTitle;

	@NotBlank(message = "내용은 필수값 입니다.")
	private String FaqContent;
	private String FaqCreatedt;
	private String FaqDelYn;
	private String FaqType;

	private List<FaqImg> faqImgList;
}
