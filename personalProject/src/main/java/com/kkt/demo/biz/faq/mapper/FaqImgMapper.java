package com.kkt.demo.biz.faq.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kkt.demo.biz.faq.vo.Faq;
import com.kkt.demo.biz.faq.vo.FaqImg;

@Mapper
public interface FaqImgMapper {

	// faqimg리스트 조회
	List<FaqImg> getList();

	// faqimg 삭제
	int delete(FaqImg faqImg);

	// faqimg 등록
	int insert(FaqImg faqImg);

	// faqimg 삭제
	int deleteByFaqSeq(int faqSeq);

	int update(FaqImg faqImg);

}
