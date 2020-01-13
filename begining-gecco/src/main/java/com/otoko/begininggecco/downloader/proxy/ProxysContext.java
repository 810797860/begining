package com.otoko.begininggecco.downloader.proxy;

import com.otoko.begininggecco.spider.Spider;
import com.otoko.begininggecco.spider.SpiderThreadLocal;

public class ProxysContext {
	
	public static Proxys get() {
		Spider spider = SpiderThreadLocal.get();
		if(spider == null) {
			return null;
		}
		return spider.getEngine().getProxysLoader();
	}
	
	public static boolean isEnableProxy() {
		Spider spider = SpiderThreadLocal.get();
		if(spider == null) {
			return false;
		}
		return spider.getEngine().isProxy();
	}

}
