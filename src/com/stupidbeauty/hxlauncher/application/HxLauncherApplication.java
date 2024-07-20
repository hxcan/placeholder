package com.stupidbeauty.hxlauncher.application;

import com.stupidbeauty.hxlauncher.bean.ApplicationListData;
// import com.stupidbeauty.victoriafresh.VFile;
import org.apache.commons.io.FileUtils;
// import com.stupidbeauty.hxlauncher.bean.ApplicationLockInformation;

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
    String result= packageNameVersionNameMap.get(packageName); // 获取可用 版本号名字。

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
        .penaltyDeath()
        .build()
      );

      loadPackageItemLaunchCoolDownMap(); //载入包条目的启动冷却时间数据。

      initializeSeekBarValueCoolDownTimeMap(); //初始化映射。

      scheduleStartBuiltinFtpServer(); // 计划启动内置 FTP 服务器。
      
      startCheckUpgrade(); // Start check upgrade.
	} //public void onCreate()

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

