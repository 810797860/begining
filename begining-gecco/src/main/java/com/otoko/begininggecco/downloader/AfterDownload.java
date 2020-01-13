package com.otoko.begininggecco.downloader;

import com.otoko.begininggecco.request.HttpRequest;
import com.otoko.begininggecco.response.HttpResponse;

public interface AfterDownload {
	
	public void process(HttpRequest request, HttpResponse response);

}
