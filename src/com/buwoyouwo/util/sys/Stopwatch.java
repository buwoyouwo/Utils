package com.buwoyouwo.util.sys;

public class Stopwatch implements IStopwatch {
	
	boolean ifRunning = false;	//是否正在计时中
	private long startTime;		//开始计时时的系统时间
	private long pauseInverse;	//计时中累计暂停的时间间隔
	private long pauseTime;		//上次暂停时的系统时间
	
	public Stopwatch(){
		init();
	}

	@Override
	public long currentTimeMillis() {
		long currentSystime;
		if(ifRunning){
			currentSystime = System.currentTimeMillis();
		} else{
			currentSystime = pauseTime;
		}
		
		return currentSystime - startTime - pauseInverse;
	}

	@Override
	public boolean ifRunning() {
		return ifRunning;
	}

	@Override
	public void start() {
		ifRunning = true;
		pauseInverse += System.currentTimeMillis() - pauseTime;
	}

	@Override
	public void pause() {
		ifRunning = false;
		pauseTime = System.currentTimeMillis();
	}

	@Override
	public boolean switchRunning() {
		if(ifRunning){
			pause();
		} else{
			start();
		}
		return ifRunning();
	}

	@Override
	public void reset() {
		ifRunning = false;
		init();
	}

	@Override
	public void reset(long time) {
		ifRunning = false;
		init(time);
	}

	@Override
	public void restart() {
		ifRunning = true;
		init();
	}

	@Override
	public void restart(long time) {
		ifRunning = true;
		init(time);
	}
	
	private void init(){
		startTime = System.currentTimeMillis();
		pauseTime = startTime;
		pauseInverse = 0;
	}
	
	public void init(long time){
		pauseTime = System.currentTimeMillis();
		startTime = pauseTime - time;
		pauseInverse = 0;
	}
	

}
