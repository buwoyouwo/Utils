package com.buwoyouwo.util.graphics;

/**
 * 接口 FPS控制器
 * @author 不我有我
 *
 */
public interface IFPSController {
    public void enable();			//启用FPS控制
    public void disable();          //停用FPS控制
    public boolean getStatus();     //查看当前是否启用FPS控制

    public void setTargetFPS(int fps);					//设置目标FPS
    public int getTargetFPS();							//获取目标FPS
    public void setUpdateInverse(int updateInverse);	//设置帧率统计更新时间
    public int getUpdateInverse();						//获取帧率统计更新时间

    public void onFrameEnd();			//在一帧绘制结束时运行
    public float getFPSNow();			//获取当前FPS
    
    /**
     * 注册监听者
     * @param listener	要注册的监听者
     * @return	注册成功返回监听者在被监听对象处的ID
     */
    public int addListener(IFPSListener listener);
    
    /**
     * 注销监听者
     * @param listener	欲注销的监听者
     * @return	注销成功返回被注销的监听者，否则返回null
     */
    public IFPSListener removeListener(IFPSListener listener);
    
    /**
     * 注销监听者
     * @param id		欲注销的监听者ID
     * @return	注销成功返回被注销的监听者，否则返回null
     */
    public IFPSListener removeListener(int id);		
}
