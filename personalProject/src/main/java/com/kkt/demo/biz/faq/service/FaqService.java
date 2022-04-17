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

//	/*
//	 * faq저장
//	 */
//	public int add(Faq faq, List<MultipartFile> file) throws Exception {
//
//		FileTools fileTools = new FileTools();
//		List<FaqImg> faqImgList = new ArrayList<>();
//
//		if(file != null && file.size() > 0) {
//			for(int i = 0 ; i < file.size(); i++) {
//				if(!file.get(i).isEmpty()) {
//					FaqImg faqImg = new FaqImg();
//					// 파일을 경로에 저장한 후 파일 명을 리턴받음
//					faqImg.setFaqImg(fileTools.insertFile(file.get(i)));
//					faqImg.setImgNm(file.get(i).getOriginalFilename());
//					faqImgList.add(faqImg);
//				}
//			}
//		}
//		int result = faqMapper.insert(faq);
//		faq.setFaqImgList(faqImgList);
//
//		if(result > 0) {
//			faqImgService.insertList(faq, faq.getFaqSeq());
//		}
//
//		return result;
//	}

	/*
	 * faq저장]
	 */
	@Transactional
	public int add(Faq faq) throws Exception {

		List<FaqImg> faqImgFile = new ArrayList<>();
		List<FaqImg> faqImgList = faq.getFaqImgList();

		if(faqImgList != null && faqImgList.size() > 0) {
			for(int i = 0 ; i < faqImgList.size(); i++) {
				if(!faqImgList.get(i).getImgFile().isEmpty()) {
					FaqImg faqImg = new FaqImg();
					// 파일을 경로에 저장한 후 파일 명을 리턴받음
					faqImg.setFaqImg(FileTools.insertFile(faqImgList.get(i).getImgFile()));
					faqImg.setImgNm(faqImgList.get(i).getImgFile().getOriginalFilename());
					faqImgFile.add(faqImg);
				}
			}
		}

		int result = faqMapper.insert(faq);

		if(result > 0) {
			faqImgService.insertList(faqImgFile, faq.getFaqSeq());
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

		List<FaqImg> faqImgList = faq.getFaqImgList();
		faqImgService.deleteByFaqSeq(faq.getFaqSeq());

		if (faqImgList != null && faqImgList.size() > 0) {
			for (FaqImg vo : faqImgList) {
				if (!vo.getImgFile().isEmpty()) {
					vo.setFaqImg(FileTools.insertFile(vo.getImgFile()));
					vo.setImgNm(vo.getImgFile().getOriginalFilename());
				}
			}
		}

		int result = faqMapper.update(faq);

		if(result > 0) {
			faqImgService.insertList(faqImgList, faq.getFaqSeq());
		}

		return result;
	}

}
