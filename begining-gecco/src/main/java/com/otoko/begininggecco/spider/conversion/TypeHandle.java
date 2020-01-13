package com.otoko.begininggecco.spider.conversion;

public interface TypeHandle<T> {
	
	public T getValue(Object src) throws Exception;

}
