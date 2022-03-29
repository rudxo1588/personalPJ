package com.kkt.demo.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kkt.demo.biz.vo.Faq;

@Mapper
public interface FaqMapper {

	// faq리스트 조회
	List<Faq> getList();

	int insert(Faq faq);
}
