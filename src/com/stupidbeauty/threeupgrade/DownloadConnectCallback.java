package com.stupidbeauty.threeupgrade;

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
import com.stupidbeauty.placeholder.R;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
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
import com.stupidbeauty.placeholder.R2;
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
import android.widget.Toast;
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

public class DownloadConnectCallback implements HttpConnectCallback
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
  private static final String TAG="DownloadConnectCallback"; //!< The tag used in debug code.
  private final OutputStream fout;
  private DownloadConnectCallbackInterface downloadRequestor; //!< The download requestor object.
  private long mDownloaded = 0;
  private String wholePath ; //!< The while path.
  private long rangeSize=0; //!< Range size.
  
  public DownloadConnectCallback(OutputStream fout, DownloadConnectCallbackInterface downloadRequestor, String wholePath , ApplicationInformationActivity launcherActivity, long rangeSize)
  {
    this.rangeSize=rangeSize;
    
    mDownloaded=rangeSize;
    
    this.fout=fout;
    this.downloadRequestor=downloadRequestor;
    this.wholePath=wholePath;
    this.launcherActivity=launcherActivity;
  } // public DownloadConnectCallback(OutputStream fout)
  
  @Override
  public void onConnectCompleted(Exception ex, final AsyncHttpResponse response) 
  {
    if (ex != null) // Some error occured
    {
      Log.d(TAG, CodePosition.newInstance().toString()+ ", connect error: "); // Debug.
      ex.printStackTrace(); // Debug.
      // return;
      downloadRequestor.processCompleted(ex, wholePath);
    } // if (ex != null) // Some error occured
    else // Connect success
    {
    long remainingcontentLength = HttpUtil.contentLength(response.headers());
    Log.d(TAG, CodePosition.newInstance().toString()+ ", remaining content length: "+ remainingcontentLength); // Debug.
    final long contentLength = remainingcontentLength + rangeSize;
    Log.d(TAG, CodePosition.newInstance().toString()+ ", content length: "+ contentLength); // Debug.
    
    if (contentLength < rangeSize) // range size too big.
    {
      downloadRequestor.processCompleted(ex, wholePath);
      Log.d(TAG, CodePosition.newInstance().toString()+ ", content length: "+ contentLength + ", range size: " + rangeSize); // Debug.
    } // if (contentLength < rangeSize) // range size too big.
    else // Normal
    {
      response.setDataCallback(new OutputStreamDataCallback(fout) 
      {
        @Override
        public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) 
        {
          mDownloaded += bb.remaining();
          super.onDataAvailable(emitter, bb);
          notifyDownloadProgress(mDownloaded, contentLength); // Notify download progress.
          // invokeProgress(callback, response, mDownloaded, contentLength);
        } // public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) 
      });

      response.setEndCallback(new CompletedCallback() 
      {
        @Override
        public void onCompleted(Exception ex) 
        {
          try 
          {
            fout.close();
          }
          catch (IOException e) 
          {
            ex = e;
          }
              
          downloadRequestor.processCompleted(ex, wholePath); // Process the completed situation.
        }
      });
    } // else // Normal

    } // else // Connect success

  }
  
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

    String mWordSeparators = baseApplication.getResources().getString(R.string.prepareInstall); // 读取 说明 字符串。Prepare install

    voiceUi.say(mWordSeparators); // 说话，prepare install
    baseApplication.startActivity(intent);
      
    stopDownloadNotificationService(); // 停止，下载通知服务。
    notifyDownloadFinished(); // Notify download finished.
  } //private void requestInstall(String downloadFilePath)

  /**
  * Construct the install status receiver.
  */
  private IntentSender constructInstallStatusReceiver()
  {
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); // 获取应用程序对象。
    // Create an install status receiver.
    //                     Context context = InstallApkSessionApi.this;
    Intent intent = new Intent(baseApplication, ApkInstallActivity.class);
    intent.setAction(PACKAGE_INSTALLED_ACTION);
    PendingIntent pendingIntent = PendingIntent.getActivity(baseApplication, 0, intent, 0);
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
      launcherActivity.reportInstalling(packageName); // 报告，installing。
    } // if (launcherActivity!=null) // The activity exists
  } // private void notifyInstalling()

  /**
  * Show notification
  */
  private void showNotification(String contentText)
  {
    // In this sample, we'll use the same text for the ticker and the expanded notification
    HxLauncherApplication baseApplication = HxLauncherApplication.getInstance(); //获取应用程序对象。
    CharSequence text = baseApplication.getText(R.string.app_name);

    // The PendingIntent to launch our activity if the user selects this notification
    PendingIntent contentIntent = PendingIntent.getActivity(baseApplication, 0, new Intent(baseApplication, LauncherActivity.class), 0);
  
    String downloadingText="Downloading " + contentText; // 构造字符串，正在下载。陈欣。

    // Set the info for the views that show in the notification panel.
    Notification notification = new Notification.Builder(baseApplication)
      .setSmallIcon(R.drawable.ic_launcher)  // the status icon
      .setTicker(text)  // the status text
      .setWhen(System.currentTimeMillis())  // the time stamp
      .setContentTitle(baseApplication.getText(R.string.app_name))  // the label of the entry
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
      launcherActivity.reportDownloadFinished(packageName); // 报告，下载 finished。
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
      launcherActivity.reportDownloadFailed(packageName, Constants.DownloadFailureReason.DownloadError); // 报告，下载失败。
    } // if (launcherActivity!=null)
    fullUrl=null; // Clear download full url.
  } //private void  notifyDownloadFail()
}
