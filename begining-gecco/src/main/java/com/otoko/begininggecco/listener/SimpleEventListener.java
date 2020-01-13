package com.otoko.begininggecco.listener;

import com.otoko.begininggecco.GeccoEngine;

/**
 * 简单的引擎时间兼容实现类，可以继承该类覆盖需要的方法
 * 
 * @author LiuJunGuang
 */
public abstract class SimpleEventListener implements EventListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otoko.begininggecco.listener.EventListener#onStart(com.otoko.begininggecco.GeccoEngine)
	 */
	@Override
	public void onStart(GeccoEngine ge) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otoko.begininggecco.listener.EventListener#onPause(com.otoko.begininggecco.GeccoEngine)
	 */
	@Override
	public void onPause(GeccoEngine ge) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otoko.begininggecco.listener.EventListener#onRestart(com.otoko.begininggecco.GeccoEngine)
	 */
	@Override
	public void onRestart(GeccoEngine ge) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otoko.begininggecco.listener.EventListener#onStop(com.otoko.begininggecco.GeccoEngine)
	 */
	@Override
	public void onStop(GeccoEngine ge) {
	}

}
