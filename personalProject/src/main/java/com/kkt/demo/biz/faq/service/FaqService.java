package com.kkt.demo.biz.faq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kkt.demo.biz.faq.mapper.FaqImgMapper;
import com.kkt.demo.biz.faq.mapper.FaqMapper;
import com.kkt.demo.biz.faq.vo.Faq;
import com.kkt.demo.biz.faq.vo.FaqImg;
import com.kkt.demo.tools.FileTools;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqService {

	private final FaqMapper faqMapper;

	private final FaqImgService faqImgService;

	private final FaqImgCrudService faqImgCrudService;

	/*
	 * faq리스트 조회
	 */
	public List<Faq> getList() {
		return faqMapper.getList();
	}

	/*
	 * faq상세화면
	 */
	public Faq getDetail(Faq faq) {
		return faqMapper.getDetail(faq);
	}

	/*
	 * faq저장
	 */
	@Transactional
	public int save(Faq faq) throws Exception {

		int result = faqMapper.insert(faq);

		if(result > 0) {
			faqImgCrudService.saveList(faq);
		}

		return result;
	}

	/*
	 * faq삭제
	 */
	@Transactional
	public int delete(Faq faq) {
		return faqMapper.delete(faq);
	}

	/*
	 * faq수정
	 */
	@Transactional
	public int edit(Faq faq) throws Exception {

		int result = faqMapper.update(faq);

		if(result > 0) {
			faqImgCrudService.saveList(faq);
		}

		return result;
	}

}
