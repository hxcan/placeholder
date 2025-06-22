package com.stupidbeauty.farmingbookapp;

import java.lang.reflect.Type;
import java.util.Set;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
// import com.stupidbeauty.hxlauncher.BuildConfig;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;

/**
 * @ClassName: PreferenceManagerUtil
 * @Description: constant
 * @author ljp
 * @date 2013-12-10
 */

public class PreferenceManagerUtil 
{
  private static final String TAG = "PreferenceManagerUtil"; //!<输出调试代码时使用的标记。
  // private static final String KEY_SHORTCUT_UPDATED = "shortcut_updated";
    private static final String KEY_SHORTCUT_UPDATED = "shortcut_created";

    /** 
     * 保存快捷方式是否已更新的状态
     * @param isUpdated 是否已更新
     */
    public static void setShortcutUpdated(boolean isUpdated) {
        Context ct = HxLauncherApplication.getAppContext(); // 获取应用程序上下文。
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct); // 获取共享配置对象。
        sp.edit().putBoolean(KEY_SHORTCUT_UPDATED, isUpdated).commit(); // 保存。
    }

    /**
     * 获取快捷方式是否已更新的状态
     * @return 是否已更新
     */
    public static boolean isShortcutUpdated() {
        Context ct = HxLauncherApplication.getAppContext(); // 获取应用程序上下文。
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct); // 获取共享配置对象。
        return sp.getBoolean(KEY_SHORTCUT_UPDATED, false); // 获取并返回状态，默认为false。
    }

  /**
  * 获取历史数据的版本号
  * @return 历史数据的版本号。语音命中应用程序数据
  */
  public static int getVoicePackageNameMapVersion()
  {
    Context ct = HxLauncherApplication.getAppContext();
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct);
    return sp.getInt(Constants.Common.VoicePackageMapVersion, 0);
  } //public static int getVoicePackageNameMapVersion()

  /**
  * 获取历史数据的版本号
  * @return 历史数据的版本号。语音命中快捷方式数据
  */
  public static int getVoiceShortCutMapVersion()
  {
    Context ct = HxLauncherApplication.getAppContext();
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct);
    return sp.getInt(Constants.Common.VoiceShortCutMapVersion, 0);
  } //public static int getVoiceShortCutMapVersion()
  
  /**
  * Get last time launched package time stamp. Second.
  */
  public static long getLastPackageTime() 
  {
    Context ct = HxLauncherApplication.getAppContext();
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct);
    return sp.getLong(Constants.Common.LastPackageTime, 0);
  } // public static long getLastPackageTime()
  
  /**
  * Get last time launched package name.
  */
  public static String getLastPackageName() 
  {
    Context ct = HxLauncherApplication.getAppContext();
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct);
    return sp.getString(Constants.Common.LastPackageName, ct.getPackageName());
  } // public String getLastPackageName()
  
  /**
  * 设置 last time launched package name.
  */
  public static void setLastPackageName(String lastPackageName) 
  {
    Context ct = HxLauncherApplication.getAppContext(); //获取应用程序上下文。
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct); //获取共享配置对象。
    sp.edit().putString(Constants.Common.LastPackageName, lastPackageName).apply(); // 保存。
  } // public static void setLastPackageName(String lastPackageName)

  /**
  * 设置 last time launched package time stamp. Second.
  */
  public static void setLastPackageTime(long lastPackageTimeStamp) 
  {
    Context ct = HxLauncherApplication.getAppContext(); //获取应用程序上下文。
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct); //获取共享配置对象。
    sp.edit().putLong(Constants.Common.LastPackageTime, lastPackageTimeStamp).apply(); // 保存。
  } // public static void setLastPackageTime(long lastPackageTimeStamp)
  
  /**
  * 设置语音命中快捷方式数据文件的版本号
  * @param BuildConfigVERSION_CODE 版本号
  */
  public static void setVoiceShortCutIdMapVersion(int BuildConfigVERSION_CODE)
  {
    Context ct = HxLauncherApplication.getAppContext(); //获取应用程序上下文。
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct); //获取共享配置对象。
    sp.edit().putInt(Constants.Common.VoiceShortCutMapVersion, BuildConfigVERSION_CODE).apply(); //保存。
  } //public static void setVoiceShortCutIdMapVersion(int BuildConfigVERSION_CODE)

  /**
  * 设置语音命中数据文件的版本号
  * @param BuildConfigVERSION_CODE 版本号
  */
  public static void setVoicePackageNameMapVersion(int BuildConfigVERSION_CODE)
  {
    Context ct = HxLauncherApplication.getAppContext(); //获取应用程序上下文。
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct); //获取共享配置对象。
    sp.edit().putInt(Constants.Common.VoicePackageMapVersion, BuildConfigVERSION_CODE).apply(); //保存。
  } //public static void setVoicePackageNameMapVersion(int BuildConfigVERSION_CODE)

  /**
  * 保存选项。是否要使用蜂窝布局
  * @param isChecked 是否要使用蜂窝布局
  */
  public static void setUseHiveLayout(Boolean isChecked)
  {
    Context ct = HxLauncherApplication.getAppContext(); //获取应用程序上下文。
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct); //获取共享配置对象。
    sp.edit().putBoolean(Constants.Common.UseHiveLayout, isChecked).commit(); //保存。
  } //public static void setUseHiveLayout(Boolean isChecked)

	/**
	 * 保存状态，常见问题数据库是否已经初始化。
	 * @param hasInit 是否已经初始化。
	 */
	public static void setBuiltinShortcutsVisible(Boolean hasInit)
	{
      Context ct = HxLauncherApplication.getAppContext(); //获取应用程序上下文。
      SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct); //获取共享配置对象。
      sp.edit().putBoolean(Constants.Common.BuitinShortcutsVisible, hasInit).commit(); //保存。
	} //public static void saveHasFAQInit(Boolean hasInit)s

	/**
	 * 是否要使用蜂窝布局
	 * @return 是否要使用蜂窝布局
	 */
	public static boolean isHiveLayout()
	{
      Context ct = HxLauncherApplication.getAppContext();
      SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct);
      return sp.getBoolean(Constants.Common.UseHiveLayout, false);
	} //public static boolean isHiveLayout()

	/**
	* 是否显示内置快捷方式。陈欣
	*/
	public static boolean isBuiltinShortcutsVisible()
	{
      Context ct = HxLauncherApplication.getAppContext();
      SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ct);
      return sp.getBoolean(Constants.Common.BuitinShortcutsVisible, true);
	}
}
