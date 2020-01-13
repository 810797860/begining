package com.otoko.begininggecco.pipeline;

import com.otoko.begininggecco.spider.SpiderBean;

public interface Pipeline<T extends SpiderBean> {

	public void process(T bean);

}
