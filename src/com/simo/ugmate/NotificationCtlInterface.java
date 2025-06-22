package com.simo.ugmate;



/**
 * 通知栏的持久通知条目接口。
 * @author root 蔡火胜。
 *
 */
public interface NotificationCtlInterface 
{
	public void cancel() ; //!<取消通知。
	public void notifyAppSilent(); //!<以静音状态更新通知栏信息。 
} //public class NotificationCtl

