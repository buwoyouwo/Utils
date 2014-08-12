package com.buwoyouwo.util.vector;

public class Float2 {
	float x;
	float y;
	
	public float getX() { return x; }
	public void setX(float x) { this.x = x; }
	public float getY() { return y; }
	public void setY(float y) { this.y = y; }
	
	public void copy(Float2 origin){
		x = origin.getX();
		y = origin.getY();
	}
	
	public Float2 clone(){
		Float2 dest = new Float2();
		dest.setX(x);
		dest.setY(y);
		
		return dest;
	}
	
	public static double distance(Float2 point1, Float2 point2){
		double diffX = point2.getX() - point1.getX();
		double diffY = point2.getY() - point1.getY();
		
		return Math.sqrt(diffX * diffX + diffY + diffY);
	}
}
