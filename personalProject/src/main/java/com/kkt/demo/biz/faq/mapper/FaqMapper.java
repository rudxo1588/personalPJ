package com.kkt.demo.biz.faq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kkt.demo.biz.faq.vo.Faq;

@Mapper
public interface FaqMapper {

	// faq리스트 조회
	List<Faq> getList();

	// faq등록
	int insert(Faq faq);

	int update(Faq faq);

	int delete(Faq faq);

	Faq getDetail(Faq faq);
}
