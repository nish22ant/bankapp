package com.bankapp;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class EhcacheEventListener implements CacheEventListener<Object, Object> {

	@Override
	public void onEvent(CacheEvent event) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>.Working");
		System.out.println("Event Captured: " + event.getKey() + " " + event.getType() + " " + event.getNewValue());
		
	}

}
