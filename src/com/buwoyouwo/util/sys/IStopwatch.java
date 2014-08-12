package com.buwoyouwo.util.sys;

/**
 * (毫)秒表，提供计时功能
 * @author buwoyouwo
 *
 */
public interface IStopwatch {
	/**
	 * 读取秒表读数
	 * @return	当前读数，单位毫秒
	 */
	public long currentTimeMillis();
	
	/**
	 * 查看表秒状态，是否正在计时
	 * @return	true为正在跑表，false计时停止中
	 */
	public boolean ifRunning();
	
	/**
	 * 开始／继续 计时
	 */
	public void start();
	
	/**
	 * 暂停计时
	 */
	public void pause();
	
	/**
	 * 切换秒表状态，若正在计时则暂停，若计时暂停中则继续
	 * @return	切换后的秒表状态,true为正在计时，false为暂停中
	 */
	public boolean switchRunning();
	
	/**
	 * 计时重置为0，并暂停计时
	 */
	public void reset();
	
	/**
	 * 计时重置为目标时间，并暂停计时
	 * @param time	目标时间
	 */
	public void reset(long time);
	
	/**
	 * 计时重置为0，并开始计时
	 */
	public void restart();
	
	/**
	 * 计时重置为目标时间，并开始计时
	 * @param time
	 */
	public void restart(long time);
	
	
}
