package com.buwoyouwo.util.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.buwoyouwo.util.sys.IStopwatch;
import com.buwoyouwo.util.sys.Stopwatch;

public class TestStopwatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IStopwatch watch = new Stopwatch();
		
		WatchThread wt = new WatchThread(watch);
		Thread t = new Thread(wt);
		t.start();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		boolean running = true;
		while(running){
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String[] cmds = line.split(" ");
			
			switch(cmds[0]){
			case "b":
				watch.start();
				break;
			case "p":
				watch.pause();
				break;
			case "s":
				watch.switchRunning();
				break;
			case "rs":
				if(cmds.length >= 2){
					long time = Long.parseLong(cmds[1]);
					watch.reset(time);
				} else{
					watch.reset();
				}
				break;
			case "rb":
				if(cmds.length >= 2){
					long time = Long.parseLong(cmds[1]);
					watch.restart(time);
				} else{
					watch.restart();
				}
				break;
			case "q":
				running = false;
				System.out.println("QUIT");
				System.out.println();
				break;
			default:
				break;
			}
		}
		wt.running = false;
		wt = null;
		t = null;
		
	}
	
	public static class WatchThread implements Runnable{
		IStopwatch watch;
		public volatile boolean running = true;
		
		public WatchThread(IStopwatch watch){
			this.watch = watch;
		}

		@Override
		public void run() {
			while(running){
				String ifRunningString = watch.ifRunning()?"[r]":"[p]";
				System.out.print("\r\b" + ifRunningString +"\t"+watch.currentTimeMillis()+"\t\t");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
