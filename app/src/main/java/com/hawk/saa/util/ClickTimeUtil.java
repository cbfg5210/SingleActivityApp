package com.hawk.saa.util;

public class ClickTimeUtil {
	private static long temptime = 0;//点击记录时间

    public static boolean isRetryTooFast(int millis){
		long nowTime=System.currentTimeMillis();
    	if(nowTime - temptime > millis){
    		temptime = nowTime;
			return false;
		}
    	return true;
    }
}
