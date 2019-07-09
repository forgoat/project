package com.hqei.server.enums;

public enum AuditResultEnum {

	SUCCESS(0), ERROR(1);

	private Integer value;

	AuditResultEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}