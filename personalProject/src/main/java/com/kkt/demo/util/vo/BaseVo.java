package com.kkt.demo.util.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BaseVo {

	private String rgstrId;

	private String modrId;

	private LocalDate rgstDt;

	private LocalDate modDt;

	private String crudMode;

}
