package com.stupidbeauty.threeupgrade;

import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
// import com.stupidbeauty.xapkinstaller.core.XAPKInstaller;
import com.stupidbeauty.blindbox.asynctask.AddApkToInstallSessionTask;
// import com.stupidbeauty.xapkinstaller.core.XAPKInstaller;
import java.io.OutputStream;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.stream.OutputStreamDataCallback;
import com.koushikdutta.async.http.callback.HttpConnectCallback;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.HttpUtil;
import com.stupidbeauty.upgrademanager.logic.VersionComparator;
import io.github.g00fy2.versioncompare.Version;
import com.stupidbeauty.hxlauncher.interfaces.LocalServerListLoadListener;
import org.apache.commons.collections4.MultiMap;
import com.koushikdutta.async.DataEmitter;
import org.apache.commons.collections4.map.MultiValueMap;
import com.koushikdutta.async.ByteBufferList;
import com.stupidbeauty.codeposition.CodePosition;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.BufferedReader;
// import com.stupidbeauty.xapkinstaller.core.XAPKInstaller;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.view.View;
import com.stupidbeauty.voiceui.VoiceUi;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Looper;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.os.Build;
import androidx.core.content.FileProvider;
import android.database.ContentObserver;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import androidx.core.content.FileProvider;
import android.database.ContentObserver;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import java.io.DataInputStream;
import com.stupidbeauty.hxlauncher.LauncherActivity;
import com.stupidbeauty.placeholder.R;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import com.stupidbeauty.placeholder.R2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import com.stupidbeauty.hxlauncher.service.DownloadNotificationService;
import com.stupidbeauty.hxlauncher.Constants;
import org.apache.commons.io.FileUtils;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
// import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.stupidbeauty.hxlauncher.activity.ApplicationInformationActivity;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import com.stupidbeauty.hxlauncher.bean.ApplicationListData;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import com.stupidbeauty.blindbox.activity.ApkInstallActivity;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.view.View;

public class DownloadRequestor implements DownloadConnectCallbackInterface
{
  private String fullUrl = null; //!< Downloading full url.
  private static final String PACKAGE_INSTALLED_ACTION = "com.example.android.apis.content.SESSION_API_PACKAGE_INSTALLED";
  private String installerType="APK"; //!< Installer type.
  private ApplicationInformationActivity launcherActivity; // !< The calling activity.
  private Notification continiusNotification=null; //!<记录的通知
  private VoiceUi voiceUi=null; //!< 语音交互对象。
  private int NOTIFICATION = 84951; //!< 通知编号。陈欣
  private long MinimalApkSize = 250; //!< minimal accepted apk file size.
  private String packageName=null; //!< 包名。
  public Future<File> fileDownloadFuture; //!< The file download future.
  public Future<AsyncHttpResponse> fileDownloadFutureAndroidAsync; //!< The file download future.
  private NotificationManager mNM;
  private static final String TAG="DownloadRequestor"; //!< 输出调试信息时使用的标记
  
  public void setLauncherActivity(ApplicationInformationActivity launcherActivity)
  {
    this.launcherActivity = launcherActivity;
  } // public void setLauncherActivity(ApplicationInformationActivity launcherActivity)

  /**
  * Forget apk file path.
  */
  private void forgetApkFilePath() 
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
  * 记录安装包路径。
  */
  private void  rememberApkFile(String apkFilePath) 
  {
    if (packageName!=null)
    {
      HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。

      HashMap<String, String> apkFilePathMap=baseApplication.getApkFilePathMap(); // 获取 APK 安装包路径映射。
          
      Log.d(TAG, "rememberApkFile, package name: " + packageName + ", apk file path: " + apkFilePath); // Debug.
          
      apkFilePathMap.put(packageName, apkFilePath); // 加入映射中。
          
      baseApplication.saveApkFilePathMap(); // 保存映射。
    } //if (packageName!=null)
  } //private void  rememberApkFile(String apkFilePath)

  /**
  * 停止，下载通知服务。
  */
  private void stopDownloadNotificationService() 
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。

    Intent serviceIntent = new Intent(baseApplication, DownloadNotificationService.class); //创建意图。

    baseApplication.stopService(serviceIntent);
  } //private void stopDownloadNotificationService()
  
  /**
    * 要求安装应用
    * @param downloadFilePath 应用安装包路径
    */
  private void requestInstallView(String downloadFilePath)
  {
    Log.d(TAG, "requestInstall, path: " + downloadFilePath); // Debug.

    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。

    String type = "application/vnd.android.package-archive";

    File file=new File(downloadFilePath);
        
    Intent intent = new Intent(Intent.ACTION_VIEW);
    
    //       04-01 16:12:41.051 19837 19837 E AndroidRuntime: android.content.ActivityNotFoundException: No Activity found to handle Intent { act=android.intent.action.VIEW dat=file:///storage/emulated/0/Android/data/com.stupidbeauty.hxlauncher/files/Download/5F1E59D37ED5FCA5542C7EB86977A9D4.apk typ=application/vnd.android.package-archive flg=0x10000000 }

    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) 
    {
      try
      {
        String applicationPackageName = baseApplication.getPackageName();
        Uri downloadedApk = FileProvider.getUriForFile(baseApplication, applicationPackageName + ".com.stupidbeauty.upgrademanager.fileprovider", file);

        intent.setClipData(ClipData.newRawUri("", downloadedApk));
          
        intent.setDataAndType(downloadedApk, type);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        
        Log.d(TAG, "requestInstall, intent: " + intent); // Debug.
      }
      catch(IllegalArgumentException e)
      {
        //       4/3/22 17:22java.lang.IllegalArgumentException: Failed to find configured root that contains /storage/emulated/0/download/b75da43000c64716bd5a688e65bfb370.apk

        e.printStackTrace();
      
        intent.setDataAndType(Uri.fromFile(file), type);
        Log.d(TAG, "requestInstall, intent: " + intent); // Debug.
      } // catch(IllegalArgumentException e)
    }
    else 
    {
      intent.setDataAndType(Uri.fromFile(file), type);
      Log.d(TAG, "requestInstall, intent: " + intent); // Debug.
    }

    Log.d(TAG, "requestInstall, starting activity to install apk"); // Debug.

    // String mWordSeparators = baseApplication.getResources().getString(R.string.prepareInstall); // 读取 说明 字符串。Prepare install

    // voiceUi.say(mWordSeparators); // 说话，prepare install
    baseApplication.startActivity(intent);
      
    stopDownloadNotificationService(); // 停止，下载通知服务。
    notifyDownloadFinished(); // Notify download finished.
  } //private void requestInstall(String downloadFilePath)

  /**
  * Add apk to install session, async.
  */
  private void addApktoInstallSessionAsync(String downloadFilePath, PackageInstaller.Session session, IntentSender statusReceiver)
  {
    AddApkToInstallSessionTask addApkToInstallSessionTask =new AddApkToInstallSessionTask();
    
    addApkToInstallSessionTask.execute(downloadFilePath, session, statusReceiver, this); // 执行任务。
  } // private void addApktoInstallSessionAsync(String downloadFilePath, PackageInstaller.Session session, IntentSender statusReceiver)

  /**
  * 要求安装应用
  * @param downloadFilePath 应用安装包路径
  */
  private boolean requestInstallApi(String downloadFilePath)
  {
    boolean result=false;
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); // Get the application object.

    PackageInstaller.Session session = null;
    try // commit to session
    {
      PackageInstaller packageInstaller = baseApplication.getPackageManager().getPackageInstaller();
      PackageInstaller.SessionParams params = new PackageInstaller.SessionParams( PackageInstaller.SessionParams.MODE_FULL_INSTALL);

          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) // 31.
    {
      // 陈欣，api level 31
      params.setRequireUserAction(PackageInstaller.SessionParams.USER_ACTION_NOT_REQUIRED); // Silent update.
    }

      
      
      int sessionId = packageInstaller.createSession(params);
      session = packageInstaller.openSession(sessionId);

      IntentSender statusReceiver = constructInstallStatusReceiver();
      
      addApktoInstallSessionAsync(downloadFilePath, session, statusReceiver);

      result=true;
    } // try // commit to session
    catch (IOException e) // Got ioexception
    {
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
    
    return result;
  } //private void requestInstall(String downloadFilePath)
  

  /**
    * 要求安装应用
    * @param downloadFilePath 应用安装包路径
    */
  private void requestInstall(String downloadFilePath)
  {
    boolean requestSucess=false; 
  
    Log.d(TAG, CodePosition.newInstance().toString()+ ", installer type: "+ installerType + ", file path: " + downloadFilePath ); //Debug.
    if (Constants.InstallerType.XAPK.equals( installerType)) // XAPK. not check
    {
      // requestSucess=requestInstallXAPKInstaller(downloadFilePath); // Request install by xapk installer.
      Log.d(TAG, CodePosition.newInstance().toString()+ ", success: " + requestSucess); // Debug.
    } // if (installerType.equals("XAPK")) // XAPK
    else //apk
    {
      // requestInstallView(downloadFilePath); // Request install by view.
      // requestSucess=true;
      requestSucess=requestInstallApi(downloadFilePath); // Request install by view.
    } // else //apk

    stopDownloadNotificationService(); // 停止，下载通知服务。

    if (requestSucess)
    {
      HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); // 获取应用程序对象。
      String mWordSeparators = baseApplication.getResources().getString(R2.string.prepareInstall); // 读取 说明 字符串。Prepare install
      voiceUi.say(mWordSeparators); // 说话，prepare install
        
      notifyDownloadFinished(); // Notify download finished.
      
      notifyInstalling(); // Notify installing.
      
      baseApplication.setInstallingPackageName(packageName); // SEt the installing package name.
    } // if (requestSucess)
    else
    {
      // deleteApkFile(downloadFilePath); // Delete the apk file.
      deleteApkFile(); // Delete the apk file for this package.

      notifyDownloadFail(); // NOtify download failed.
    }
  } //private void requestInstall(String downloadFilePath)

  /**
  * Construct the install status receiver.
  */
  private IntentSender constructInstallStatusReceiver()
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); // 获取应用程序对象。

    Intent intent = new Intent(baseApplication, ApkInstallActivity.class);
    intent.setAction(PACKAGE_INSTALLED_ACTION);

    PendingIntent pendingIntent = PendingIntent.getActivity(baseApplication, 0, intent, PendingIntent.FLAG_MUTABLE);
		// PendingIntent contentIntent = PendingIntent.getActivity(baseApplication, 0, new Intent(baseApplication, LauncherActivity.class), PendingIntent.FLAG_MUTABLE);
    
    IntentSender statusReceiver = pendingIntent.getIntentSender();
    
    return statusReceiver;
  } // private IntentSender constructInstallStatusReceiver()

  /**
  * Notify installing.
  */
  private void notifyInstalling() 
  {
    if (launcherActivity!=null) // The activity exists
    {
      Handler uiHandler = new Handler(Looper.getMainLooper());

      Runnable runnable= new Runnable()
      {
        /**
        * 具体执行的代码
        */
        public void run()
        {
          // launcherActivity.reportDownloadProgress(packageName, downloaded, total); // 报告，下载 progress。
          // launcherActivity.reportDownloadFinished(packageName); // 报告，下载 finished。
          launcherActivity.reportInstalling(packageName); // notify, installing.
        } //public void run()
      };

      uiHandler.post(runnable);
    } // if (launcherActivity!=null) // The activity exists
  } // private void notifyInstalling()

  public DownloadRequestor() 
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
  
    voiceUi=new VoiceUi(baseApplication); // 创建语音交互对象。
  }

  private void deleteApkFile()
  {
    // File targetApkFile=new File(apkFilePath); // Find the file.
    // targetApkFile.delete(); // Delete the cache file.
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
    
    baseApplication.deleteApkFile(packageName); // Delete the apk file.
    
    forgetApkFilePath(); // Forget apk file path.
  } // private void deleteApkFile(String apkFilePath)

  /**
  * Show notification
  */
  private void showNotification(String contentText)
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
    String Rstringapp_name = baseApplication.getPackageName(); // The applicaiton name.
    int Rdrawableic_launcher = R2.drawable.preview573png; // The appliciton icon resource id.
    
    try 
    {
      // 获取包管理器
      PackageManager packageManager = baseApplication.getPackageManager();
      // 获取当前应用的包名
      ApplicationInfo applicationInfo = packageManager.getApplicationInfo(baseApplication.getPackageName(), 0);
      // 使用包管理器获取应用信息的可读名称
      String appName = packageManager.getApplicationLabel(applicationInfo).toString();
      // 输出应用名称
      Rstringapp_name = appName; // The applicaiton name.
      
      Rdrawableic_launcher = applicationInfo.icon; // Get the icon resource id.
    }
    catch (PackageManager.NameNotFoundException e) 
    {
      e.printStackTrace();
    }

    // In this sample, we'll use the same text for the ticker and the expanded notification
    CharSequence text = (Rstringapp_name);

    // The PendingIntent to launch our activity if the user selects this notification
    // PendingIntent contentIntent = PendingIntent.getActivity(baseApplication, 0, new Intent(baseApplication, LauncherActivity.class), 0);
		PendingIntent contentIntent = PendingIntent.getActivity(baseApplication, 0, new Intent(baseApplication, LauncherActivity.class), PendingIntent.FLAG_MUTABLE);

    String downloadingText="Downloading " + contentText; // 构造字符串，正在下载。陈欣。

    // Set the info for the views that show in the notification panel.
    Notification notification = new Notification.Builder(baseApplication)
      .setSmallIcon(Rdrawableic_launcher)  // the status icon
      .setTicker(text)  // the status text
      .setWhen(System.currentTimeMillis())  // the time stamp
      .setContentTitle((Rstringapp_name))  // the label of the entry
      .setContentText(downloadingText)  // the contents of the entry
      .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
      .setPriority(Notification.PRIORITY_HIGH)   // heads-up
      .build();

    continiusNotification=notification; //记录通知

    // Send the notification.
    mNM = (NotificationManager)baseApplication.getSystemService(Context.NOTIFICATION_SERVICE);

    mNM.notify(NOTIFICATION, notification);
  }

  /**
  * Notify download progress.
  */
  private void notifyDownloadProgress(long downloaded, long total) 
  {
    if (launcherActivity!=null)
    {
      Handler uiHandler = new Handler(Looper.getMainLooper());

      Runnable runnable= new Runnable()
      {
        /**
        * 具体执行的代码
        */
        public void run()
        {
          launcherActivity.reportDownloadProgress(packageName, downloaded, total); // 报告，下载 progress。
        } //public void run()
      };

      uiHandler.post(runnable);
    }
  } // private void notifyDownloadProgress(long downloaded, long total)
  
  /**
  * Notify download finished.
  */
  private void notifyDownloadFinished() 
  {
    if (launcherActivity!=null)
    {
      Handler uiHandler = new Handler(Looper.getMainLooper());

      Runnable runnable= new Runnable()
      {
        /**
        * 具体执行的代码
        */
        public void run()
        {
          // launcherActivity.reportDownloadProgress(packageName, downloaded, total); // 报告，下载 progress。
          launcherActivity.reportDownloadFinished(packageName); // 报告，下载 finished。
        } //public void run()
      };

      uiHandler.post(runnable);
    }
  } // private void notifyDownloadFinished() // Notify download finished.

  /**
  * 通知，下载失败。
  */
  public void  notifyDownloadFail() 
  {
    String contentText="Failed to download";
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); // 获取应用程序对象。
    ApplicationListData applicationListData = baseApplication.getApplicationListData(); //获取本地服务器列表数据对象。
    applicationListData.removeUrl(fullUrl); // Remove the download url.

    showNotification(contentText);
    if (launcherActivity!=null)
    {
      Handler uiHandler = new Handler(Looper.getMainLooper());

      Runnable runnable= new Runnable()
      {
        /**
        * 具体执行的代码
        */
        public void run()
        {
          // launcherActivity.reportDownloadProgress(packageName, downloaded, total); // 报告，下载 progress。
          // launcherActivity.reportDownloadFinished(packageName); // 报告，下载 finished。
          launcherActivity.reportDownloadFailed(packageName); // 报告，下载失败。
        } //public void run()
      };

      uiHandler.post(runnable);
    } // if (launcherActivity!=null)
    fullUrl=null; // Clear download full url.
  } //private void  notifyDownloadFail()
    
  /**
  * 请求下载指定网址的安装包。
  */
  void requestDownloadUrl(Uri uri, String refererUrl, String applicationName, String packageName)
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。

    HashMap<String, String> apkFilePathMap=baseApplication.getApkFilePathMap(); // 获取 APK 安装包路径映射。

    String apkFilePath=apkFilePathMap.get(packageName); // 获取安装包文件路径。
        
    Log.d(TAG, "requestDownloadUrl, apkFilePath: " + apkFilePath + ", package name: " + packageName+ ", map length: " + apkFilePathMap.size()); // Debug.
        
    boolean shouldDownload=false; // 是否应当下载。
        
    if (apkFilePath!=null) // 路径存在。
    {
      File apkFile=new File(apkFilePath);
        
      if (apkFile.exists()) // 文件存在。
      {
        boolean isApkFile=checkIsApkFile(apkFilePath); // 检查是不是APK文件。
      
        // 检查是否是有效的安装包文件：
        if (isApkFile) // 是APK文件
        {
//           if (baseApplication.isPackageInWhiteList(packageName)) // It is in white list. re download
//           {
//             shouldDownload=true; // 应当下载。
//           } // if (baseApplication.isPackageInWhitelist(packageName)) // It is in white list. re download
//           else // preceed as normal
          {
            requestInstall(apkFilePath); // 要求安装。
          } // else // preceed as normal
        } // if (isApkFile) // 是APK文件
        else //不是APK文件。
        {
          shouldDownload=true; // 应当下载。
        } // else //不是APK文件。
      } //if (apkFile.exists()) // 文件存在。
      else // 文件不存在
      {
        shouldDownload=true; // 应当下载。
      } //else // 文件不存在
    } //if (apkFilePath!=null) // 路径存在。
    else // 路径不存在。
    {
      shouldDownload=true; // 应当下载。
    }

    if (shouldDownload) // We should download it
    {
      Log.d(TAG, "requestDownloadUrl, download file path: " + uri); //debug.

      ApplicationListData applicationListData = baseApplication.getApplicationListData(); //获取本地服务器列表数据对象。

      fullUrl = uri.toString();

      if (applicationListData.containsUrl(fullUrl)) //已经包含这个网址。
      {
      } //if (applicationListData.containsUrl(fullUrl)) //已经包含这个网址。
      else //尚未包含这个网址。
      {
        applicationListData.addUrl(fullUrl); //记录，已经请求下载这个网址。

        Log.d(TAG, "requestDownloadUrl, url scheme: " + uri.getScheme()); //debug.

        String mWordSeparators = baseApplication.getResources().getString(R.string.startDownload); // 读取 说明 字符串。
    
        voiceUi.say(mWordSeparators); // 说话，需要解锁。

        // downloadByIon(uri); // 使用离子下载来下载。
        downloadByAndroidAsync(uri); // Downlaod by android async
      } //else //尚未包含这个网址。
    } // if (shouldDownload) // We should download it
  }

  @Override
  public void processCompleted(Exception ex, String wholePath ) 
  {
    Log.d(TAG, CodePosition.newInstance().toString()+ ", whole path: "+ wholePath); //Debug.
    if (ex != null) // There is exception
    {
      Log.d(TAG, CodePosition.newInstance().toString()+ ", download fail: "+ wholePath); //Debug.
      notifyDownloadFail(); // 报告下载失败。
    } // if (ex != null) // There is exception
    else  // No exception.
    {
      Log.d(TAG, CodePosition.newInstance().toString()+ ", whole path: "+ wholePath); //Debug.
      rememberApkFile(wholePath); // 记录安装包路径。

      if (checkIsApkFile(wholePath)) // 是安装包文件。
      {
        Log.d(TAG, CodePosition.newInstance().toString()+ ", whole path: "+ wholePath); //Debug.
        requestInstall(wholePath); // 要求安装。陈欣。
      } // if (checkIsApkFile(wholePath)) // 是安装包文件。
      else // 不是安装包。
      {
        Log.d(TAG, CodePosition.newInstance().toString()+ ", whole path: "+ wholePath); //Debug.
        notifyDownloadFail(); // 报告下载失败。
      } // else // 不是安装包。
    } // else  // No exception.
    Log.d(TAG, CodePosition.newInstance().toString()+ ", whole path: "+ wholePath); //Debug.
  } // public void processCompleted(Exception ex) 
  
  /**
  * Downlaod by android async
  */
  private void downloadByAndroidAsync(Uri uri)
  {
    String targetUrl=uri.toString(); //获取目标URL。

    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。

    String fileName=uri.getLastPathSegment(); // 获取文件名。陈欣
        
    File downloadFolder = baseApplication.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);

    String wholePath =downloadFolder.getPath()+ File.separator  + fileName;
    
    File targetFile=new File(wholePath); // The target file.
    
    AsyncHttpRequest requestObject=new AsyncHttpGet(uri);
    requestObject.setTimeout(15000);
    requestObject.setLogging(TAG+".AndroidAsync", Log.DEBUG);
    
    targetFile.getParentFile().mkdirs();
    OutputStream fout=null;
    long fileSize=targetFile.length(); // 文件尺寸。
    boolean appendTrue=true; // Append file.
    
    // QString Range="bytes="+QString::number(DownedSize)+"-";//告诉服务器从DownedSize起开始传输
    // 
    // networkRequest.setRawHeader ("Range",Range.toUtf8 ()); //Set the range header.
    requestObject.setHeader("Range", "bytes="+ fileSize + "-"); // Tell the range start.
    Log.d(TAG, CodePosition.newInstance().toString()+ ", request object: "+ requestObject.toString()); //Debug.
    
    try 
    {
      OutputStream createdfout = new BufferedOutputStream(new FileOutputStream(targetFile, appendTrue), 28192);
      fout=createdfout;
    }
    catch (FileNotFoundException e) 
    {
      e.printStackTrace();
      // fout=null;
    } // catch (FileNotFoundException e) 
    
    HttpConnectCallback connectCallback=new DownloadConnectCallback(fout, this, wholePath, launcherActivity, fileSize);
    // connectCallback.setLauncherActivity(launcherActivity);
    
    fileDownloadFutureAndroidAsync=AsyncHttpClient.getDefaultInstance().execute(requestObject, connectCallback);
    

    // cx.fileDownloadFuture= Ion.with(baseApplication)
    //   .load(targetUrl)
    //   .setTimeout(15000) //Set the time out to be 15s.
    //   .progress(new ProgressCallback() 
    //   {
    //     @Override
    //     public void onProgress(long downloaded, long total) 
    //     {
    //       Log.d(TAG, "downloadByIon, progress: " + downloaded + "/" + total + ", " + targetUrl); // 报告进度。
    //       notifyDownloadProgress(downloaded, total); // Notify download progress.
    //     }
    //   })
    //   .setLogging(TAG, Log.DEBUG)
    //   .write(new File( wholePath));
      
      
//       cx.fileDownloadFuture.setCallback(new FutureCallback<File>() 
//       {
//         @Override
//         public void onCompleted(Exception e, File file) 
//         {
//           if (e!=null) //Some error occured.
//           {
//             notifyDownloadFail(); // 报告下载失败。
//           } //if (e!=null) //Some error occured.
//           else // 下载完毕
//           {
//             {
//               requestInstall(wholePath); // 要求安装。陈欣。
//             } // if (checkIsApkFile(wholePath)) // 是安装包文件。
//             else // 不是安装包。
//             {
//               notifyDownloadFail(); // 报告下载失败。
//             } // else // 不是安装包。
//           } //else // 下载完毕
//         }
//       });
  } // private void downloadByAndroidAsync(Uri uri)

  /**
  * Cancel download.
  */
  public void cancelDownload()
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
    Ion.getDefault(baseApplication).cancelAll(baseApplication);
  } // public void cancelDownload() // Cancel download.
  
  /**
  * 使用离子下载来下载。
  * @param uri 要下载的网址。
  */
  private void downloadByIon(Uri uri)
  {
    String targetUrl=uri.toString(); //获取目标URL。

    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。

    String fileName=uri.getLastPathSegment(); // 获取文件名。陈欣
        
    File downloadFolder = baseApplication.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);

    String wholePath =downloadFolder.getPath()+ File.separator  + fileName;

    fileDownloadFuture= Ion.with(baseApplication)
      .load(targetUrl)
      .setTimeout(15000) //Set the time out to be 15s.
      // have a ProgressBar get updated automatically with the percent
      //                .progressBar(ionprogressBar1)
      // can also use a custom callback
      .progress(new ProgressCallback() 
      {
        @Override
        public void onProgress(long downloaded, long total) 
        {
          Log.d(TAG, "downloadByIon, progress: " + downloaded + "/" + total + ", " + targetUrl); // 报告进度。
          notifyDownloadProgress(downloaded, total); // Notify download progress.
        }
      })
      .setLogging(TAG, Log.DEBUG)
      .write(new File( wholePath));
      
      
      fileDownloadFuture.setCallback(new FutureCallback<File>() 
      {
        @Override
        public void onCompleted(Exception e, File file) 
        {
          // download done...
          // do stuff with the File or error

          if (e!=null) //Some error occured.
          {
            Log.d(TAG,"download error:"); //Debug.
            e.printStackTrace(); //Report error.
                            
            //                             陈欣
            notifyDownloadFail(); // 报告下载失败。
          } //if (e!=null) //Some error occured.
          else // 下载完毕
          {
            rememberApkFile(wholePath); // 记录安装包路径。

            if (checkIsApkFile(wholePath)) // 是安装包文件。
            {
              
              requestInstall(wholePath); // 要求安装。陈欣。
            } // if (checkIsApkFile(wholePath)) // 是安装包文件。
            else // 不是安装包。
            {
              notifyDownloadFail(); // 报告下载失败。
            } // else // 不是安装包。
          } //else // 下载完毕
        }
      });
  } //private void downloadByIon(Uri uri)

  /**
  * 检查是不是APK文件。
  */
  private boolean checkIsApkFile(String apkFilePath) 
  {
    boolean result=false;
    
    if (Constants.InstallerType.XAPK.equals( installerType)) // XAPK. not check
//     if (installerType.equals("XAPK")) // XAPK. not check
    {
      result=true;
    }  // if (installerType.equals("XAPK")) // XAPK. not check
    else // Check
    {
      HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
      
      PackageManager packageManager = baseApplication.getPackageManager();
      
      PackageInfo packageInfo=packageManager.getPackageArchiveInfo(apkFilePath, 0);
      
//       String expectedVersionName = baseApplication.getAvailableVersionName(packageName).trim(); // 获取可用的版本名字。 Trim trailing space.
      String expectedVersionName = baseApplication.getHighestVersionNameByType(packageName, Constants.VersionNameType.Available).trim(); // 获取可用的版本名字。 Trim trailing space.

      if (packageInfo!=null) // 有有效的包信息。
      {
        String versionName=packageInfo.versionName; // Get the versin name.

        Log.d(TAG, "checkIsApkFile, 568, version name: " + versionName + "/" + expectedVersionName); // Debug.

        VersionComparator versionComparator=new VersionComparator(); // Create version comparator.

        if ( (versionName.equals(expectedVersionName)) ||  (versionComparator.isHigerVersion(versionName, expectedVersionName))  ) // There is a higher version. Or exactly equal.
//         if (versionName.equals(expectedVersionName)) // The version name is expected.
        {
          result=true; // 是安装包。
          Log.d(TAG, "checkIsApkFile, 573, version name: " + versionName + "/" + expectedVersionName); // Debug.
        } // if (versionName==expectedVersionName)
        else // Version name not equal
        {
          if (packageName==null) // No package name provided. might be xapk.
          {
            Log.d(TAG, "checkIsApkFile, 579, version name: " + versionName + "/" + expectedVersionName); // Debug.
            result=true; // It is apk file.
          } // if (packageName==null) // No package name provided
          else // package name provided
          {
            Log.d(TAG, "checkIsApkFile, 604, version name: " + versionName + "/" + expectedVersionName + ", apk version name not match, deleting apk file: " + apkFilePath); // Debug.
            File targetApkFile=new File(apkFilePath); // Find the file.
            targetApkFile.delete(); // Delete the cache file.
            
            forgetApkFilePath(); // Forget apk file path.
          } // else // package name provided
        } // else // Version name not equal
        Log.d(TAG, "checkIsApkFile, 583, version name: " + versionName + "/" + expectedVersionName); // Debug.
      } // if (packageInfo!=null) // 有有效的包信息。
      else // Not valid package
      {
        File targetApkFile=new File(apkFilePath); // Find the file.
        long fileSize=targetApkFile.length(); // 文件尺寸。
        Log.d(TAG, "checkIsApkFile, 608, file size: " + fileSize + ", file path: " + apkFilePath); // Debug.

        if (fileSize < MinimalApkSize) // File too small
        {
          targetApkFile.delete(); // Delete the cache file.
          
          forgetApkFilePath(); // Forget apk file path.
        } // if (fileSize < MinimalApkSize) // File too small
      } // else // Not valid package
    } // else // Check

    return result;
  } // private boolean checkIsApkFile(String apkFilePath)

  public void requestDownloadUrl(String url, String refererUrl, String applicatinName, String packageName, String installerType)
  {
    this.packageName=packageName;
    this.installerType=installerType; // Remember installer type.

    Uri uri=Uri.parse(url);

    requestDownloadUrl(uri, refererUrl, applicatinName, packageName);
  } // public void requestDownloadUrl(String url, String refererUrl, String applicatinName, String packageName, String installerType)
}
