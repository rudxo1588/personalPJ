package com.kkt.demo.biz.faq.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kkt.demo.biz.faq.service.FaqImgService;
import com.kkt.demo.biz.faq.service.FaqService;
import com.kkt.demo.biz.faq.vo.Faq;
import com.kkt.demo.biz.faq.vo.FaqImg;
import com.kkt.demo.biz.user.vo.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {

	private final FaqService faqService;

	private final FaqImgService faqImgService;

	/*
	 * faq리스트 페이지 조회
	 */
	@GetMapping("/list")
	public ModelAndView listPage() {
		ModelAndView mv = new ModelAndView("/faq/faqList");
		return mv;
	}

	/*
	 * faq리스트 조회
	 */
	@GetMapping("")
	public List<Faq> list(Faq faq) {
		List<Faq> list = faqService.getList(faq);
		return list;
	}


	/*
	 * faq 상세화면 페이지 이동
	 */
	@GetMapping("/detail/{faqSeq}")
	public ModelAndView detailPage(@PathVariable(value="faqSeq")int faqSeq) {
		ModelAndView mv = new ModelAndView("/faq/faqDetail");
		mv.addObject("faqSeq", faqSeq);
		return mv;
	}

	/*
	 * faq상세화면
	 */
	@GetMapping("/{faqSeq}")
	public Faq getDetail(@PathVariable("faqSeq") int faqSeq) {
		Faq faq = new Faq();
		faq = faqService.getDetail(faqSeq);
		return faq;
	}

	/*
	 * faq 등록화면
	 */
	@GetMapping("/add")
	public ModelAndView addPage() {
		ModelAndView mv = new ModelAndView("faq/faqWrite");
		return mv;
	}

	/*
	 * faq 등록
	 */
	@PostMapping("")
	public void save(@Valid Faq faq) throws Exception {
		faqService.save(faq);
	}

	/*
	 * faq 수정
	 */
	@PutMapping("/{faqSeq}")
	public void edit(@PathVariable(value = "faqSeq")int faqSeq, HttpSession session, @Valid Faq faq) throws Exception {

		User user = (User)session.getAttribute("user");

		faq.setRgstrId(user.getId());
		faq.setModrId(user.getId());
		faq.setFaqSeq(faqSeq);

		faqService.edit(faq);
	}


	/*
	 * faq 삭제
	 */
	@DeleteMapping("/{faqSeq}")
	public void delete(@PathVariable(value="faqSeq") int faqSeq) throws Exception {
		Faq faq = new Faq();
		faq.setFaqSeq(faqSeq);
		faqService.delete(faq);
	}

	@PostMapping("/excel/upload")
	public String excelUpload(@RequestParam(value = "excel", required = false) MultipartFile file , HttpSession session) throws Exception {

		User user = (User)session.getAttribute("user");

		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		List<Faq> faqList = new ArrayList<>();

		Sheet worksheet = workbook.getSheetAt(0);

		for(int i = 1; i<worksheet.getPhysicalNumberOfRows(); i++) {

			Row row = worksheet.getRow(i);

			Faq vo = new Faq();
			vo.setFaqTitle(row.getCell(0).getStringCellValue());
			vo.setFaqContent(row.getCell(1).getStringCellValue());
			vo.setRgstrId(user.getId());
			vo.setModrId(user.getId());

			faqList.add(vo);
		}

		faqService.addList(faqList);


		return "faq/faqList";
	}
}
