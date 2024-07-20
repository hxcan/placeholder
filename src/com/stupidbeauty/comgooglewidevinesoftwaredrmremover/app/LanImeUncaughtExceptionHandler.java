package com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app;

import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * 未捕获的异常处理器。
 * @author root 蔡火胜。
 *
 */
public class LanImeUncaughtExceptionHandler implements UncaughtExceptionHandler 
{
	private static final String TAG = "LanImeUncaughtException"; //!<输出调试信息时使用的标记。
	private final UncaughtExceptionHandler mOldHandler;

	private final String mExceptionPath;
	
	/**
	 * 构造函数。
	 */
	public LanImeUncaughtExceptionHandler()
	{
		mOldHandler = Thread.getDefaultUncaughtExceptionHandler(); //记录旧的处理器。
		

		mExceptionPath = LanImeBaseDef.LOG_BASE_DIR + File.separator + LanImeBaseDef.EXCEPTION_FILE; //构造异常文件路径。 
	}

	@SuppressWarnings("DanglingJavadoc")
	@Override
	/**
	 * 未捕获的异常。
	 */
	public void uncaughtException(Thread thread, Throwable ex) 
	{
		Log.d(TAG,"uncaughtException,捕获到异常。"); //Debug.
		try //尝试写入日志，并且捕获可能的异常。 
		{
			PrintWriter file = new PrintWriter(new FileWriter(mExceptionPath, true)); //创建日志输出器。
			file.write(DateFormat.getDateTimeInstance(DateFormat.SHORT , DateFormat.SHORT , Locale.US).format(new Date())); //输出日期。

			ex.printStackTrace(file); //输出调用栈。
			file.write("\r\n"); //输出换行。
			file.close(); //关闭。
		} //try //尝试写入日志，并且捕获可能的异常。
		catch (Exception e) //捕获异常。 
		{
			e.printStackTrace(); //报告错误。
		} //catch (Exception e) //捕获异常。 
		mOldHandler.uncaughtException(thread, ex); //使用原有异常处理器来处理。
	} //public void uncaughtException(Thread thread, Throwable ex)
}
