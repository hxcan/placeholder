package com.stupidbeauty.blindbox.asynctask;

import com.stupidbeauty.threeupgrade.DownloadRequestor;
import java.io.OutputStream;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.view.View;
import com.stupidbeauty.blindbox.activity.ApkInstallActivity;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.view.View;
import android.content.IntentSender;
import java.util.Random;
import com.stupidbeauty.hxlauncher.manager.ActiveUserReportManager;
import android.os.Debug;
import com.stupidbeauty.upgrademanager.UpgradeManager;
import android.util.Log;
import java.lang.reflect.Type;
import java.lang.reflect.Field;
import com.upokecenter.cbor.CBORObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import android.annotation.SuppressLint;
// import com.stupidbeauty.hxlauncher.bean.VoicePackageUrlMapData;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
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
// import com.stupidbeauty.hxlauncher.bean.ApplicationNameInternationalizationData;
// import com.stupidbeauty.hxlauncher.bean.VoicePackageMapJsonItem;
// import com.stupidbeauty.hxlauncher.bean.VoicePackageUrlMapData;

public class AddApkToInstallSessionTask extends AsyncTask<Object, Void, Object>
{
  private static final String TAG="AddApkToInstallSessionTask"; //!< 输出调试信息时使用的标记。
  // private VoicePackageUrlMapData voicePackageUrlMapData; //!<语音识别结果与软件包下载地址之间的映射。
  private boolean voicePackageUrlMap=false; //!< Success or not.
  private HashMap<String, String> packageNameIconUrlMap; //!< The map of package name and icon url.
  private DownloadRequestor launcherActivity=null; //!< The download requestor.
	
  @Override
  protected Object doInBackground(Object... params)
  {
//     private void addApktoInstallSessionAsync(String downloadFilePath, PackageInstaller.Session session, IntentSender statusReceiver)
//   {
//     AddApkToInstallSessionTask addApkToInstallSessionTask =new AddApkToInstallSessionTask();
//     
//     addApkToInstallSessionTask.execute(downloadFilePath, session, statusReceiver); // 执行任务。
//   } // private void addApktoInstallSessionAsync(String downloadFilePath, PackageInstaller.Session session, IntentSender statusReceiver)

    Boolean result=false; //结果，是否成功。

//     launcherActivity=(BlindBoxApplication)(params[0]); //获取映射对象
    String downloadFilePath=(String)(params[0]); // 获取 file path
    PackageInstaller.Session session=(PackageInstaller.Session )(params[1]); //获取映射对象
    IntentSender statusReceiver=(IntentSender )(params[2]); //获取映射对象
    launcherActivity=(DownloadRequestor )(params[3]); // 获取 download requestor.
          
//     long usedStorage=calculateUsedStorage(); // Calculate used storage.
//     
//     long storageThreshold=864581493; // threshold. about 824 MiB
//     
//     if (usedStorage>storageThreshold) // Used too much storage
//     {
//       deleteOneApkRandomly(); // Delete one apk randomely.
//     } // if (usedStorage>storageThreshold) // Used too much storage
//           
//     boolean addPhotoFile=false; //Whether to add photo file

    try // commit to session
    {
      addApkToInstallSession(downloadFilePath, session);

      // Commit the session (this will start the installation workflow).
      session.commit(statusReceiver);
      
      result=true;
    } // try // commit to session
    catch (IOException e) // Got ioexception
    {
//       throw new RuntimeException("Couldn't install package", e);
      Log.d(TAG, "requestInstallApi, caught exception"); // Debug.
      e.printStackTrace();
    } // catch (IOException e) // Got ioexception
    catch (RuntimeException e) 
    {
      if (session != null) 
      {
        session.abandon();
      }
      throw e;
    }

    voicePackageUrlMap=result; // remember result.


    return voicePackageUrlMap;
  } //protected Object doInBackground(Object... params)

    private void addApkToInstallSession(String assetName, PackageInstaller.Session session) throws IOException 
  {
    // It's recommended to pass the file size to openWrite(). Otherwise installation may fail
    // if the disk is almost full.
    OutputStream packageInSession = session.openWrite("package", 0, -1);
        
    File apkFileObject=new File(assetName);
            
//     byte[] buffer=FileUtils.readFileToByteArray(apkFileObject);
//             
//     int n=buffer.length;
//             
//     packageInSession.write(buffer, 0, n);
    
    FileUtils.copyFile(apkFileObject, packageInSession); // Copy file
                
    packageInSession.close();
  } // private void addApkToInstallSession(String assetName, PackageInstaller.Session session) throws IOException 

  /**
  * Calculate used storage.
  */
  private long calculateUsedStorage() 
  {
    long totalFileSize=0; // result;
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
    
    HashMap<String, String> apkFilePathMap=baseApplication.getApkFilePathMap(); // 获取 APK 安装包路径映射。

    List<String> keys = new ArrayList<>(apkFilePathMap.keySet());

    int keySize=keys.size();
    
    int keyCounter=0;
    
    for(keyCounter=0; keyCounter<keySize; keyCounter++)
    {
      String result=keys.get(keyCounter); // Get a package name.

      String packageName=result;
    
      String apkFilePath=apkFilePathMap.get(packageName); // 获取安装包文件路径。
    
      File apkFile=new File(apkFilePath);

      long fileSize=apkFile.length(); // 文件尺寸。

      totalFileSize+=fileSize; // add file size.
    } // for(keyCounter=0; keyCounter<keySize; keyCounter++)
    
    return totalFileSize;
  } // private int calculateUsedStorage()
  
  /**
  * Delete one apk randomely.
  */
  private void deleteOneApkRandomly() 
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。

    HashMap<String, String> apkFilePathMap=baseApplication.getApkFilePathMap(); // 获取 APK 安装包路径映射。

    List<String> keys = new ArrayList<>(apkFilePathMap.keySet());

    int keySize=keys.size();
    
    if (keySize>0) // There is content
    {
      Random random=new Random();
      
      int intentIndex=random.nextInt(keySize); //随机确定一个下标．

      String result=keys.get(intentIndex);

      String packageName=result;
      
      String apkFilePath=apkFilePathMap.get(packageName); // 获取安装包文件路径。
      
      File apkFile=new File(apkFilePath);
      
      long lastModified=apkFile.lastModified(); // Get last modified.
      
      long currentMillisecond=System.currentTimeMillis(); // 记录开始时间戳。
      
      
//       妳真是又好看又渊博
//       2.19 8:16
// 
//       2.21 8:27:50
// 
//       2 day 11 min 50 second


      
      
      long modifiedTimeThreshold=((2*24*60+11)*60+50)*1000;
      if ((currentMillisecond-lastModified)>modifiedTimeThreshold) // Old enough
      {
        apkFile.delete(); // Delete file.
        
        forgetApkFilePath(packageName); // forget apk file path.
      } // if ((currentMillisecond-lastModified)>modifiedTimeThreshold) // Old enough
      
    } // if (keySize>0) // There is content
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
    if (voicePackageUrlMap) // success
    {
    }
    else // Failed
    {
      launcherActivity.notifyDownloadFail(Constants.DownloadFailureReason.InstallRequestError); // notify download failed.
    } // else // Failed
  } //protected void onPostExecute(Boolean result)
}
