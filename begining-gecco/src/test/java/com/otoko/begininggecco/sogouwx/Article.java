package com.otoko.begininggecco.sogouwx;

import com.otoko.begininggecco.annotation.Gecco;
import com.otoko.begininggecco.annotation.HtmlField;
import com.otoko.begininggecco.annotation.Text;
import com.otoko.begininggecco.spider.HtmlBean;


@Gecco(matchUrl="http://mp.weixin.qq.com/s?{params}", pipelines="consolePipeline")
public class Article implements HtmlBean {

	private static final long serialVersionUID = 5310736056776105882L;

	@Text(own=false)
	@HtmlField(cssPath="body")
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
