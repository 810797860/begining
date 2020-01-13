package com.otoko.begininggecco.pipeline;

import com.alibaba.fastjson.JSON;
import com.otoko.begininggecco.annotation.PipelineName;
import com.otoko.begininggecco.spider.SpiderBean;

@PipelineName("consolePipeline")
public class ConsolePipeline implements Pipeline<SpiderBean> {

	@Override
	public void process(SpiderBean bean) {
		System.out.println(JSON.toJSONString(bean));
	}

}
