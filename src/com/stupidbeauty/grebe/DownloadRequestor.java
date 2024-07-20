package com.stupidbeauty.grebe;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Handler;
import android.os.Looper;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.app.Application;
import android.os.Looper;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.app.Application;
import android.os.Looper;
import java.util.Timer;
import java.util.TimerTask;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import com.stupidbeauty.voiceui.VoiceUi;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.content.FileProvider;
import android.database.ContentObserver;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import com.stupidbeauty.hxlauncher.bean.ApplicationListData;

import com.stupidbeauty.placeholder.R;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;

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
import com.stupidbeauty.hxlauncher.rpc.DownloadResult;
import com.stupidbeauty.hxlauncher.rpc.DownloadListener;
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
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import com.stupidbeauty.hxlauncher.bean.ApplicationListData;
// import com.stupidbeauty.hxlauncher.rpc.CloudRequestorZzaqwb;
import com.stupidbeauty.hxlauncher.rpc.RecognizerResult;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class DownloadRequestor
{
  private Timer timerObj = null; //!< The timer of cancelling download when no progress for a long time.
private static final String PACKAGE_INSTALLED_ACTION = "com.example.android.apis.content.SESSION_API_PACKAGE_INSTALLED";

  private Notification continiusNotification=null; //!<记录的通知
  private DownloadRequestorInterface launcherActivity=null; //!< 启动活动。
  private int NOTIFICATION = 84951; //!< 通知编号。陈欣
  private VoiceUi voiceUi=null; //!< 语音交互对象。

  private String packageName=null; //!< 包名。
  private String fullUrl = null; //!< Downloading full url.

  public Future<File> fileDownloadFuture; //!<The file download future.
  private NotificationManager mNM;

  private static final String TAG="DownloadRequestor"; //!<输出调试信息了时使用的标记

  // private CloudRequestorZzaqwb cloudRequestorZzaqwb=new CloudRequestorZzaqwb(); //!<云端请求发送器

  private long downloadId; //!<当前的下载编号

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
  private void requestInstall(String downloadFilePath)
  {
    notifyDownloadFinished(); // Notify download finished.
    requestInstallApi(downloadFilePath); // Request install by view.
  } //private void requestInstall(String downloadFilePath)

  /**
  * Notify download finished.
  */
  private void notifyDownloadFinished() 
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
    ApplicationListData applicationListData = baseApplication.getApplicationListData(); //获取本地服务器列表数据对象。
    applicationListData.removeUrl(fullUrl); // Remove the download url.
  
    if (launcherActivity!=null)
    {
      launcherActivity.reportDownloadFinished(packageName); // 报告，下载 finished。
    }

    packageName=null; // Clear package name.
    fullUrl=null; // Clear download full url.
  } // private void notifyDownloadFinished() // Notify download finished.
  
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

    FileUtils.copyFile(apkFileObject, packageInSession); // Copy to output stream.

    packageInSession.close();
  } // private void addApkToInstallSession(String assetName, PackageInstaller.Session session) throws IOException 

  /**
  * 要求安装应用
  * @param downloadFilePath 应用安装包路径
  */
  private void requestInstallApi(String downloadFilePath)
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。

    //   https://github.com/aosp-mirror/platform_development/blob/master/samples/ApiDemos/src/com/example/android/apis/content/InstallApkSessionApi.java

    PackageInstaller.Session session = null;
    try 
    {
      PackageInstaller packageInstaller = baseApplication.getPackageManager().getPackageInstaller();
      PackageInstaller.SessionParams params = new PackageInstaller.SessionParams( PackageInstaller.SessionParams.MODE_FULL_INSTALL);

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) // USER_ACTION_NOT_REQUIRED
      {
        params.setRequireUserAction(PackageInstaller.SessionParams.USER_ACTION_NOT_REQUIRED); // Silent update.
      } //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //动态权限

      int sessionId = packageInstaller.createSession(params);
      session = packageInstaller.openSession(sessionId);

      //                     addApkToInstallSession("HelloActivity.apk", session);
      addApkToInstallSession(downloadFilePath, session);

      // Create an install status receiver.
      //                     Context context = InstallApkSessionApi.this;

      // Intent intent = new Intent(baseApplication, LauncherActivity.class);
      Intent intent = new Intent(baseApplication, DownloadRequestorInterface.class);
      
      intent.setAction(PACKAGE_INSTALLED_ACTION);
      PendingIntent pendingIntent = PendingIntent.getActivity(baseApplication, 0, intent, 0);
      IntentSender statusReceiver = pendingIntent.getIntentSender();

      // Commit the session (this will start the installation workflow).
      session.commit(statusReceiver);
    }
    catch (IOException e) 
    {
      throw new RuntimeException("Couldn't install package", e);
    }
    catch (RuntimeException e) 
    {
      if (session != null) 
      {
        session.abandon();
      }
      throw e;
    }

    String mWordSeparators = baseApplication.getResources().getString(R.string.prepareInstall); // 读取 说明 字符串。Prepare install

    voiceUi.say(mWordSeparators); // 说话，prepare install

    stopDownloadNotificationService(); // 停止，下载通知服务。
  } //private void requestInstall(String downloadFilePath)

  /**
  * 要求安装应用
  * @param downloadFilePath 应用安装包路径
  */
  private void requestInstallView(String downloadFilePath)
  {
//             Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
//             intent.setData(getApkUri("HelloActivity.apk"));
//             intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//             startActivity(intent);
//    
  
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
        
    String type = "application/vnd.android.package-archive";

    File file=new File(downloadFilePath);
        
//         Intent.ACTION_INSTALL_PACKAGE
    Intent intent = new Intent(Intent.ACTION_VIEW);
//     Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
      
// 1/25/23 18:59android.content.ActivityNotFoundException: No Activity found to handle Intent { act=android.intent.action.INSTALL_PACKAGE dat=file:///storage/emulated/0/Android/data/com.stupidbeauty.hxlauncher/cache/com.cs_credit_bank.apk typ=application/vnd.android.package-archive flg=0x10000000 }

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
      }
      catch(IllegalArgumentException e)
      {
//       4/3/22 17:22java.lang.IllegalArgumentException: Failed to find configured root that contains /storage/emulated/0/download/b75da43000c64716bd5a688e65bfb370.apk

        e.printStackTrace();
        
        intent.setDataAndType(Uri.fromFile(file), type);
      } // catch(IllegalArgumentException e)
    }
    else 
    {
      intent.setDataAndType(Uri.fromFile(file), type);
    }

    Log.d(TAG, "requestInstall, starting activity to install apk"); // Debug.

    String mWordSeparators = baseApplication.getResources().getString(R.string.prepareInstall); // 读取 说明 字符串。Prepare install

    voiceUi.say(mWordSeparators); // 说话，prepare install

    baseApplication.startActivity(intent);
        
    stopDownloadNotificationService(); // 停止，下载通知服务。
  } //private void requestInstall(String downloadFilePath)

  public DownloadRequestor() 
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
    
    voiceUi=new VoiceUi(baseApplication); // 创建语音交互对象。


//       04-01 08:46:40.772  3082  5366 W DownloadManager: [3203] Stop requested with status INSUFFICIENT_SPACE_ERROR: Failed to allocate 78744689 because only 60489728 allocatable
// 04-01 08:46:40.772  3082  5366 D DownloadManager: [3203] Finished with status INSUFFICIENT_SPACE_ERROR

    // cloudRequestorZzaqwb.setContext(baseApplication); //设置上下文
      
    DownloadManager.Query q=new DownloadManager.Query(); // 构造查询对象。
      
    final DownloadManager downloadManager = (DownloadManager)baseApplication.getSystemService(Context.DOWNLOAD_SERVICE); // 获取下载管理器对象。
    baseApplication.getContentResolver().registerContentObserver(Uri.parse("content://downloads/my_downloads"),true, new ContentObserver(null) 
    {
      @Override
      public void onChange(boolean selfChange) 
      {
        super.onChange(selfChange);
        Cursor localCursor = downloadManager.query( q);
        if (localCursor.getCount() == 0) 
        {
          localCursor.close();
        }
        localCursor.moveToFirst();
        do 
        {
          if ((localCursor.getInt(localCursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) & DownloadManager.STATUS_FAILED )!=0) 
          {
            // Download failed, go see why
            if (localCursor.getInt(localCursor.getColumnIndex(DownloadManager.COLUMN_REASON)) == DownloadManager.ERROR_INSUFFICIENT_SPACE)
            {
              Log.w("DownloadStatus", " Download failed with ERROR_INSUFFICIENT_SPACE");
                                    
              long currentId=localCursor.getLong(localCursor.getColumnIndex(DownloadManager.COLUMN_ID)); // 获取当前这条记录的编号。
              // 陈欣

              if (currentId==downloadId) // 正是我们想要下载的编号
              {
                notifyDownloadFail(); // 通知下载失败。
              } // if (currentId==downloadId) // 正是我们想要下载的编号
            }
          }
        }
        while (localCursor.moveToNext());
      }
    });
  } // public DownloadRequestor() 

  private void showNotification(String contentText)
	{
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
    String Rstringapp_name = baseApplication.getPackageName(); // The applicaiton name.
    int Rdrawableic_launcher = R.drawable.preview573png; // The appliciton icon resource id.
    
    try 
    {
      // 获取包管理器
      PackageManager packageManager = baseApplication.getPackageManager();
      // 获取当前应用的包名
      ApplicationInfo applicationInfo = packageManager.getApplicationInfo(baseApplication.getPackageName(), 0);
      // 使用包管理器获取应用信息的可读名称
      String appName = packageManager.getApplicationLabel(applicationInfo).toString();
      // 输出应用名称
      // System.out.println("App Name: " + appName);
      Rstringapp_name = appName; // The applicaiton name.
      
      Rdrawableic_launcher = applicationInfo.icon; // Get the icon resource id.
    }
    catch (PackageManager.NameNotFoundException e) 
    {
      e.printStackTrace();
    }

		// In this sample, we'll use the same text for the ticker and the expanded notification
		CharSequence text = Rstringapp_name;

		// The PendingIntent to launch our activity if the user selects this notification
		PendingIntent contentIntent = PendingIntent.getActivity(baseApplication, 0, new Intent(baseApplication, DownloadRequestorInterface.class), 0);
		
		String downloadingText="Downloading " + contentText; // 构造字符串，正在下载。陈欣。

		// Set the info for the views that show in the notification panel.
		Notification notification = new Notification.Builder(baseApplication)
      .setSmallIcon(Rdrawableic_launcher)  // the status icon
      .setTicker(text)  // the status text
      .setWhen(System.currentTimeMillis())  // the time stamp
      // .setContentTitle(baseApplication.getText(R.string.app_name))  // the label of the entry
      .setContentTitle((Rstringapp_name))  // the label of the entry
      .setContentText(downloadingText)  // the contents of the entry
      .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
      .setPriority(Notification.PRIORITY_HIGH)   // heads-up
      .build();

		continiusNotification=notification; //记录通知

		// Send the notification.
    mNM = (NotificationManager)baseApplication.getSystemService(Context.NOTIFICATION_SERVICE);

		mNM.notify(NOTIFICATION, notification);
	} // private void showNotification(String contentText)

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
  * 通知，下载失败。
  */
  private void  notifyDownloadFail() 
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
    ApplicationListData applicationListData = baseApplication.getApplicationListData(); //获取本地服务器列表数据对象。
    applicationListData.removeUrl(fullUrl); // Remove the download url.
  
    String contentText="Failed to download";
      
    showNotification(contentText);
    
    if (launcherActivity!=null) // The launcher activity exists
    {
      launcherActivity.reportDownloadFailed(packageName); // 报告，下载失败。
    } // if (launcherActivity!=null) // The launcher activity exists
    
    packageName=null; // Clear package name.
    fullUrl=null; // Clear download full url.
  } //private void  notifyDownloadFail()
    
  /**
  * 请求下载指定网址的安装包。
  */
  public void requestDownloadUrl(Uri uri, String refererUrl, String applicationName, String packageName)
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
          requestInstall(apkFilePath); // 要求安装。
        } // if (isApkFile) // 是APK文件
        else //不是APK文件。
        {
          shouldDownload=true; // 应当下载。
        }
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

    if (shouldDownload) // Should download.
    {
      Log.d(TAG, "requestDownloadUrl, 347, download file path: " + uri); //debug.

      ApplicationListData applicationListData = baseApplication.getApplicationListData(); //获取本地服务器列表数据对象。

      // Toast.makeText(baseApplication, "Download Started " + applicationName, Toast.LENGTH_SHORT).show();

      fullUrl = uri.toString();

      if (applicationListData.containsUrl(fullUrl)) //已经包含这个网址。
      {
        Log.d(TAG, "requestDownloadUrl, 357, already downloading: " + uri+", ignore"); //debug.
      } //if (applicationListData.containsUrl(fullUrl)) //已经包含这个网址。
      else //尚未包含这个网址。
      {
        applicationListData.addUrl(fullUrl); //记录，已经请求下载这个网址。

        Log.d(TAG, "requestDownloadUrl, url scheme: " + uri.getScheme()); //debug.
        
        String mWordSeparators = baseApplication.getResources().getString(R.string.startDownload); // 读取 说明 字符串。
    
        voiceUi.say(mWordSeparators); // 说话，需要解锁。

        downloadByIon(uri); // 使用离子下载来下载。
      } //else //尚未包含这个网址。
    } // if (shouldDownload) // Should download.
  } // public void requestDownloadUrl(Uri uri, String refererUrl, String applicationName, String packageName)

  /**
  * 检查是不是APK文件。
  */
  private boolean checkIsApkFile(String apkFilePath) 
  {
    boolean result=false;

    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
    
    PackageManager packageManager = baseApplication.getPackageManager();
    
    PackageInfo packageInfo=packageManager.getPackageArchiveInfo(apkFilePath, 0);
    
    String expectedVersionName = baseApplication.getAvailableVersionName(packageName); // 获取可用的版本名字。

    if (packageInfo!=null) // 有有效的包信息。
    {
      String versionName=packageInfo.versionName; // Get the versin name.

      Log.d(TAG, "checkIsApkFile, version name: " + versionName + "/" + expectedVersionName); // Debug.

      if (versionName.equals(expectedVersionName)) // The version name is expected.
      {
        result=true; // 是安装包。
      } // if (versionName==expectedVersionName)
      else // Version name not equal
      {
        if (packageName==null) // No package name provided
        {
          result=true; // It is apk file.
        } // if (packageName==null) // No package name provided
      } // else // Version name not equal
    }

    return result;
  } // private boolean checkIsApkFile(String apkFilePath)

  /**
  * 使用离子下载来下载。
  * @param uri 要下载的网址。
  */
  private void downloadByIon(Uri uri)
  {
    String targetUrl=uri.toString(); //获取目标URL。

    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。

    String fileName=uri.getLastPathSegment(); // 获取文件名。陈欣
    
    if (packageName!=null) // Has package name
    {
      fileName=packageName+".apk";
    } // if (packageName!=null) // Has package name
        
    Context context = HxLauncherApplication.getAppContext();

//     File downloadFolder = baseApplication.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
    File downloadFolder = baseApplication.getExternalCacheDir();
//     File downloadFolder = context.getCacheDir();

    String wholePath =downloadFolder.getPath()+ File.separator  + fileName;
    
    Log.d(TAG, "downloadByIon, 463, url: " + targetUrl + ", path: " + wholePath); // Debug.

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
          timerObj.cancel(); // Cancel the timer of cancel.
          Log.d(TAG, "downloadByIon, 470, progress: " + downloaded + "/" + total + ", " + targetUrl); // 报告进度。
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
            Toast.makeText(baseApplication, "Download Failed" + targetUrl, Toast.LENGTH_SHORT).show();

            Log.d(TAG,"download error:"); //Debug.
            e.printStackTrace(); //Report error.
                            
            //                             陈欣
            notifyDownloadFail(); // 报告下载失败。
          } //if (e!=null) //Some error occured.
          else // 下载完毕
          {
            Toast.makeText(baseApplication, "Download Completed" + wholePath, Toast.LENGTH_SHORT).show();

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
      
      startTimeoutCancelTimer(); // Start time out cancel timer.
  } //private void downloadByIon(Uri uri)
  
  /**
  * Start time out cancel timer.
  */
  private void startTimeoutCancelTimer() 
  {
    timerObj = new Timer();
    TimerTask timerTaskObj = new TimerTask() 
    {
      public void run() 
      {
        Handler uiHandler = new Handler(Looper.getMainLooper());

        Runnable runnable= new Runnable()
        {
          /**
          * 具体执行的代码
          */
          public void run()
          {
            cancelDownload(); // Cancel download.
          } //public void run()
        };

        uiHandler.post(runnable);
      }
    };
    timerObj.schedule(timerTaskObj, 15000); // 延时启动。
  } // private void startTimeoutCancelTimer()
  
  /**
  * Cancel download.
  */
  public void cancelDownload()
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
    Ion.getDefault(baseApplication).cancelAll(baseApplication);
  } // public void cancelDownload() // Cancel download.


    public void requestDownloadUrl(String url, String refererUrl, String applicatinName, String packageName, DownloadRequestorInterface launcherActivity)
    {
      this.launcherActivity=launcherActivity;
      this.packageName=packageName;
      
      String fixedUrl=url;
      
      if (fixedUrl.startsWith("http")) // correct url.
      {
      } // if (fixedUrl.startsWith("http")) // correct url.
      else // Not full url.
      {
        fixedUrl="http://"+fixedUrl;
      } // else // Not full url.
    
      Uri uri=Uri.parse(fixedUrl);

      requestDownloadUrl(uri, refererUrl, applicatinName, packageName);
    }

    public void requestDownloadUrl(String url, String refererUrl, String applicatinName, String packageName)
    {
      requestDownloadUrl(url, refererUrl, applicatinName, packageName, null);
    }

}
