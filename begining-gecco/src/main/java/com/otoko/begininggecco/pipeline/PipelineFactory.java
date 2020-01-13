package com.otoko.begininggecco.pipeline;

import com.otoko.begininggecco.spider.SpiderBean;

public interface PipelineFactory {
	
	public Pipeline<? extends SpiderBean> getPipeline(String name);

}
