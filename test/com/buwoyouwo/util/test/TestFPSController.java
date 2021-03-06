package com.buwoyouwo.util.test;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.buwoyouwo.util.graphics.BufferedCanvas;
import com.buwoyouwo.util.graphics.FPSController;
import com.buwoyouwo.util.graphics.IFPSController;
import com.buwoyouwo.util.graphics.IFPSListener;
import com.buwoyouwo.util.graphics2d.shapes.Curve2D;
import com.buwoyouwo.util.sys.IStopwatch;
import com.buwoyouwo.util.sys.Stopwatch;
import com.buwoyouwo.util.vector.Integer2;

public class TestFPSController extends JFrame 
	implements ActionListener, IFPSListener
	{
	boolean fpsEnable = true;
	
	JButton fpsCtrl = new JButton("Disable FPS Control");
	JLabel fpsDisp = new JLabel("FPS");
	JPanel bar = new JPanel();
	Canvas canvas = new DrawCanvas();
	
	IStopwatch watch = new Stopwatch();
	IFPSController fpsController = new FPSController(watch);
	
	public TestFPSController(){
		watch.restart();
		fpsController.enable();
		fpsController.setTargetFPS(30);
		fpsController.addListener(this);
		
		setLayout(new BorderLayout());
		//Container bar = getContentPane();
		//bar.setLayout(new FlowLayout());
		fpsCtrl.addActionListener(this);
		canvas.setSize(this.getSize());
		
		bar.setLayout(new GridLayout(1,2));
		bar.add(fpsCtrl);
		bar.add(fpsDisp);
		
		add(BorderLayout.NORTH, bar);
		add(BorderLayout.CENTER,canvas);
		
		new Thread(new Runnable(){
			public void run(){
				while(true){
					
					SwingUtilities.invokeLater(new Runnable(){
						public void run(){
							//fpsDisp.setText(""+fpsController.getFPSNow());
							canvas.repaint();
							//canvas.validate();
							//repaint();
							//validate();
						}
					});
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					fpsController.onFrameEnd();
				}
			}
		}).start();
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final TestFPSController frame = new TestFPSController();
		SwingConsole.run(frame, 1280, 600);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(fpsCtrl)){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					fpsEnable = !fpsEnable;
					
					if(fpsEnable){
						fpsCtrl.setText("Disable FPS Control");
						fpsController.enable();
					} else{
						fpsCtrl.setText("Enable FPS Control");
						fpsController.disable();
					}
				}
			});
		}
	}

	@Override
	public void onEnable() {
		System.out.println("[fps listner] fps enable!");
	}

	@Override
	public void onDisable() {
		System.out.println("[fps listener] fps disable!");
	}

	@Override
	public void onFPSUpdate() {
		System.out.println("[fps listener] fps update :" + fpsController.getFPSNow());
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				fpsDisp.setText("" + fpsController.getFPSNow());
			}
		});
	}

	@Override
	public void beforeSleep() {
//		System.out.println("[fps listener] before sleep");
	}

	@Override
	public void afterSleep() {
//		System.out.println("[fps listener] after sleep");
	}
	
	

}

class SwingConsole{
	public static void 
	run(final JFrame frame, final int width, final int height){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				frame.setTitle(frame.getClass().getSimpleName());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(width, height);
				frame.setVisible(true);
			}
		});
	}
}

class DrawCanvas extends BufferedCanvas{
	
	int x = 0;
	int y = 0;
	int delta = 2;
	int deltaX = delta;
	int deltaY = -delta;
	int l = 40;
	
	Graphics graphics;
    @Override  
    public void paint(Graphics g) {  
        graphics = g;
        Dimension size = getSize();  
//        System.out.println(size.width);  
//        System.out.println(size.height);  
//          
//        int x = 0;  
//        int y = 0;  
//        int i = 0;  
//        g.setColor(Color.blue);  
//        g.fillRect(x,y,size.width,size.height);  
//        while(x < size.width && y < size.height) {  
//            g.setColor(i%2==0? Color.red : Color.white);  
//            g.fillOval(x,y,size.width-(2*x),size.height-(2*y));  
//            x+=10; y+=10; i++;  
//        }  
        
        x += deltaX;
        y += deltaY;
        if(x > size.width){
        	deltaX = -delta;
        }
        else if(x < 0){
        	deltaX = delta;
        }
        if(y > size.height){
        	deltaY = -delta;
        } else if(y < 0){
        	deltaY = delta;
        }
        g.setColor(Color.gray);
        //g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.blue);
        g.fillRect(x, y, l, l);
        
        Integer2[] line = generateCurve(size.width, size.height);
        for(int i = 1; i < line.length; i++){
        	g.drawLine(line[i-1].getX(), line[i-1].getY(), line[i].getX(), line[i].getY());
        }
        
        if(controlPoints != null){
        	for(int i = 1; i < controlPoints.length; i++){
        		g.drawOval(controlPoints[i].getX(), controlPoints[i].getY(), 20, 20);
        	}
        }
        
        //super.paint(g);
    }  
    
    Integer2[] controlPoints;
    private Integer2[] generateCurve(int xRange, int yRange){
    	int xStage;
		int yStage;
    	if(controlPoints == null){
    		controlPoints = new Integer2[10];
    		xStage = xRange / (controlPoints.length + 1);
    		yStage = yRange / (controlPoints.length + 1);
    		Random random = new Random();
    		for(int  i = 0; i < controlPoints.length; i++){
    			int x = random.nextInt(2*xStage) + i*xStage;
    			int y = random.nextInt(2*yStage) + i*yStage;
    			controlPoints[i] = new Integer2(x,y);
    		}
    	}
    	xStage = xRange / (controlPoints.length + 1);
		yStage = yRange / (controlPoints.length + 1);
    	Random rdm = new Random();
    	for(int i = 0; i < controlPoints.length; i++ ){
    		controlPoints[i].setX(controlPoints[i].getX() + rdm.nextInt(xStage/10) - xStage/20);
    		controlPoints[i].setY(controlPoints[i].getY() + rdm.nextInt(yStage/10) - yStage/20);
    	}
    	
    	return Curve2D.bezier(controlPoints,0.1f);
    }
    
    
//    public void repaint(){
//    	if(graphics != null){
//    		this.paint(graphics);
//    	}
//    	super.repaint();
//    }
}

///:~
