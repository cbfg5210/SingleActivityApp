package com.hawk.saa;

import android.app.Application;

public class App extends Application {
	private static App mInstance;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstance=this;
	}
	
	public static App getInstance(){
		return mInstance;
	}
}
