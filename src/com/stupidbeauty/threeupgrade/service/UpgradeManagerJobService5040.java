package com.stupidbeauty.threeupgrade.service;

import com.stupidbeauty.placeholder.R2;
import com.stupidbeauty.voiceui.VoiceUi;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import com.stupidbeauty.codeposition.CodePosition;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.BufferedReader;
import com.koushikdutta.async.future.FutureCallback;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class UpgradeManagerJobService5040 extends JobService
{
  private static final String TAG = "UpgradeManagerJobService5040"; //!< 输出调试信息时使用的标记。
  private VoiceUi voiceUi=null; //!< 语音交互对象。
  
	/**
	 * We are being created.
	 */
	@Override
	public void onCreate()
	{
		super.onCreate(); //创建超类。

    HxLauncherApplication baseApplication= HxLauncherApplication.getInstance(); //获取应用程序对象。
    voiceUi=new VoiceUi(baseApplication); // 创建语音交互对象。
	} //public void onCreate()

  @Override
  public boolean onStartJob(JobParameters params)
  {
    Log.d(TAG, CodePosition.newInstance().toString() + ", params: " + params); //Debug.
    
    HxLauncherApplication baseApplication= HxLauncherApplication.getInstance(); //获取应用程序对象。

    baseApplication.startCheckUpgrade(); // Start check upgrade.
    
    // Chen xin. voice ui.
    String mWordSeparators = baseApplication.getResources().getString(R2.string.startCheckUpgrade); // 读取 说明 字符串。
    voiceUi.say(mWordSeparators); // 说话，需要解锁。

    return true;
  } // public boolean onStartJob(JobParamters params)
  
  @Override
  public boolean onStopJob(JobParameters params)
  {
    Log.d(TAG, CodePosition.newInstance().toString() + ", params: " + params); //Debug.
  
    return true;
  } // public boolean onStopJob(JobParameters params)
} // public class UpgradeManagerJobService5040 extends JobService
