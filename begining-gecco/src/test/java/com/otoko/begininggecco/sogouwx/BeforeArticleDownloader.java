package com.otoko.begininggecco.sogouwx;

import com.otoko.begininggecco.annotation.GeccoClass;
import com.otoko.begininggecco.downloader.BeforeDownload;
import com.otoko.begininggecco.request.HttpRequest;

@GeccoClass(Article.class)
public class BeforeArticleDownloader implements BeforeDownload {

	@Override
	public void process(HttpRequest request) {
		request.clearCookie();
		request.clearHeader();
	}

}
