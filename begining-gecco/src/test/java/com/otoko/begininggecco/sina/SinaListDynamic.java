package com.otoko.begininggecco.sina;

import com.alibaba.fastjson.JSONObject;
import com.otoko.begininggecco.GeccoEngine;
import com.otoko.begininggecco.annotation.PipelineName;
import com.otoko.begininggecco.dynamic.DynamicGecco;
import com.otoko.begininggecco.pipeline.JsonPipeline;

@PipelineName("sinaListDynamicPipeline")
public class SinaListDynamic extends JsonPipeline {
	
	@Override
	public void process(JSONObject jo) {
		System.out.println(jo);		
	}
	
	public static void main(String[] args) {
		
		Class<?> item = DynamicGecco.html()
				.stringField("url").csspath("a").build()
				.stringField("tag").csspath("a").text().build()
				.register();
		
		DynamicGecco.html()
		.gecco("http://news.sina.com.cn/china/", "sinaListDynamicPipeline")
		.listField("items", item).csspath("#subShowContent1_static .news-item h2 a").build()
		.register();
		
		GeccoEngine.create()
		.classpath("com.otoko.begininggecco.demo.sina")
		.start("http://news.sina.com.cn/china/")
		.run();
	}

}