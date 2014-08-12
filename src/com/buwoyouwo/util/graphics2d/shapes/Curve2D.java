package com.buwoyouwo.util.graphics2d.shapes;

import java.util.ArrayList;
import java.util.List;

import com.buwoyouwo.util.vector.Integer2;

public class Curve2D {
	/**
	 * Generate Bezier curve nodes using control points and step
	 * @param controlPoints
	 * @param step	A value between (0,1)
	 * @return	Bezier curve nodes
	 */
	static public Integer2[] bezier(Integer2[] controlPoints, float step){
		int n = controlPoints.length;
		int i;
		int r;
		float u;
		
		List<Integer2>bezierPoints = new ArrayList<Integer2>();
		for(u = 0; u <= 1; u += step){
			Integer2[] p = new Integer2[n];
			for(i = 0; i < n; i++){
				p[i] = controlPoints[i].clone();
			}
			
			for(r = 1; r < n; r++){
				for(i = 0; i < n-r; i++){
					p[i].copy( Integer2.plus(Integer2.multiply(p[i], 1-u), Integer2.multiply(p[i+1], u)) );
				}
			}
			
			bezierPoints.add(p[0]);
			
			
		}
		Integer2[] res = new Integer2[bezierPoints.size()];
		
		return bezierPoints.toArray(res);
	}
	
}
