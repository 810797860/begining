package com.otoko.begininggecco.downloader;

import com.otoko.begininggecco.request.HttpRequest;

public interface BeforeDownload {
	
	public void process(HttpRequest request);

}
