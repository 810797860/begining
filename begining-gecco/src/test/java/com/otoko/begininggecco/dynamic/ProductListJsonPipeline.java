package com.otoko.begininggecco.dynamic;

import com.alibaba.fastjson.JSONObject;
import com.otoko.begininggecco.annotation.PipelineName;
import com.otoko.begininggecco.pipeline.JsonPipeline;
import com.otoko.begininggecco.request.HttpGetRequest;
import com.otoko.begininggecco.request.HttpRequest;
import com.otoko.begininggecco.scheduler.SchedulerContext;
import org.apache.commons.lang3.StringUtils;

@PipelineName("productListJsonPipeline")
public class ProductListJsonPipeline extends JsonPipeline {

	@Override
	public void process(JSONObject productList) {
		HttpRequest currRequest = HttpGetRequest.fromJson(productList.getJSONObject("request"));
		//下一页继续抓取
		int currPage = productList.getIntValue("currPage");
		int nextPage = currPage + 1;
		int totalPage = productList.getIntValue("totalPage");
		if(nextPage <= totalPage) {
			String nextUrl = "";
			String currUrl = currRequest.getUrl();
			if(currUrl.indexOf("page=") != -1) {
				nextUrl = StringUtils.replaceOnce(currUrl, "page=" + currPage, "page=" + nextPage);
			} else {
				nextUrl = currUrl + "&" + "page=" + nextPage;
			}
			SchedulerContext.into(currRequest.subRequest(nextUrl));
		}
	}

}
