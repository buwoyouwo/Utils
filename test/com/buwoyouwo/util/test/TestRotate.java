package com.buwoyouwo.util.test;

import com.buwoyouwo.util.vector.Integer2;

public class TestRotate {
	
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) {
		Integer2 point = new Integer2(368,450);
		Integer2 center = new Integer2(350,450);
		
		point.rotate(center, 0.4);
	}
}
