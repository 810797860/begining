package com.otoko.begininggecco.csdn;

import com.otoko.begininggecco.GeccoEngine;
import com.otoko.begininggecco.annotation.Gecco;
import com.otoko.begininggecco.annotation.HtmlField;
import com.otoko.begininggecco.request.HttpGetRequest;
import com.otoko.begininggecco.spider.HtmlBean;

import java.util.List;

@Gecco(matchUrl = "https://blog.csdn.net/weixin_41835916/article/details/81413498", pipelines = "consolePipeline")
public class CsdnHref implements HtmlBean{

    private static final long serialVersionUID = -1L;

    //标题
    @HtmlField(cssPath = "#content_views")
    private List<CsdnLeaf> csdnLeaves;

    public List<CsdnLeaf> getCsdnLeaves() {
        return csdnLeaves;
    }

    public void setCsdnLeaves(List<CsdnLeaf> csdnLeaves) {
        this.csdnLeaves = csdnLeaves;
    }

    public static void main(String[] args){
        HttpGetRequest httpGetRequest = new HttpGetRequest("https://blog.csdn.net/weixin_41835916/article/details/81413498");
        httpGetRequest.setCharset("GBK");
        GeccoEngine.create()
                .classpath("com.otoko.begininggecco.csdn")
                .start(httpGetRequest)
                .thread(1)
                .interval(2000)
                .run();
    }
}
