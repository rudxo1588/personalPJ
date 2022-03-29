package com.kkt.demo.biz.faq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kkt.demo.biz.faq.mapper.FaqMapper;
import com.kkt.demo.biz.faq.vo.Faq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqService {

	private final FaqMapper faqMapper;

	/*
	 * faq리스트 조회
	 */
	public List<Faq> getList() {
		return faqMapper.getList();
	}

	public int add(Faq faq) {
		return faqMapper.insert(faq);
	}

}
