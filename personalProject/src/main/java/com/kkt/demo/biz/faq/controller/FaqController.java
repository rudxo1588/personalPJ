package com.kkt.demo.biz.faq.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kkt.demo.biz.faq.service.FaqImgCrudService;
import com.kkt.demo.biz.faq.service.FaqImgService;
import com.kkt.demo.biz.faq.service.FaqService;
import com.kkt.demo.biz.faq.vo.Faq;
import com.kkt.demo.biz.faq.vo.FaqImg;
import com.kkt.demo.tools.FileTools;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {

	private final FaqService faqService;

	private final FaqImgService faqImgService;

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

//	/*
//	 * faq 등록
//	 */
//	@PostMapping("/add")
//	@ResponseBody
//	public void add(@Valid Faq faq, @RequestParam(value = "file", required = false) List<MultipartFile> file) throws Exception {
//
//		faqService.add(faq, file);
//	}

	/*
	 * faq 등록
	 */
	@PostMapping("save")
	@ResponseBody
	public void add(@Valid Faq faq) throws Exception {
		faqService.save(faq);
	}

	/*
	 * faq 수정
	 */
	@PostMapping("/edit")
	@ResponseBody
	public void edit(Faq faq) throws Exception {
		faqService.edit(faq);
	}

	/*
	 * faq상세화면
	 */
	@GetMapping("/getDetail")
	public ModelAndView getDetail(Faq faq) {
		ModelAndView mv = new ModelAndView("/faq/faqDetail");
		mv.addObject("faq", faqService.getDetail(faq));

		return mv;
	}

	/*
	 * faq 삭제
	 */
	@PostMapping("/delete")
	@ResponseBody
	public void delete(Faq faq) throws Exception {
		faqService.delete(faq);
	}

	/*
	 * faqImg 삭제
	 */
	@PostMapping("/deleteByImgSeq")
	@ResponseBody
	public void deleteByImgSeq(FaqImg faqImg) throws Exception {
		faqImgService.delete(faqImg);
	}
}
