package com.buwoyouwo.util.graphics;

/**
 * 帧线程监听器
 * @author 不我有我
 *
 */
public interface IFrameThreadListener {
	
	public void onStart();          //线程开启时调用
	public void onTerminal();       //线程终止时调用

	public void beforeBussiness();	//一帧业务执行前调用
	public void afterBussiness();	//一帧业务执行后调用
	
}
