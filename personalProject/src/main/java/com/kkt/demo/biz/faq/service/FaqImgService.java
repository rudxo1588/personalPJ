package com.kkt.demo.biz.faq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kkt.demo.biz.faq.mapper.FaqImgMapper;
import com.kkt.demo.biz.faq.vo.Faq;
import com.kkt.demo.biz.faq.vo.FaqImg;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqImgService {

	private final FaqImgMapper faqImgMapper;

	/*
	 * faqImg리스트 저장
	 */
	public void insertList(List<FaqImg> list, int faqSeq) {

		if(list != null) {
			for(FaqImg vo : list) {
				vo.setFaqSeq(faqSeq);
				this.insert(vo);
			}
		}
	}

	/*
	 * faqImg 저장
	 */
	public int insert(FaqImg faqImg) {
		return faqImgMapper.insert(faqImg);
	}

	/*
	 * faqImg 삭제
	 */
	public int delete(FaqImg faqImg) {
		faqImg.setDelYn("Y");
		return faqImgMapper.delete(faqImg);
	}

	public int deleteByFaqSeq(int faqSeq) {
		return faqImgMapper.deleteByFaqSeq(faqSeq);
	}



}
