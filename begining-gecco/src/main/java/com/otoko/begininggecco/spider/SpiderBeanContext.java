package com.otoko.begininggecco.spider;

import com.otoko.begininggecco.downloader.AfterDownload;
import com.otoko.begininggecco.downloader.BeforeDownload;
import com.otoko.begininggecco.downloader.Downloader;
import com.otoko.begininggecco.pipeline.Pipeline;
import com.otoko.begininggecco.spider.render.Render;

import java.util.List;

/**
 * 渲染bean的上下文对象。 包括下载前的自定义操作beforeDownload,下载后的自定义操作afterDownload。 使用的哪种渲染器渲染bean，目前支持html、json、xml。
 * 渲染完成后通过管道过滤器进行bean的进步一部清洗和整理。
 * 
 * @author huchengyi
 *
 */
public class SpiderBeanContext {

	private Render render;

	private Downloader downloader;

	private int timeout;

	private BeforeDownload beforeDownload;

	private AfterDownload afterDownload;

	@SuppressWarnings({ "rawtypes" })
	private List<Pipeline> pipelines;

	public Render getRender() {
		return render;
	}

	public void setRender(Render render) {
		this.render = render;
	}

	public BeforeDownload getBeforeDownload() {
		return beforeDownload;
	}

	public void setBeforeDownload(BeforeDownload beforeDownload) {
		this.beforeDownload = beforeDownload;
	}

	public AfterDownload getAfterDownload() {
		return afterDownload;
	}

	public void setAfterDownload(AfterDownload afterDownload) {
		this.afterDownload = afterDownload;
	}

	@SuppressWarnings({ "rawtypes" })
	public List<Pipeline> getPipelines() {
		return pipelines;
	}

	@SuppressWarnings({ "rawtypes" })
	public void setPipelines(List<Pipeline> pipelines) {
		this.pipelines = pipelines;
	}

	public Downloader getDownloader() {
		return downloader;
	}

	public void setDownloader(Downloader downloader) {
		this.downloader = downloader;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}