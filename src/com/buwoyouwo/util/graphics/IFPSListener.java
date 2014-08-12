package com.buwoyouwo.util.graphics;

/**
 * FPS����������������FPSController����Ϣ
 * @author ��������
 *
 */
public interface IFPSListener {
	public void onEnable();
	public void onDisable();
	
	public void onFPSUpdate();
	public void beforeSleep();
	public void afterSleep();
}
