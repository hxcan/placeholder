package com.huiti.clipcms;

import android.content.Context;

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
public class HuitiUncaughtExceptionHandler implements UncaughtExceptionHandler
{
	private UncaughtExceptionHandler mOldHandler = null;
	private String mExceptionPath;
	public HuitiUncaughtExceptionHandler()
	{
		mOldHandler = Thread.getDefaultUncaughtExceptionHandler();
		mExceptionPath = HuitiBaseDef.LOG_BASE_DIR + File.separator + HuitiBaseDef.EXCEPTION_FILE;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		try {
			PrintWriter file = new PrintWriter(new FileWriter(mExceptionPath, true));
			file.write(DateFormat
					.getDateTimeInstance(DateFormat.SHORT , DateFormat.SHORT , Locale.US)
					.format(new Date()));
			String version = "";

			file.write("\r\n" + version + "\r\n");
			ex.printStackTrace(file);
			file.write("\r\n");
			file.close();
		} catch (Exception e) {
		} 
		mOldHandler.uncaughtException(thread, ex);
	}
} //public class HuitiUncaughtExceptionHandler implements UncaughtExceptionHandler



