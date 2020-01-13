package com.otoko.begininggecco.spider.conversion;

public class StringTypeHandle implements TypeHandle<String> {

	@Override
	public String getValue(Object src) {
		return src.toString();
	}

}
