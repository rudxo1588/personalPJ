package com.kkt.demo.biz.faq.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkt.demo.biz.faq.mapper.FaqMapper;
import com.kkt.demo.biz.faq.vo.Faq;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqService {

	private final FaqMapper faqMapper;

	private final FaqImgCrudService faqImgCrudService;

	/*
	 * faq리스트 조회
	 */
	public List<Faq> getList(Faq faq) {
		return faqMapper.getList();
	}

	/*
	 * faq상세화면
	 */
	public Faq getDetail(int faqSeq) {
		return faqMapper.getDetail(faqSeq);
	}

	/*
	 * faq저장
	 */
	@Transactional
	public int save(Faq faq) throws Exception {
		int result = faqMapper.insert(faq);
		System.out.println(result);
		System.out.println(faq);

		if(result > 0) {
			faqImgCrudService.saveList(faq.getFaqImgList(), faq.getFaqSeq(), faq.getRgstrId());
		}

		return result;
	}

	/*
	 * faqList 저장
	*/
	public void addList(List<Faq> faqList) throws Exception {
		for (Faq vo : faqList) {
			this.save(vo);
		}
	}

	/*
	 * faq 삭제
	 */
	@Transactional
	public int delete(Faq faq) {
		return faqMapper.delete(faq);
	}

	/*
	 * faq 수정
	 */
	@Transactional
	public int edit(Faq faq) throws Exception {

		int result = faqMapper.update(faq);

		if(result > 0) {
			faqImgCrudService.saveList(faq.getFaqImgList(), faq.getFaqSeq(), faq.getRgstrId());
		}

		return result;
	}

	// test aop 에러용
	public void test() throws Exception {
		this.save(null);
	}



}
