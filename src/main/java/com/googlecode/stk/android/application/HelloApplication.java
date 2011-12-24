package com.googlecode.stk.android.application;

import java.util.List;

import com.google.inject.Module;
import com.googlecode.stk.android.service.HelloService;
import com.googlecode.stk.android.service.HelloServiceImpl;

import roboguice.application.RoboApplication;
import roboguice.config.AbstractAndroidModule;


public class HelloApplication extends RoboApplication {

	@Override
	protected void addApplicationModules(List<Module> modules) {
		modules.add(new HelloModule());
	}
	
	static class HelloModule extends AbstractAndroidModule {

		@Override
		protected void configure() {
			
			bind(HelloService.class).to(HelloServiceImpl.class);
		}
		
	}
	
}
