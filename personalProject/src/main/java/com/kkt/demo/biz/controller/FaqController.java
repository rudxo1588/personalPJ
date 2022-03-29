package com.kkt.demo.biz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kkt.demo.biz.service.FaqService;
import com.kkt.demo.biz.vo.Faq;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {

	private final FaqService faqService;

	/*
	 * faq리스트 조회
	 */
	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("/faq/faqList");
		mv.addObject("list", faqService.getList());

		return mv;
	}

	/*
	 * faq 등록화면
	 */
	@GetMapping("/add")
	public ModelAndView addPage() {
		ModelAndView mv = new ModelAndView("/faq/faqWrite");
		return mv;
	}

	/*
	 * faq 등록화면
	 */
	@PostMapping("/add")
	@ResponseBody
	public void add(@RequestBody @Valid Faq faq) {
		faqService.add(faq);
	}
}
