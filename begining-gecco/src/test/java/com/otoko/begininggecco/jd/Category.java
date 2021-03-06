package com.otoko.begininggecco.jd;

import com.otoko.begininggecco.annotation.HtmlField;
import com.otoko.begininggecco.annotation.Text;
import com.otoko.begininggecco.spider.HrefBean;
import com.otoko.begininggecco.spider.HtmlBean;

import java.util.List;

public class Category implements HtmlBean {

	private static final long serialVersionUID = 3018760488621382659L;

	@Text
	@HtmlField(cssPath="dt a")
	private String parentName;
	
	@HtmlField(cssPath="dd a")
	private List<HrefBean> categorys;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<HrefBean> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<HrefBean> categorys) {
		this.categorys = categorys;
	}
	
}
