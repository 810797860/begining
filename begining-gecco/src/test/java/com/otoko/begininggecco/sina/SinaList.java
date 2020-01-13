package com.otoko.begininggecco.sina;

import com.otoko.begininggecco.GeccoEngine;
import com.otoko.begininggecco.annotation.Gecco;
import com.otoko.begininggecco.annotation.HtmlField;
import com.otoko.begininggecco.annotation.PipelineName;
import com.otoko.begininggecco.annotation.Text;
import com.otoko.begininggecco.pipeline.Pipeline;
import com.otoko.begininggecco.spider.HtmlBean;

import java.util.List;

@PipelineName("SinaList")
@Gecco(matchUrl="http://news.sina.com.cn/china/", pipelines="SinaList")
public class SinaList implements HtmlBean, Pipeline<SinaList> {
	
	private static final long serialVersionUID = 6683895914723213684L;
	
	@HtmlField(cssPath="#subShowContent1_static .news-item h2 a")
	private List<Item> items;
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public static class Item implements HtmlBean {
		
		private static final long serialVersionUID = 5243013123370386328L;

		@HtmlField(cssPath="a")
		private String url;
		
		@Text
		@HtmlField(cssPath="a")
		private String tag;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}
		
	}

	@Override
	public void process(SinaList bean) {
		System.out.println(bean.getItems().size());
	}

	public static void main(String[] args) {
		GeccoEngine.create()
		.classpath("com.otoko.begininggecco.demo.sina")
		.start("http://news.sina.com.cn/china/")
		.run();
	}
}
