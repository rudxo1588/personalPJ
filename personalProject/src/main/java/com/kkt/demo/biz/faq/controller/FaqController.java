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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kkt.demo.biz.faq.service.FaqImgService;
import com.kkt.demo.biz.faq.service.FaqService;
import com.kkt.demo.biz.faq.vo.Faq;
import com.kkt.demo.biz.faq.vo.FaqImg;
import com.kkt.demo.biz.user.vo.User;

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
	public ModelAndView list(Faq faq) {
		ModelAndView mv = new ModelAndView("/faq/faqList");
		System.out.println(faq);
		mv.addObject("list", faqService.getList(faq));

		return mv;
	}

	/*
	 * faq 등록화면
	 */
	@GetMapping("/add")
	public String addPage(Model model, HttpSession session) {
		model.addAttribute("id", session.getAttribute("id"));
		return "faq/faqWrite";
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
	public void edit(Faq faq, HttpSession session) throws Exception {
		User user = (User)session.getAttribute("user");
		faq.setRgstrId(user.getId());
		faq.setModrId(user.getId());
		faqService.edit(faq);
	}

	/*
	 * faq상세화면
	 */
	@GetMapping("/getDetail")
	public ModelAndView getDetail(Faq faq) {
		System.out.println(faq.getModrId());
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
