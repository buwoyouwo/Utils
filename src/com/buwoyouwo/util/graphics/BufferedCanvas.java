package com.buwoyouwo.util.graphics;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

/**
 * 带双缓冲功能的Canvas
 * @author buwoyouwo
 *
 */
public class BufferedCanvas extends Canvas {
	
	Image bufferedImage;
	int bufferedWidth;
	int bufferedHeight;
	
	@Override
	public void update(Graphics g){
		bufferedImage = getBufferImage();
		
		Graphics bufferedGraphics = bufferedImage.getGraphics();
		bufferedGraphics.clearRect(0, 0, bufferedWidth,bufferedHeight);
		this.paint(bufferedGraphics);
		bufferedGraphics.dispose();
		
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(bufferedImage, 0, 0, null);
	}
	
	/**
	 * 画布尺寸是否发生了变化
	 * @return
	 */
	private boolean sizeChanged(){
		if(bufferedWidth != getWidth() || bufferedHeight != getHeight()){
			bufferedWidth = getWidth();
			bufferedHeight = getHeight();
			return true;
		}
		return false;
	}
	
	private Image getBufferImage(){
		if(bufferedImage == null || sizeChanged()){
			bufferedImage = createImage(getWidth(), getHeight());
		}
		return bufferedImage;
	}

}
