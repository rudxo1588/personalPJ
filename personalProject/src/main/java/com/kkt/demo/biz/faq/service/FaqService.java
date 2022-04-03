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

}
