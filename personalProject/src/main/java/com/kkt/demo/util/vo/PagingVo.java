package com.kkt.demo.util.vo;

import lombok.Data;

@Data
public class PagingVo {

	private int next;

	private int now;

	private int start;

	private int end;

	private int total;


}
