package com.buwoyouwo.util.graphics;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.buwoyouwo.util.sys.IStopwatch;
import com.buwoyouwo.util.sys.Stopwatch;

/**
 * FPS控制器
 * 
 * @author buwoyouwo
 *
 */
public class FPSController implements IFPSController {
    boolean ifEnabled = false;
    int targetFPS;				//目标fps
    int targetMSPF;				//目标mspf,fps的倒数
    int inverse = 1000;				//FPS更新时间间隔
    

    long beginTime = -1;        //一帧内容开始时间
    long endTime = 0;           //一帧内容结束时间
    long lastStatistics;        //上次统计时间
    int frameCount = 0;			//帧数计数
    float fpsNow;               //上次统计到的fps
    
    List<IFPSListener> listeners;
    IStopwatch watch;
    

    public IStopwatch getWatch() {
		return watch;
	}

	public void setWatch(IStopwatch watch) {
		this.watch = watch;
	}

	public FPSController() {
        this(new Stopwatch());
    }
    
    public FPSController(IStopwatch watch){
    	this.watch = watch;
    	setTargetFPS(60);
    	listeners = new ArrayList<IFPSListener>();
        reset();
    }
    
    

    @Override
    public void enable() {
        if(ifEnabled)	//本来就是启用状态
        	return ;
        
        ifEnabled = true;
        reset();
//        watch.reset();
        this.callListeners("onEnable");
    }

    @Override
    public void disable() {
        if(!ifEnabled)	//本来就是停用状态
        	return ;
        
        ifEnabled = false;
//        watch.restart();
        this.callListeners("onDisable");
        
    }

    @Override
    public boolean getStatus() {
        return ifEnabled;
    }

    @Override
    public void setTargetFPS(int fps) {
        targetFPS = fps;
        targetMSPF = 1000 / targetFPS;
    }

    @Override
    public int getTargetFPS() {
        return targetFPS;
    }

    @Override
    public void setUpdateInverse(int updateInverse) {
        inverse = updateInverse;
    }

    @Override
    public int getUpdateInverse() {
        return inverse;
    }
    
    

    @Override
    public void onFrameEnd() {
    	//一帧结束时间
        endTime = watch.currentTimeMillis();

        if (beginTime < 0) {
            //第一帧
        	if(ifEnabled){
        		callListeners("beforeSleep");
	            try {
	                Thread.sleep(targetMSPF);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            callListeners("afterSleep");
        	}
            lastStatistics = endTime;
        } else {
    	if(ifEnabled){
            long sleepTime = targetMSPF - (endTime - beginTime);
            if(sleepTime > 0) {
            	callListeners("beforeSleep");
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                callListeners("afterSleep");
            }
    	}
        }

        //一帧开始
        beginTime = watch.currentTimeMillis();
        frameCount++;

        long inverseNow = beginTime - lastStatistics;
        if(inverseNow > inverse ){
            //应当更新fps
            lastStatistics = beginTime;
            fpsNow =  frameCount * 1000.0f / inverseNow;
            frameCount = 0;
//            System.out.println("" +ifEnabled + "\t" + fpsNow);
            callListeners("onFPSUpdate");
        }
    }

    @Override
    public float getFPSNow() {
        return fpsNow;
    }

	@Override
	public int addListener(IFPSListener listener) {
		listeners.add(listener);
		return listeners.size() - 1;
	}

	@Override
	public IFPSListener removeListener(IFPSListener listener) {
		boolean success = listeners.remove(listener);
		return (success?listener:null);
	}

	@Override
	public IFPSListener removeListener(int id) {
		return listeners.remove(id);
	}
	
	private void reset(){
    	beginTime = -1;
    	endTime = 0;
    	lastStatistics = 0;
    	frameCount = 0;
    }
	
	private void callListeners(String methodName){
		Method method;
		try {
			method = IFPSListener.class.getDeclaredMethod(methodName);
			for(IFPSListener listener : listeners){
				method.invoke(listener);
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
