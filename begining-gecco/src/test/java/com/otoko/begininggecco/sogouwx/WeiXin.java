package com.otoko.begininggecco.sogouwx;

import com.otoko.begininggecco.annotation.Href;
import com.otoko.begininggecco.annotation.HtmlField;
import com.otoko.begininggecco.annotation.Text;
import com.otoko.begininggecco.spider.HtmlBean;

public class WeiXin implements HtmlBean {

	private static final long serialVersionUID = 5821685160506822729L;

	@Text
	@HtmlField(cssPath=".txt-box h4 a")
	private String title;
	
	@Text
	@HtmlField(cssPath=".txt-box p")
	private String text;
	
	@Href(click=true)
	@HtmlField(cssPath=".txt-box h4 a")
	private String detailUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	
}
