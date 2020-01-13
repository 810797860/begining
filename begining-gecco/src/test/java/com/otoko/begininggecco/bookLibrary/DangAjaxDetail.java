package com.otoko.begininggecco.bookLibrary;

import com.otoko.begininggecco.annotation.JSONPath;
import com.otoko.begininggecco.spider.JsonBean;

public class DangAjaxDetail implements JsonBean{

    private static final long serialVersionUID = -1L;

    @JSONPath("$.data.html")
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
