package com.kkt.demo.biz.faq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
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

	/*
	 * faq저장
	 */
	public int add(Faq faq, List<MultipartFile> file) throws Exception {

		FileTools fileTools = new FileTools();
		List<FaqImg> faqImgList = new ArrayList<>();

		if(file != null && file.size() > 0) {
			for(int i = 0 ; i < file.size(); i++) {
				if(!file.get(i).isEmpty()) {
					FaqImg faqImg = new FaqImg();
					// 파일을 경로에 저장한 후 파일 명을 리턴받음
					faqImg.setFaqImg(fileTools.insertFile(file.get(i)));
					faqImg.setImgNm(file.get(i).getOriginalFilename());
					faqImgList.add(faqImg);
				}
			}
		}
		int result = faqMapper.insert(faq);
		faq.setFaqImgList(faqImgList);

		if(result > 0) {
			faqImgService.insertList(faq, faq.getFaqSeq());
		}

		return result;
	}

	/*
	 * faq삭제
	 */
	public int delete(Faq faq) {
		return faqMapper.delete(faq);
	}

	/*
	 * faq수정
	 */
	public int edit(Faq faq, List<MultipartFile> file) throws Exception {

		FileTools fileTools = new FileTools();
		List<FaqImg> faqImgList = faq.getFaqImgList();

		List<FaqImg> fileFaqImgList = new ArrayList<FaqImg>();
		FaqImg faqImg = new FaqImg();

		// 파일이 바뀌지않았으면 재 인서트 목록에서 제외시킨다.
		if(faqImgList != null) {
			for(FaqImg vo : faqImgList) {
				if("Y".equals(vo.getFileChangeYn())) {
					fileFaqImgList.add(vo);

					// 파일 변경시 삭제
					if(vo.getImgSeq() != 0) {
						faqImgService.delete(vo);
					}
				}
			}
		}

		faqImgList = new ArrayList<FaqImg>();
		// 바뀐 파일과 새로 추가된 파일에 대해서만 vo에 담아서 인서트
		for(int i = 0 ; i < file.size(); i++) {
			if(!file.get(i).isEmpty()) {
				faqImg = new FaqImg();
				// 파일을 경로에 저장한 후 파일 명을 리턴받음
				faqImg.setFaqImg(fileTools.insertFile(file.get(i)));
				faqImg.setImgNm(file.get(i).getOriginalFilename());
				faqImgList.add(faqImg);
			}
		}

		faq.setFaqDelYn("N");
		int result = faqMapper.update(faq);

		faq.setFaqImgList(faqImgList);
		if(result > 0) {
			faqImgService.insertList(faq, faq.getFaqSeq());
		}

		return result;
	}

}
