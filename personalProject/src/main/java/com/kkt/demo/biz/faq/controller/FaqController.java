package com.kkt.demo.biz.faq.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kkt.demo.biz.faq.service.FaqService;
import com.kkt.demo.biz.faq.vo.Faq;

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
	 * faq 등록
	 */
	@PostMapping("/add")
	@ResponseBody
	public void add( @RequestBody @Valid Faq faq) {
		faqService.add(faq);
	}
}
