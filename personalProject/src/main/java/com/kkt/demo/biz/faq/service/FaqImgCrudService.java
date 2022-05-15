package com.kkt.demo.biz.faq.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kkt.demo.biz.faq.mapper.FaqImgMapper;
import com.kkt.demo.biz.faq.vo.Faq;
import com.kkt.demo.biz.faq.vo.FaqImg;
import com.kkt.demo.tools.FileTools;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FaqImgCrudService {

	private final FaqImgMapper faqImgMapper;

	// insert
	public int insert(FaqImg faqImg) {
		return faqImgMapper.insert(faqImg);
	}

	// delete
	public int delete(FaqImg faqImg) {
		return faqImgMapper.delete(faqImg);
	}

	// delete by faqseq
	public int deleteByFaqSeq(int faqSeq) {
		return faqImgMapper.deleteByFaqSeq(faqSeq);
	}

	// update
	public int update(FaqImg faqImg) {
		return faqImgMapper.update(faqImg);
	}

	// 이미지 파일 입혀주기
	public List<FaqImg> saveFile(List<FaqImg> list) throws Exception {

		if(list != null && list.size() > 0) {
			for (FaqImg vo : list) {
				if (!vo.getImgFile().isEmpty()) {
					vo.setFaqImg(FileTools.insertFile(vo.getImgFile()));
					vo.setImgNm(vo.getImgFile().getOriginalFilename());
				}
			}
		}

		return list;
	}

	// crudMode에 따라서 각각 CRUD실행
	public void saveList(Faq faq) throws Exception {

		List<FaqImg> list = faq.getFaqImgList();
		list = this.saveFile(list);

		log.debug("==========================start======================================");
		if(list != null && list.size() > 0) {
			for (FaqImg vo : list) {

				vo.setFaqSeq(faq.getFaqSeq());
				vo.setRgstrId(faq.getRgstrId());
				vo.setModrId(faq.getModrId());
				log.debug(vo.getCrudMode());

				// update
				if(StringUtils.equalsIgnoreCase("U", vo.getCrudMode())) {
					this.update(vo);
					continue;
				}

				// insert
				if(StringUtils.equalsIgnoreCase("C", vo.getCrudMode())) {
					this.insert(vo);
					continue;
				}

				// read
				if(StringUtils.equalsIgnoreCase("R", vo.getCrudMode())) continue;

				// delete
				this.delete(vo);
			}
		}
		log.debug("==========================end======================================");
	}



}
