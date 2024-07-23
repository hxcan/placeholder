package com.stupidbeauty.hxlauncher.application;

import com.stupidbeauty.hxlauncher.asynctask.LoadVoicePackageUrlMapTask;
// import com.stupidbeauty.hxlauncher.asynctask.LoadApplicationLockSetTask;
// import com.stupidbeauty.hxlauncher.asynctask.LoadApplicationNameInternationalFileTask;
// import com.stupidbeauty.hxlauncher.asynctask.LoadPackageItemAliasMapTask;
import com.google.gson.Gson;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import com.stupidbeauty.victoriafresh.VFile;
import org.apache.commons.io.FileUtils;
import com.stupidbeauty.qtdocchinese.ArticleInfo;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import com.stupidbeauty.blindbox.asynctask.StorageCleanerTask;
import com.stupidbeauty.hxlauncher.Constants;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.ComponentName;
import com.stupidbeauty.hxlauncher.PackageItemLaunchCoolDownMapItemMessageProtos;
import com.stupidbeauty.hxlauncher.PackageItemLaunchCoolDownMapMessageProtos;
import com.stupidbeauty.hxlauncher.bean.ApplicationListData;
import java.util.HashSet;
import com.stupidbeauty.blindbox.asynctask.StorageCleanerTask;
import io.github.g00fy2.versioncompare.Version;
import com.stupidbeauty.hxlauncher.interfaces.LocalServerListLoadListener;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.io.FileUtils;
import com.stupidbeauty.upgrademanager.logic.VersionComparator;
import io.github.g00fy2.versioncompare.Version;
import com.stupidbeauty.hxlauncher.interfaces.LocalServerListLoadListener;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import com.stupidbeauty.upgrademanager.UpgradeManager;
import com.stupidbeauty.hxlauncher.listener.BuiltinFtpServerErrorListener; 
import android.os.Process;
import com.stupidbeauty.builtinftp.BuiltinFtpServer;
import java.util.TimerTask;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.StrictMode;
import androidx.multidex.MultiDex;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;
import com.upokecenter.cbor.CBORObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import com.upokecenter.cbor.CBORException;
import java.lang.reflect.Field;
import com.upokecenter.cbor.CBORObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import com.stupidbeauty.hxlauncher.PackageItemLaunchCoolDownMapItemMessageProtos;
import android.util.Log;
import com.stupidbeauty.upgrademanager.listener.PackageNameUrlMapDataListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.stupidbeauty.hxlauncher.PackageItemLaunchCoolDownMapItemMessageProtos;
import com.stupidbeauty.hxlauncher.PackageItemLaunchCoolDownMapMessageProtos;
// import com.stupidbeauty.hxlauncher.bean.ApplicationNameInternationalizationData;
// import com.stupidbeauty.hxlauncher.bean.VoicePackageUrlMapData;
// import com.stupidbeauty.hxlauncher.bean.WakeLockPackageNameSetData;
// import com.stupidbeauty.hxlauncher.datastore.RuntimeInformationStore;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import com.stupidbeauty.hxlauncher.interfaces.LocalServerListLoadListener;
import org.apache.commons.io.FileUtils;
import java.util.Timer;

/**
 * 应用程序对象。
 * @author root 蔡火胜。
 *
 */
public class HxLauncherApplication extends Application implements PackageNameUrlMapDataListener
{
  private BuiltinFtpServerErrorListener builtinFtpServerErrorListener=null; //!< the builtin ftp server error listener.  Chen xin.
  private BuiltinFtpServer builtinFtpServer=null; //!< The builtin ftp server.
	private  HashMap<String, String > packageNameApplicationNameMap; //!<包名与应用程序名的映射
	private HashMap<String, String> packageNameUrlMap; //!<包名与下载地址之间的映射关系。
  private HashMap<String, Integer> packageItemLaunchCoolDownMap=new HashMap<>(); //获取映射。包条目信息字符串与实际冷却时间秒数之间的映射。
  private HashMap<Integer, Integer> seekBarValueCoolDownTimeMap=new HashMap<>(); //!<映射。寻找条上的索引，与实际冷却时间秒数之间的映射。
  private boolean firstRunAfterBoot=false; //!<标志，是否是启动后初次运行。
  PowerManager.WakeLock wakeLock=null; //!<游戏辅助唤醒锁。
  private HashMap<String, String> apkFilePathMap=null; //!< 包名与安装包文件路径之间的映片。陈欣
	private HashMap<String, String> apkUrlPackageNameMap; //!< The map of apk url to package name.
  private HashMap<String, String> packageNameIconUrlMap; //!< map of package name of icon url.
	private HashMap<String, String> packageNameVersionNameMap; //!< 包名与可用版本号之间的映射关系。
  private UpgradeManager upgradeManager=null; //!< upgrade manager.
	private ApplicationListData applicationListData; //!< 应用程序列表数据。
  private HashMap<String, String> packageNameInformationUrlMap; //!< 包名与信息页面地址之间的映射关系。
  private HashMap<String, List<String> > packageNameExtraPackageNamesMap; //!< Map of packge name to extra package names.
  private HashMap<String, String> packageNameInstallerTypeMap; //!< Map of package name to installer type.
	private HashMap<String, String> packageItemAliasMap=null; //!<映射。包条目信息字符串与别名之间的映射。
  private String installingPackageName = null; //!< The currently instaling package name.
  private HashSet<String> iconNoCachePackageNameSet = new HashSet<>(); //!<不应当缓存其图标的软件包名集合

  /**
    * 获取图标。
    * @param articleInfo 应用程序信息。
    * @return 应用程序的启动图标。
    */
  public Drawable getApplicationIcon(String packageName)
  {
    ArticleInfo articleInfo=null; //创建应用程序信息对象。
    PackageManager packageManager=getPackageManager(); //获取软件包管理器。

    Intent launchIntent= packageManager.getLaunchIntentForPackage(packageName); //获取当前软件包的启动意图。

    if (launchIntent!=null) //意图存在。
    {
      try //尝试启动活动，并且捕获可能的异常。
      {
        ArticleInfo currentApplication=new ArticleInfo(); //创建应用程序信息对象。

        String activityName=launchIntent.getComponent().getClassName(); //获取活动的类名。

        currentApplication.setPackageName(packageName); //设置包名。
        currentApplication.setActivityName(activityName); // 设置活动名字。

        articleInfo=currentApplication;
      } //try //尝试启动活动，并且捕获可能的异常。
      catch (ActivityNotFoundException exception)
      {
        exception.printStackTrace(); //报告错误。
      } //catch (ActivityNotFoundException exception)
    } //if (launchIntent!=null) //意图存在。
  
    Drawable result; //结果。

    result=getBuiltinApplicationIcon(articleInfo); //尝试载入内置的应用程序图标。

    if (result==null) //没有内置的应用程序图标。
    {
      result=getSystemProvidedApplicationIcon(articleInfo); //获取由系统提供的应用程序图标。
    } //if (result==null) //没有内置的应用程序图标。

    return result;
  } //private Drawable getApplicationIcon(ArticleInfo articleInfo)

	/**
	* Start the storage cleaner.
	*/
	public void startStorageCleaner() 
	{
    StorageCleanerTask translateRequestSendTask =new StorageCleanerTask(); // 创建异步任务。

    translateRequestSendTask.execute(this); // 执行任务。
	} // private void startStorageCleaner()

  /**
  * The set of package name to ignore new versions.
  */
  public HashSet<String> getIconNoCachePackageNameSet()
  {
    return iconNoCachePackageNameSet;
  } // public HashSet<String> getIconNoCachePackageNameSet()

  public void deleteApkFile(String packageName)
  {
    HashMap<String, String> apkFilePathMap = getApkFilePathMap(); // 获取 APK 安装包路径映射。

    String apkFilePath=apkFilePathMap.get(packageName); // 获取安装包文件路径。

    // 陈欣
    File targetApkFile=new File(apkFilePath); // Find the file.
    targetApkFile.delete(); // Delete the cache file.
    
    // forgetApkFilePath(); // Forget apk file path.
  } // private void deleteApkFile(String apkFilePath)
	
  /**
  * Get the installing package name.
  */
  public String getInstallingPackageName()
  {
    return installingPackageName;
  } // public String getInstallingPackageName()
  
  /**
  * SEt the installing package name.
  */
  public void setInstallingPackageName(String packageName)
  {
    installingPackageName = packageName; // Remember the installing package name.
  } // public void setInstallingPackageName(String packageName)
  
    /**
     * 尝试载入内置的应用程序图标。
     * @param articleInfo 应用程序信息。
     * @return 内置的应用程序图标。
     */
    private  Drawable getBuiltinApplicationIcon(ArticleInfo articleInfo)
    {
      Drawable result=null; //结果。

      if (articleInfo!=null) // The packag einformation exists
      {
        String packageName = articleInfo.getPackageName(); //获取包名。
        String qrcFileName=packageName; //文件名。
        String fullQrcFileName=":/ApplicationIcon/"+qrcFileName; //构造完整的qrc文件名。

        VFile qrcHtmlFile=new VFile(this, fullQrcFileName); // qrc网页文件。

        if (qrcHtmlFile.exists()) //文件存在。
        {
          byte[] photoBytes= qrcHtmlFile.getFileContent(); //将照片文件内容全部读取。

          ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(photoBytes);

          result= Drawable.createFromStream(byteArrayInputStream, "JPEG"); //从文件中解码。
        } //if (qrcHtmlFile.exists()) //文件存在。

      } // if (articleInfo!=null) // The packag einformation exists
      
      return result;
    } //private  Drawable getBuiltinApplicationIcon(ArticleInfo articleInfo)

    /**
    * 获取图标。
    * @param articleInfo 应用程序信息。
    * @return 应用程序的启动图标。
    */
  public Drawable getApplicationIcon(ArticleInfo articleInfo)
  {
    Drawable result; //结果。

    result=getBuiltinApplicationIcon(articleInfo); //尝试载入内置的应用程序图标。

    if (result==null) //没有内置的应用程序图标。
    {
        result=getSystemProvidedApplicationIcon(articleInfo); //获取由系统提供的应用程序图标。
    } //if (result==null) //没有内置的应用程序图标。

    return result;
  } //private Drawable getApplicationIcon(ArticleInfo articleInfo)

    /**
     * 获取由系统提供的应用程序图标。
     * @param articleInfo 应用程序信息。
     * @return 由系统提供的应用程序图标。
     */
    private Drawable getSystemProvidedApplicationIcon(ArticleInfo articleInfo)
    {
      Drawable result = null; //结果。
      
      if (articleInfo!=null) // The information exists
      {
        String packageName = articleInfo.getPackageName(); //获取应用程序包名。

        String activityName=articleInfo.getActivityName(); //获取活动名字

        HxLauncherApplication application=HxLauncherApplication.getInstance(); //获取应用程序对象。

        if (result==null) //未缓存。
        {
          PackageManager packageManager=getPackageManager(); //获取软件包管理器。

          try //读取图标内容
          {
            PackageInfo packageInfo=packageManager.getPackageInfo(packageName,0); //获取应用程序信息。

            ApplicationInfo applicationInfo=packageInfo.applicationInfo; //获取应用程序信息。

            ComponentName componentName=new ComponentName(packageName, activityName); //创建部件名字对象

            result=packageManager.getActivityIcon(componentName); //获取活动的图标
          } //try //读取图标内容
          catch (PackageManager.NameNotFoundException e) //未找到该应用程序包。
          {
            e.printStackTrace(); //报告错误。
          } //catch (PackageManager.NameNotFoundException e) //未找到该应用程序包。
          catch (OutOfMemoryError outOfMemoryError)
          {
              outOfMemoryError.printStackTrace(); //报告错误。
          } //catch (OutOfMemoryError outOfMemoryError)
        } //if (result==null) //未缓存。
      } // if (articleInfo!=null) // The information exists

      return result;
    } //private Drawable getSystemProvidedApplicationIcon(ArticleInfo articleInfo)

	/**
	 * 将对象加入到本地服务器载入完毕的回调列表中。
	 * @param localServerListLoadListener 要加入的回调对象。
	 */
	public void addLocalServerListLoadListener(LocalServerListLoadListener localServerListLoadListener)
	{
    applicationListData.addLocalServerListLoadListener(localServerListLoadListener); //加入列表。
	} //public void addLocalServerListLoadListener(LocalServerListLoadListener localServerListLoadListener)

  /**
  * 获取 map of package name to installyer type.
  */
  public HashMap<String, String> getPackageNameInstallyerTypeMap() 
  {
    return packageNameInstallerTypeMap;
  } // public getPackageNameInstallyerTypeMap()

  /**
  * 获取数据对象。包名与信息页面地址之间的映射。
  */
  public HashMap<String, String> getPackageNameInformationUrlMap() 
  {
    return packageNameInformationUrlMap;
  } // public HashMap<String, String> getPackageNameInformationUrlMap()

	/**
	 *  // 获取可用的版本名字。
	 * @param packageName
	 * @return
	 */
	public String getHighestVersionNameByType(String packageName, String typeToGet)
	{
    String versionName=getVersionNameByType(packageName,  typeToGet); // Get the version name.
      
    if (packageNameExtraPackageNamesMap!=null) // The map exists
    {
      List<String> extraPackageNames = packageNameExtraPackageNamesMap.get(packageName);
      
      if (extraPackageNames!=null) // Has extra packgae names.
      {
        for(String extraPackage: extraPackageNames) // Check one by one
        {
          String extraPackageVersion= getVersionNameByType(extraPackage,  typeToGet); // Get version name of extra package.
          
          VersionComparator versionComparator=new VersionComparator(); // Create version comparator.

          if (versionComparator.isHigerVersion(extraPackageVersion, versionName)) // There is a higher version.
          {
            versionName = extraPackageVersion; // This is the highest version name so far.
          } //if (availableVersonName > currentVersionName) // 有新版本
        } // for(String extraPackage: extraPackageNames) // Check one by one
      } // if (extraPackageNames!=null) // Has extra packgae names.
    } // if (packageNameExtraPackageNamesMap!=null) // The map exists

    return versionName;
  } //public String getAvailableVersionName(String packageName)

	/**
	 * 获取版本名字。
	 * @param packageName 包名。陈欣
	 * @return 这个软件包现在的版本名字。
	 */
	public String getVersionName(String packageName)
	{
    String versionName=null;
    try
    {
      PackageManager packageManager=getPackageManager(); //获取软件包管理器。
      PackageInfo packageInfo=packageManager.getPackageInfo(packageName,0); //获取对应的软件包信息。

      versionName= packageInfo.versionName; // 获取版本号名字。
    }
    catch (PackageManager.NameNotFoundException e) //未找到该软件包。
    {
      // e.printStackTrace(); //报告错误。
    } //catch (PackageManager.NameNotFoundException e) //未找到该软件包。

    return versionName;
	} //public String getVersionName(String packageName)

	/**
	 *  // 获取可用的版本名字。
	 * @param packageName
	 * @return
	 */
	public String getVersionNameByType(String packageName, String typeToGet)
	{
    String result= null; // 获取可用 版本号名字。
    
    if (typeToGet.equals(Constants.VersionNameType.Existing)) // Existing.
    {
      result= getVersionName(packageName);
    } // if (typeToGet.equals(Constants.VersionNameType.Existing)) // Existing.
    else // Available
    {
      result=getAvailableVersionName(packageName);
    } // else // Available
    
    return result;
	} //public String getAvailableVersionName(String packageName)

  /**
  * Package is in whitelist.
  */
  public boolean isPackageInWhiteList(String packageName)
  {
    HashSet<String> whilteListSet=new HashSet<>();
    
//     whilteListSet.add("com.lilithgames.xgame.gp");
    
    return whilteListSet.contains(packageName);
  } // private boolean isPackageInWhiteList(String packageName)

  @Override
	/**
	* 语音识别结果与包名之间的映射关系。
	*/
	public void setVoicePackageUrlMap (HashMap<String, String> voicePackageUrlMap)
	{
    if (this.voicePackageUrlMap==null)
		{
			this.voicePackageUrlMap=voicePackageUrlMap;
		}
		else // voice package url map exists
		{
      if (voicePackageUrlMap!=null) // The source map exists
      {
        this.voicePackageUrlMap.putAll(voicePackageUrlMap);
      } // if (voicePackageUrlMap!=null) // The source map exists
		} // else // voice package url map exists

//       this.voicePackageUrlMap=voicePackageUrlMap;
	} //public void setVoicePackageUrlMap (HashMap<String, String> voicePackageUrlMap)

  @Override
	/**
	* 设置包名与下载地址之间的映射关系。
	*/
  public void setPackageNameUrlMap (HashMap<String, String> packageNameUrlMap) //!<包名与下载地址之间的映射关系。
  {
    if (this.packageNameUrlMap==null)
    {
      this.packageNameUrlMap=packageNameUrlMap;
    }
    else // NOt null
    {
      this.packageNameUrlMap.putAll(packageNameUrlMap);
    } // else // NOt null
    
		Log.d(TAG,"setPackageNameUrlMap, cn.j.yoyo: "+  this.packageNameUrlMap.get("cn.j.yoyo") + ", this: " + this); //Debug.
  } //public void setPackageNameUrlMap (HashMap<String, String> packageNameUrlMap)

  @Override
  /**
  * Set package name installer type map.
  */
  public void setPackageNameInstallerTypeMap(HashMap<String, String> packageNameInstallerTypeMap)
  {
    if (this.packageNameInstallerTypeMap==null)
    {
      this.packageNameInstallerTypeMap=packageNameInstallerTypeMap;
    }
    else // NOt null
    {
      this.packageNameInstallerTypeMap.putAll(packageNameInstallerTypeMap);
    } // else // NOt null
  } // public void setPackageNameInstallerTypeMap(HashMap<String, String> packageNameInstallerTypeMap)

  @Override
  /**
  * Set the map of package name to extra package names list.
  */
  public void setPackageNameExtraPackageNamesMap(HashMap<String, List<String> > packageNameExtraPackageNamesMap)
  {
    Log.d(TAG, "setPackageNameExtraPackageNamesMap, dance ball result in provided map: " + packageNameExtraPackageNamesMap.get("com.lew.game.danceball.lenovo"));
    if (this.packageNameExtraPackageNamesMap==null)
    {
      this.packageNameExtraPackageNamesMap=packageNameExtraPackageNamesMap;
    }
    else // NOt null
    {
      this.packageNameExtraPackageNamesMap.putAll(packageNameExtraPackageNamesMap);
    } // else // NOt null

    Log.d(TAG, "setPackageNameExtraPackageNamesMap, dance ball result in final map: " + this.packageNameExtraPackageNamesMap.get("com.lew.game.danceball.lenovo"));
  } // public void setPackageNameExtraPackageNamesMap(HashMap<String, List<String> > packageNameExtraPackageNamesMap)

	@Override
	/**
	* 设置包名与信息页面地址之间的映射。
	*/
	public void setPackageNameInformationUrlMap(HashMap<String, String> packageNameInformationUrlMap) 
	{
		if (this.packageNameInformationUrlMap==null)
		{
			this.packageNameInformationUrlMap=packageNameInformationUrlMap;
		}
		else 
		{
			this.packageNameInformationUrlMap.putAll(packageNameInformationUrlMap);
		}
	} // public void setPackageNameInformationUrlMap(HashMap<String, String> packageNameInformationUrlMap)

	@Override
	/**
	* set 包名与可用版本号之间的映射关系。
	*/
	public void setPackageNameVersionNameMap (HashMap<String, String> packageNameVersionNameMap) 
	{
		if (this.packageNameVersionNameMap==null)
		{
			this.packageNameVersionNameMap=packageNameVersionNameMap;
		}
		else
		{
			this.packageNameVersionNameMap.putAll(packageNameVersionNameMap);
		}

		applicationListData.notifyApplicationList(); // Notify version change.
	} //public void setPackageNameVersionNameMap (HashMap<String, String> packageNameVersionNameMap)

  @Override
	/**
	* Set 包名与应用程序名的映射 
	*/
	public void setPackageNameApplicationNameMap (HashMap<String, String > packageNameApplicationNameMap)
	{
    if (this.packageNameApplicationNameMap==null)
    {
      this.packageNameApplicationNameMap=packageNameApplicationNameMap;
    } // if (this.packageNameApplicationNameMap==null)
    else
    {
      this.packageNameApplicationNameMap.putAll(packageNameApplicationNameMap);
    }
	} //public void setPackageNameApplicationNameMap (HashMap<String, String > packageNameApplicationNameMap)

  @Override
  public void setPackageNameIconUrlMap(HashMap<String, String> packageNameUrlMap)
  {
    if (this.packageNameIconUrlMap==null)
    {
      this.packageNameIconUrlMap=packageNameUrlMap;
    }
    else // NOt null
    {
      this.packageNameIconUrlMap.putAll(packageNameUrlMap);
    } // else // NOt null
  } // public void setPackageNameIconUrlMap(HashMap<String, String> packageNameUrlMap)

	public ApplicationListData getApplicationListData()
	{
		return applicationListData;
	}

	/**
	* Start check upgrade.
	*/
	public void startCheckUpgrade() 
	{
    if (upgradeManager==null) // Upgrade manager does not exist
    {
      upgradeManager=new UpgradeManager(this); // Create upgrade manager.
    } // if (upgradeManager==null) // Upgrade manager does not exist
      
		upgradeManager.setPackageNameUrlMapDataListener(this);
      
		upgradeManager.checkUpgrade(); // Check upgrade.
	} // private void startCheckUpgrade()

	/**
	 *  // 获取可用的版本名字。
	 * @param packageName
	 * @return
	 */
	public String getAvailableVersionName(String packageName)
	{
    String result = null; // 获取可用 版本号名字。
    
    if (packageNameVersionNameMap!=null) // The map exists
    {
      result = packageNameVersionNameMap.get(packageName); // 获取可用 版本号名字。
    } // if (packageNameVersionNameMap!=null) // The map exists

    return result;
	} //public String getAvailableVersionName(String packageName)
	
	/**
	* Get the map of apk url to package name.
	*/
	public HashMap<String, String> getApkUrlPackageNameMap()
	{
		return apkUrlPackageNameMap;
	} // public HashMap<String, String> getApkUrlPackageNameMap()

	public HashMap<String, String> getPackageNameApplicationNameMap() 
	{
		return packageNameApplicationNameMap;
	}

	public HashMap<String, String> getPackageNameUrlMap() 
	{
    return packageNameUrlMap;
	}

  public HashMap<String, Integer> getPackageItemLaunchCoolDownMap() 
  {
    return packageItemLaunchCoolDownMap;
  }

  public HashMap<Integer, Integer> getSeekBarValueCoolDownTimeMap() 
  {
    return seekBarValueCoolDownTimeMap;
  }

	/**
	*  寻找映射文件。 包名与安装包路径之间的映片。陈欣。
	*/
	private File findApkFilePathMapFile()
	{
    File result=null;

    File filesDir=getFilesDir();

    if (filesDir==null) //该目录不存在。
    {
    } //if (filesDir==null) //该目录不存在。
    else //该目录存在。
    {
      result=new File(filesDir.getAbsolutePath()+"/apkFilePathMap.cx.d"); //指定文件名。

      if (result.exists()) //文件存在。
      {
      } //if (result.exists()) //文件存在。
      else //文件不存在。
      {
        try
        {
          boolean createResult=result.createNewFile(); //创建文件。
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      } //else //文件不存在。
    } //else //该目录存在。

    return result;
	} //private File findApkFilePathMapFile() //寻找映射文件。

	/**
	* 载入映射。
	*/
	private void loadApkFilePathMap() 
	{
    CBORObject result=null; //结果。

    byte[] payloadData=null; //获取负载字节数组。

    File apkFilePathMapFile=findApkFilePathMapFile(); //寻找映射文件。
    
    apkFilePathMap=new HashMap<>(); // 创建映射。

    try
    {
      payloadData=FileUtils.readFileToByteArray(apkFilePathMapFile); // 载入内容。
      
      Log.d(TAG, "loadApkFilePathMap, payloadData: " + payloadData.toString()); // Debug.
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    //解析消息：
    try
    {
      CBORObject videoStreamMessage= CBORObject.DecodeFromBytes(payloadData); //解析消息。
      
      java.util.Collection<java.util.Map.Entry<CBORObject,CBORObject>> entries=  videoStreamMessage.getEntries(); // 获取条目列表。
      
      for(java.util.Map.Entry<CBORObject,CBORObject> entry: entries) // 一个个地加入。
      {
        CBORObject keyObject=entry.getKey();
        CBORObject valueObject=entry.getValue();
        
        apkFilePathMap.put(keyObject.AsString(), valueObject.AsString()); // 加入映射。
      } //for(entry: entries) // 一个个地加入。
    }
    catch (CBORException e)
    {
      e.printStackTrace(); // 报告错误。
    }
	} //private void loadApkFilePathMap()

	/**
	* Get icon url.
	*/
	public String getIconForPackage(String packagename) 
	{
    String result="";
    
    if (packageNameIconUrlMap!=null)
    {
      result=packageNameIconUrlMap.get(packagename);
    } // if (packageNameIconUrlMap!=null)
    
    return result;
	} // public String getIconForPackage(String packagename)

	/**
	* 获取 APK 安装包路径映射。
	*/
	public HashMap<String, String> getApkFilePathMap()
	{
    if (apkFilePathMap==null)
    {
        loadApkFilePathMap(); // 载入映射。
    } //if (apkFilePathMap==null)
    
    return apkFilePathMap;
	} //public void HashMap<String, String> getApkFilePathMap()
	
	/**
	 * 保存映射。包条目字符串，与安装包文件路径之间的映射。
	 */
	public void saveApkFilePathMap()
	{
    CBORObject cborObject= CBORObject.NewMap(); //创建对象
        
		for(String currentVoiceRecognizeResult: apkFilePathMap.keySet()) //一个个地保存。
		{
			String currentPackageName=apkFilePathMap.get(currentVoiceRecognizeResult); //获取包名。

      cborObject.Add(currentVoiceRecognizeResult, currentPackageName); // 加入映射中。
		} //for(String currentVoiceRecognizeResult: voicePackageNameMap.keySet()) //一个个地保存。

    byte[] array=cborObject.EncodeToBytes();

		byte[] serializedContent=array; //序列化成字节数组。

		File photoFile=findApkFilePathMapFile(); //寻找映射文件。

		try
		{
			FileUtils.writeByteArrayToFile(photoFile, serializedContent); //写入内容。
			
			Log.d(TAG, "saveApkFilePathMap, content: " + serializedContent.toString()); // Debug.
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	} //public void savePackageItemAliasMap() //保存映射。
	
  public boolean isFirstRunAfterBoot() 
  {
    return firstRunAfterBoot;
  }

  public void setFirstRunAfterBoot(boolean firstRunAfterBoot) 
  {
    this.firstRunAfterBoot = firstRunAfterBoot;
  }

  @Override
  protected void attachBaseContext(Context base) 
  {
    super.attachBaseContext(base);

		MultiDex.install(this); //启动MultiDex.
	}

// 	private VoicePackageUrlMapData voicePackageUrlMapData; //!<语音识别结果与软件包下载地址之间的映射。
	public HashMap<String, String> getVoicePackageUrlMap() {
		return voicePackageUrlMap;
	}

	private HashMap<String, String> voicePackageUrlMap; //!<语音识别结果与包名之间的映射关系。


	/**
	 * 获取唤醒锁。
	 */
	public void acquireWakeLock()
	{
		PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "HxLauncher::WakeLockForGames");

		Log.d(TAG,"acquireWakeLock, wake lock: "+wakeLock + ", this: " + this); //Debug.

		wakeLock.acquire();
	} //private void acquireWakeLock()

	public void releaseWakeLock() //释放唤醒锁。
	{
		Log.d(TAG,"releaseWakeLock, wake lock: "+wakeLock + ", this: " + this); //Debug.

		if (wakeLock!=null) //唤醒锁存在。
        {
            wakeLock.release();
            wakeLock=null; //置空，避免重复释放。
        } //if (wakeLock!=null) //唤醒锁存在。

	} //public void releaseWakeLock()

	private static HxLauncherApplication mInstance = null;

	public static HxLauncherApplication getInstance() 
	{
      if (mInstance == null) 
      {
        mInstance = new HxLauncherApplication();
      }
      return mInstance;
	}

	private static Context mContext;
	private static final String TAG="HxLauncherApplication"; //!<输出调试信息时使用的标记。
	
	@Override
	/**
	 * 程序被创建。
	 */
	public void onCreate() 
	{
    super.onCreate(); //创建超类。

    mInstance = this;

    mContext = getApplicationContext(); //获取应用程序上下文。

    //启用严格模式：
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
      .detectDiskReads()
      .detectDiskWrites()
      .detectNetwork()
      .penaltyLog()
      .build()
    );

    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
      .detectLeakedSqlLiteObjects()
      .detectLeakedClosableObjects()
      .penaltyLog()
      // .penaltyDeath()
      .build()
    );

		loadVoicePackageUrlMap(); //载入语音识别结果与包下载地址之间的映射。
    loadPackageItemLaunchCoolDownMap(); //载入包条目的启动冷却时间数据。
		loadApplicationList(); //载入应用程序列表。

    initializeSeekBarValueCoolDownTimeMap(); //初始化映射。

    scheduleStartBuiltinFtpServer(); // 计划启动内置 FTP 服务器。
      
    startCheckUpgrade(); // Start check upgrade.
	} //public void onCreate()

	/**
	 * 载入应用程序列表。
	 */
	private void loadApplicationList()
	{
		applicationListData =new ApplicationListData(this); //创建数据对象。
	} //private void loadApplicationList()

	/**
	 * 载入语音识别结果与包下载地址之间的映射。
	 */
	private void loadVoicePackageUrlMap()
	{
    LoadVoicePackageUrlMapTask translateRequestSendTask =new LoadVoicePackageUrlMapTask(); //创建异步任务。

    translateRequestSendTask.execute(this); //执行任务。
	} //private void loadVoicePackageUrlMap()

    /**
    * 选择随机端口。
    */
    private int chooseRandomPort() 
    {
      int randomIndex=2005; //随机选择一个文件。

      return randomIndex;
    } //private int chooseRandomPort()

    /**
    * 启动内置 FTP 服务器。
    */
    private void startBultinFtpServer()
    {
      builtinFtpServer=new BuiltinFtpServer(this); //!< The builtin ftp server.
      builtinFtpServerErrorListener=new BuiltinFtpServerErrorListener(); // Create the builtin ftp server error listener. 
      int actualPort=chooseRandomPort(); // 选择随机端口。
      builtinFtpServer.setPort(actualPort); // 设置自动选择FTP监听端口。
      builtinFtpServer.setAllowActiveMode(false); // 设置不允许主动模式。
      builtinFtpServer.setErrorListener(builtinFtpServerErrorListener); // Set the error listener. Chen xin.
      builtinFtpServer.start(); //启动服务器。
    } //        startBultinFtpServer(); // 启动内置 FTP 服务器。
    
    /**
    * 计划启动内置 FTP 服务器。
    */
    private void scheduleStartBuiltinFtpServer() 
    {
      Timer timerObj = new Timer();
      TimerTask timerTaskObj = new TimerTask() 
      {
        public void run() 
        {
          startBultinFtpServer(); // 启动内置 FTP 服务器。
        }
      };
      timerObj.schedule(timerTaskObj, 15000); // 延时启动。
    } //private void scheduleStartBuiltinFtpServer()

	/**
	 * 初始化映射。
	 */
	private void initializeSeekBarValueCoolDownTimeMap()
	{
		seekBarValueCoolDownTimeMap.put(0, 0);
		seekBarValueCoolDownTimeMap.put(1, 30);
		seekBarValueCoolDownTimeMap.put(2, 1*60);
		seekBarValueCoolDownTimeMap.put(3, 10*60);
		seekBarValueCoolDownTimeMap.put(4, 30*60);
		seekBarValueCoolDownTimeMap.put(5, 1*60*60);
		seekBarValueCoolDownTimeMap.put(6, 2*60*60);
		seekBarValueCoolDownTimeMap.put(7, -1);
	} //private void initializeSeekBarValueCoolDownTimeMap()

	/**
	 * 载入语音识别结果与包名之间的映射。
	 */
	private void loadPackageItemLaunchCoolDownMap()
	{
		File photoFile=findRandomPhotoFile(); //随机寻找一个照片文件。

		packageItemLaunchCoolDownMap=new HashMap<>(); //创建映射。

		if (photoFile!=null) //不是空指针。
		{
			if (photoFile.exists()) //文件存在。
			{
				try
				{
					byte[] photoBytes= FileUtils.readFileToByteArray(photoFile); //将照片文件内容全部读取。

					PackageItemLaunchCoolDownMapMessageProtos.PackageItemLaunchCoolDownMapMessage translateRequestMessage=PackageItemLaunchCoolDownMapMessageProtos.PackageItemLaunchCoolDownMapMessage.parseFrom(photoBytes); //创建一个消息对象。

					List<PackageItemLaunchCoolDownMapItemMessageProtos.PackageItemLaunchCoolDownMapItemMessage> relationships=translateRequestMessage.getMapList(); //获取关系列表。

					for(PackageItemLaunchCoolDownMapItemMessageProtos.PackageItemLaunchCoolDownMapItemMessage currentRelationship: relationships) //一个个地加入映射中。
					{
						Log.i(TAG, "loadVoicePackageNameMap, package name: "+ currentRelationship.getPackageItemName() + ", auto run: " + currentRelationship.getLaunchCoolDown()); //Debug.


						packageItemLaunchCoolDownMap.put(currentRelationship.getPackageItemName(), currentRelationship.getLaunchCoolDown()); //加入映射。
					} //for(TranslateRequestMessageProtos.TranslateRequestMessage currentRelationship: relationships) //一个个地加入映射中。


				}
				catch (IOException e)
				{
					e.printStackTrace();
				} //catch (IOException e)

			} //if (photoFile.exists()) //文件存在。

		} //if (photoFile!=null) //不是空指针。

	} //private void loadVoicePackageNameMap()


	/**
	 * 获取应用程序上下文。
	 * @return 应用程序上下文。
	 */
	public static Context getAppContext() 
	{ 
		return mContext; 
	}  //public static Context getAppContext()

	/**
	 * 保存映射。包条目字符串，与实际冷却时间之间的映射。
	 */
	public void savePackageItemLaunchCoolDownMap()
	{
		PackageItemLaunchCoolDownMapMessageProtos.PackageItemLaunchCoolDownMapMessage.Builder translateRequestMessage= PackageItemLaunchCoolDownMapMessageProtos.PackageItemLaunchCoolDownMapMessage.newBuilder(); //创建一个消息对象。

		for(String currentVoiceRecognizeResult: packageItemLaunchCoolDownMap.keySet()) //一个个地保存。
		{
			Integer currentPackageName=packageItemLaunchCoolDownMap.get(currentVoiceRecognizeResult); //获取包名。

			Log.i(TAG,"savePackageNameAutoRunMap, package name: "+currentVoiceRecognizeResult+ ", auto run: " + currentPackageName); //Debug.


			PackageItemLaunchCoolDownMapItemMessageProtos.PackageItemLaunchCoolDownMapItemMessage.Builder translateRequestMessageBuilder= PackageItemLaunchCoolDownMapItemMessageProtos.PackageItemLaunchCoolDownMapItemMessage.newBuilder();

			translateRequestMessageBuilder.setLaunchCoolDown(currentPackageName).setPackageItemName(currentVoiceRecognizeResult); //设置字段。

			translateRequestMessage.addMap(translateRequestMessageBuilder); //添加映射关系。
		} //for(String currentVoiceRecognizeResult: voicePackageNameMap.keySet()) //一个个地保存。

		byte[] serializedContent=translateRequestMessage.build().toByteArray(); //序列化成字节数组。

		File photoFile=findRandomPhotoFile(); //随机寻找一个照片文件。

		try
		{
          FileUtils.writeByteArrayToFile(photoFile, serializedContent); //写入内容。
		}
		catch (IOException e)
		{
          e.printStackTrace();
		}
	} //public void savePackageItemLaunchCoolDownMap()

	/**
	 * 随机寻找一个照片文件。
	 * @return 随机寻找的一个照片文件。
	 */
	private  File findRandomPhotoFile()
	{
      File result=null;

      File filesDir=getFilesDir();

      Log.d(TAG, "findRandomPhotoFile, files dir: "+ filesDir); //Debug.

      if (filesDir==null) //该目录不存在。
      {
      } //if (filesDir==null) //该目录不存在。
      else //该目录存在。
      {
        result=new File(filesDir.getAbsolutePath()+"/packageItemLaunchCoolDownMap.off"); //指定文件名。

        if (result.exists()) //文件存在。
        {
        } //if (result.exists()) //文件存在。
        else //文件不存在。
        {
          try
          {
            boolean createResult=result.createNewFile(); //创建文件。

            Log.d(TAG, "findRandomPhotoFile, create file result: " + createResult); //Debug.
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
        } //else //文件不存在。
      } //else //该目录存在。

		return result;
	} //private  File findRandomPhotoFile()

} //public class SimoApp extends Application

