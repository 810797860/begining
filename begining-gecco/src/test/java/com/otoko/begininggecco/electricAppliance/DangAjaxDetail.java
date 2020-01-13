package com.otoko.begininggecco.electricAppliance;

import com.otoko.begininggecco.annotation.HtmlField;
import com.otoko.begininggecco.spider.HtmlBean;

public class DangAjaxDetail implements HtmlBean{

    private static final long serialVersionUID = -1L;

    @HtmlField(cssPath = "body > div.descrip")
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
