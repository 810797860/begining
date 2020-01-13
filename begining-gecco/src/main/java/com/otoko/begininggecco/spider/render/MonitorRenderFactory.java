package com.otoko.begininggecco.spider.render;

import com.otoko.begininggecco.monitor.RenderMointorIntercetor;
import com.otoko.begininggecco.spider.render.html.HtmlRender;
import com.otoko.begininggecco.spider.render.json.JsonRender;
import net.sf.cglib.proxy.Enhancer;
import org.reflections.Reflections;

public class MonitorRenderFactory extends RenderFactory {

	public MonitorRenderFactory(Reflections reflections) {
		super(reflections);
	}

	@Override
	public HtmlRender createHtmlRender() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(HtmlRender.class);
		enhancer.setCallback(new RenderMointorIntercetor());
		return (HtmlRender)enhancer.create();
	}

	@Override
	public JsonRender createJsonRender() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(JsonRender.class);
		enhancer.setCallback(new RenderMointorIntercetor());
		return (JsonRender)enhancer.create();
	}
	
	
	
}
