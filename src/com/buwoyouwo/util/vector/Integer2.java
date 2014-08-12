package com.buwoyouwo.util.vector;

public class Integer2 {
	int x;
	int y;
	
	public Integer2(){	}
	
	public Integer2(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	
	
	public void copy(Integer2 source){
		x = source.x;
		y = source.y;
	}
	
	public Integer2 clone(){
		Integer2 dest = new Integer2();
		dest.setX(x);
		dest.setY(y);
		
		return dest;
	}
	
	public Integer2 plus(Integer2 addend){
		copy(plus(this,addend));
		return this;
	}
	
	public Integer2 minus(Integer2 subtractor){
		copy(minus(this, subtractor));
		return this;
	}
	
	public Integer2 multiply(double multiplier){
		copy(multiply(this, multiplier));
		return this;
	}
	
	public Integer2 negate(){
		copy(negate(this));
		return this;
	}
	
	public Integer2 rotate(Integer2 center, double theta){
		copy(rotate(center, this, theta));
		return this;
	}
	
	public static Integer2 plus(Integer2 vector1, Integer2 vector2){
		return new Integer2(vector1.x + vector2.x, vector1.y + vector2.y);
	}
	
	public static Integer2 minus(Integer2 subtrahend, Integer2 subtractor){
		return new Integer2(subtrahend.x - subtractor.x, subtrahend.y - subtractor.y);
	}
	
	public static Integer2 multiply(Integer2 multiplicand, double multiplier){
		return new Integer2((int)(multiplicand.x * multiplier), (int)(multiplicand.y * multiplier));
	}
	
	public static Integer2 negate(Integer2 origin){
		return new Integer2(-origin.x, -origin.y);
	}
	
	/**
	 * Distance between two point
	 * @param point1
	 * @param point2
	 * @return
	 */
	public static double distance(Integer2 point1, Integer2 point2){
		double diffX = point2.getX() - point1.getX();
		double diffY = point2.getY() - point1.getY();
		
		return Math.sqrt(diffX * diffX + diffY * diffY);
	}
	
	public static Integer2 rotate(Integer2 center, Integer2 point, double theta){
		double diffX = point.x - center.x;
		double diffY = point.y - center.y;
		double r = Math.sqrt(diffX * diffX + diffY * diffY);
		double alpha = Math.atan2(diffY, diffX);
		double phy = alpha + theta;
		
		Integer2 newPoint = new Integer2();
		newPoint.y = center.y + (int) (r * Math.sin(phy));
		newPoint.x = center.x + (int) (r * Math.cos(phy));
		
		return newPoint;
	}
	
}
