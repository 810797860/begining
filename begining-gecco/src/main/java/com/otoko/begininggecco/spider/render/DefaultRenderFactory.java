package com.otoko.begininggecco.spider.render;

import com.otoko.begininggecco.spider.render.html.HtmlRender;
import com.otoko.begininggecco.spider.render.json.JsonRender;
import org.reflections.Reflections;

public class DefaultRenderFactory extends RenderFactory {
	
	public DefaultRenderFactory(Reflections reflections) {
		super(reflections);
	}

	public HtmlRender createHtmlRender() {
		return new HtmlRender();
	}
	
	public JsonRender createJsonRender() {
		return new JsonRender();
	}
	
}
