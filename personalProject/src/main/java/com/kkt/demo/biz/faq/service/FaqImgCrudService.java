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

	// crudMode에 따라서 각각 CRUD실행
	public void saveList(List<FaqImg> list, int faqSeq, String rgstrId) throws Exception {

		log.debug("==========================start======================================");
		if(list != null && list.size() > 0) {
			for (FaqImg vo : list) {

				vo.setFaqSeq(faqSeq);
				vo.setRgstrId(rgstrId);
				vo.setModrId(rgstrId);
				vo.setFaqImg(FileTools.insertFile(vo.getImgFile()));
				vo.setImgNm(vo.getImgFile().getOriginalFilename());
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
