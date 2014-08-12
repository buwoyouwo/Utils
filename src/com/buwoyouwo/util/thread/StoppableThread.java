package com.buwoyouwo.util.thread;

public abstract class StoppableThread extends Thread {
	private boolean running = true;
	
	public void run(){
		while(running){
			doInLoop();
		}
	}
	
	public void terminal(){
		running = false;
	}
	
	public boolean ifRunning(){
		return running;
	}
	
	public abstract void doInLoop();
}
