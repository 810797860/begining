package com.otoko.begininggecco.digitalSquare;

import com.otoko.begininggecco.GeccoEngine;
import com.otoko.begininggecco.annotation.Gecco;
import com.otoko.begininggecco.annotation.HtmlField;
import com.otoko.begininggecco.annotation.Request;
import com.otoko.begininggecco.request.HttpGetRequest;
import com.otoko.begininggecco.request.HttpRequest;
import com.otoko.begininggecco.spider.HtmlBean;

import java.util.List;

@Gecco(matchUrl = "http://category.dangdang.com/cid4001136.html", pipelines = {"consolePipeline", "digitalSquareListPipeline"})
public class DangPage implements HtmlBean{

    private static final long serialVersionUID = -1L;

    @Request
    private HttpRequest request;

    //列表页
    @HtmlField(cssPath = "#bd > div")
    private List<DangList> dangLists;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public List<DangList> getDangLists() {
        return dangLists;
    }

    public void setDangLists(List<DangList> dangLists) {
        this.dangLists = dangLists;
    }

    public static void main(String[] args){

        HttpGetRequest httpGetRequest = new HttpGetRequest("http://category.dangdang.com/cid4001136.html");
        httpGetRequest.setCharset("GBK");
        GeccoEngine.create()
                .classpath("com.otoko.begininggecco.digitalSquare")
                .start(httpGetRequest)
                .thread(1)
                .interval(4000)
                .run();

        GeccoEngine.create()
                .classpath("com.otoko.begininggecco.digitalSquare")
                .start(DangListPipeline.httpRequests)
                .thread(1)
                .interval(4000)
                .run();
    }
}