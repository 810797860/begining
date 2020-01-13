package com.otoko.begininggecco.bookLibrary;

import com.otoko.begininggecco.annotation.HtmlField;
import com.otoko.begininggecco.spider.HrefBean;
import com.otoko.begininggecco.spider.HtmlBean;

import java.util.List;

public class DangList implements HtmlBean{

    private static final long serialVersionUID = -1L;

    @HtmlField(cssPath = "li p a")
    private List<HrefBean> hrefBeanList;

    public List<HrefBean> getHrefBeanList() {
        return hrefBeanList;
    }

    public void setHrefBeanList(List<HrefBean> hrefBeanList) {
        this.hrefBeanList = hrefBeanList;
    }
}
