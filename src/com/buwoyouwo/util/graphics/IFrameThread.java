package com.buwoyouwo.util.graphics;

public interface IFrameThread {
	/**
	 * ע�������
	 * @param listener ������
	 * @return	�˶�����ע������ߵ�ID
	 */
	public int addFrameListener(IFrameThreadListener listener);
	
	/**
	 * ע��������
	 * @param listener ��ע���ļ�����
	 * @return	��ע���ļ����ߣ�ע��ʧ�ܷ���null
	 */
	public IFrameThreadListener removeFrameListener(IFrameThreadListener listener);
	
	/**
	 * ����IDע��������
	 * @param id ��ע�������ߵ�ID
	 * @return ��ע���ļ����ߣ�ע��ʧ�ܷ���null
	 */
	public IFrameThreadListener removeFrameListener(int id);
}
