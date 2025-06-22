package com.stupidbeauty.blindbox.asynctask;

import java.util.Random;
import com.stupidbeauty.hxlauncher.manager.ActiveUserReportManager;
import android.os.Debug;
import com.stupidbeauty.upgrademanager.UpgradeManager;
import com.stupidbeauty.codeposition.CodePosition;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.BufferedReader;
import com.koushikdutta.async.future.FutureCallback;
import android.util.Log;
import java.lang.reflect.Type;
import java.lang.reflect.Field;
import com.upokecenter.cbor.CBORObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import android.annotation.SuppressLint;
// import com.stupidbeauty.hxlauncher.bean.VoicePackageUrlMapData;
import java.util.Locale;
import com.stupidbeauty.hxlauncher.interfaces.LocalServerListLoadListener;
import com.google.gson.Gson;
import com.stupidbeauty.hxlauncher.Constants;
// import com.stupidbeauty.hxlauncher.PackageItemAliasMapMessageProtos;
// import com.stupidbeauty.hxlauncher.PackageItemLaunchCoolDownMapItemMessageProtos;
// import com.stupidbeauty.victoriafresh.VFile;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static android.content.Intent.ACTION_PACKAGE_CHANGED;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import android.view.View;
import android.os.AsyncTask;
import java.util.HashMap;
// import com.stupidbeauty.hxlauncher.bean.ApplicationNamePair;
import java.util.List;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.AnimationDrawable;
import org.apache.commons.collections4.SetValuedMap;
import android.util.Pair;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
// import com.andexert.library.RippleView;
// import com.stupidbeauty.hxlauncher.AndroidApplicationMessage;
// import com.stupidbeauty.hxlauncher.VoicePackageMapItemMessageProtos;
// import com.stupidbeauty.hxlauncher.VoicePackageMapMessageProtos;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
// import com.stupidbeauty.hxlauncher.bean.VoicePackageMapJsonItem;
// import com.stupidbeauty.hxlauncher.bean.VoicePackageUrlMapData;

public class StorageCleanerTask extends AsyncTask<Object, Void, Object>
{
  private static final String TAG="StorageCleanerTask"; //!< 输出调试信息时使用的标记。
  // private VoicePackageUrlMapData voicePackageUrlMapData; //!<语音识别结果与软件包下载地址之间的映射。
  private HashMap<String, String> voicePackageUrlMap; //!<语音识别结果与包名之间的映射关系。
  private HashMap<String, String> packageNameIconUrlMap; //!< The map of package name and icon url.
  private HxLauncherApplication launcherActivity=null; //!< 启动活动。
	
  @Override
  protected Object doInBackground(Object... params)
  {
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
    Boolean result=false; //结果，是否成功。

    launcherActivity=(HxLauncherApplication)(params[0]); // 获取映射对象
          
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
    long usedStorage=calculateUsedStorage(); // Calculate used storage.
    
    long storageThreshold=864581493; // threshold. about 824 MiB
    
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
    if (usedStorage>storageThreshold) // Used too much storage
    {
      deleteOneApkRandomly(); // Delete one apk randomely.
    } // if (usedStorage>storageThreshold) // Used too much storage
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
          
    boolean addPhotoFile=false; //Whether to add photo file

    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
    return voicePackageUrlMap;
  } //protected Object doInBackground(Object... params)
  
  /**
  * Calculate used storage.
  */
  private long calculateUsedStorage() 
  {
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
    long totalFileSize=0; // result;
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
    
    HashMap<String, String> apkFilePathMap=baseApplication.getApkFilePathMap(); // 获取 APK 安装包路径映射。
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.

    List<String> keys = new ArrayList<>(apkFilePathMap.keySet());

    int keySize=keys.size();
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
    
    int keyCounter=0;
    
    for(keyCounter=0; keyCounter<keySize; keyCounter++)
    {
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      String result=keys.get(keyCounter); // Get a package name.

      String packageName=result;
    
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      String apkFilePath=apkFilePathMap.get(packageName); // 获取安装包文件路径。
    
      File apkFile=new File(apkFilePath);

    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      long fileSize=apkFile.length(); // 文件尺寸。

      totalFileSize+=fileSize; // add file size.
    } // for(keyCounter=0; keyCounter<keySize; keyCounter++)
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
    
    return totalFileSize;
  } // private int calculateUsedStorage()
  
  /**
  * Delete one apk randomely.
  */
  private void deleteOneApkRandomly() 
  {
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。

    HashMap<String, String> apkFilePathMap=baseApplication.getApkFilePathMap(); // 获取 APK 安装包路径映射。

    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
    List<String> keys = new ArrayList<>(apkFilePathMap.keySet());

    int keySize=keys.size();
    
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
    if (keySize>0) // There is content
    {
      Random random=new Random();
      
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      int intentIndex=random.nextInt(keySize); //随机确定一个下标．

      String result=keys.get(intentIndex);

    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      String packageName=result;
      
      String apkFilePath=apkFilePathMap.get(packageName); // 获取安装包文件路径。
      
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      File apkFile=new File(apkFilePath);
      
      long lastModified=apkFile.lastModified(); // Get last modified.
      
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      long currentMillisecond=System.currentTimeMillis(); // 记录开始时间戳。
      
      
//       妳真是又好看又渊博
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
//       2.19 8:16
// 
//       2.21 8:27:50
// 
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
//       2 day 11 min 50 second


      
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      
      long modifiedTimeThreshold=((2*24*60+11)*60+50)*1000;
      if ((currentMillisecond-lastModified)>modifiedTimeThreshold) // Old enough
      {
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
        apkFile.delete(); // Delete file.
        
        forgetApkFilePath(packageName); // forget apk file path.
      } // if ((currentMillisecond-lastModified)>modifiedTimeThreshold) // Old enough
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      
    } // if (keySize>0) // There is content
    Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
  } // private void deleteOneApkRandomly()
  
    /**
  * Forget apk file path.
  */
  private void forgetApkFilePath(String packageName)
  {
    if (packageName!=null)
    {
      HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。

      HashMap<String, String> apkFilePathMap=baseApplication.getApkFilePathMap(); // 获取 APK 安装包路径映射。
          
      Log.d(TAG, "forgetApkFilePath, package name: " + packageName + ", apk file path: "); // Debug.
          
      apkFilePathMap.remove(packageName);
          
      baseApplication.saveApkFilePathMap(); // 保存映射。
    } //if (packageName!=null)
  } // private void forgetApkFilePath()



  /**
    * 报告结果。
    * @param result 结果。是否成功。
  */
  @Override
  protected void onPostExecute(Object result)
  {
//     launcherActivity.setVoicePackageUrlMap(voicePackageUrlMap);
//     launcherActivity.setPackageNameUrlMap(packageNameUrlMap);
//     launcherActivity.setPackageNameIconUrlMap(packageNameIconUrlMap); // Set the map of package name and icon url.
//     launcherActivity.setPackageNameInstallerTypeMap(packageNameInstallerTypeMap); // Set package name installer type map.
//     launcherActivity.setPackageNameInformationUrlMap(packageNameInformationUrlMap); // 设置包名与信息页面地址之间的映射。
//     launcherActivity.setPackageNameVersionNameMap(packageNameVersionNameMap);
//     launcherActivity.setPackageNameApplicationNameMap(packageNameApplicationNameMap);
  } //protected void onPostExecute(Boolean result)
}
