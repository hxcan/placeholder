package com.stupidbeauty.hxlauncher.service;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.LocaleList;
import android.os.Vibrator;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.stupidbeauty.placeholder.R;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.app.NotificationChannel;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
// import com.google.protobuf.InvalidProtocolBufferException;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
// import com.stupidbeauty.exist.ExistMessageContainer;
// import com.stupidbeauty.exist.ServicePublisher;
import com.stupidbeauty.lanime.Constants;
import com.stupidbeauty.shutdownat2100.Sda2FunctionName;
import com.stupidbeauty.shutdownat2100.Sda2Message;
import static com.stupidbeauty.shutdownat2100.Sda2FunctionName.CheckTime;
import com.stupidbeauty.hxlauncher.RandomApplicationActivity;

public class DownloadNotificationService extends Service
{
  private Notification continiusNotification=null; //!<记录的通知
  private int NOTIFICATION = 163731;

  private NotificationManager mNM;

  private long lastPublishTimestamp=0; //!<记录的上次发布局域网服务的时间戳。
  private String callbackIp="127.0.0.1"; //!<回调的IP。

  private static final String TAG = "DownloadNotificationService"; //!< 输出调试信息时使用的标记。
  private static final String LanServiceName = "com.stupidbeauty.shutdownat2100.android"; //!<局域网服务名字。
  private static final int LanServicePort = 9521; //!<局域网服务的端口号。
  // private static final ExistMessageContainer.ServicePublishMessage.ServiceProtocolType LanServiceProtocolType = ExistMessageContainer.ServicePublishMessage.ServiceProtocolType.UDP; //!<服务协议类型是UDP。

  @Override
  public IBinder onBind(Intent intent) 
  {
    return null;
  }
	
  @Override
  /**
  * 服务被启动。也可能是重新启动。
  */
  public int onStartCommand(Intent intent, int flags, int startId) 
  {
    Log.d(TAG,"onStartCommand,180"); //Debug.
    
        String Rstringapp_name = getPackageName(); // The applicaiton name.
    int Rdrawableic_launcher = R.drawable.preview573png; // The appliciton icon resource id.
    
    try 
    {
      // 获取包管理器
      PackageManager packageManager = getPackageManager();
      // 获取当前应用的包名
      ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
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


    String contentText = Rstringapp_name;

    if (intent!=null)
    {
      Bundle extras=intent.getExtras(); //获取参数包。
      String packageName=extras.getString("applicationName"); //get the progress value.

      contentText = packageName;
    }

    showNotification(contentText);

    startForeground(NOTIFICATION, continiusNotification); //显示在前台
		
    return START_STICKY; //被杀死时，自动重启。
  } //public int onStartCommand(Intent intent, int flags, int startId)

  private void showNotification(String contentText)
  {
    String Rstringapp_name = getPackageName(); // The applicaiton name.
    int Rdrawableic_launcher = R.drawable.preview573png; // The appliciton icon resource id.
    
    try 
    {
      // 获取包管理器
      PackageManager packageManager = getPackageManager();
      // 获取当前应用的包名
      ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
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
    PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, RandomApplicationActivity.class), 0);
		
    String downloadingText="Downloading " + contentText; // 构造字符串，正在下载。陈欣。

    Notification.Builder notificationBuilder= new Notification.Builder(this);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) // NotificationChannel
    {
      NotificationChannel chan = new NotificationChannel( "#include", "My Foreground Service", NotificationManager.IMPORTANCE_LOW);
              
      mNM.createNotificationChannel(chan);
      notificationBuilder.setChannelId("#include");
    } //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //动态权限

    // Set the info for the views that show in the notification panel.
    
    Notification notification = notificationBuilder
      .setSmallIcon(R.drawable.ic_launcher)  // the status icon
      .setTicker(text)  // the status text
      .setWhen(System.currentTimeMillis())  // the time stamp
      .setContentTitle(Rstringapp_name)  // the label of the entry
      .setContentText(downloadingText)  // the contents of the entry
      .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
      .setPriority(Notification.PRIORITY_HIGH)   // heads-up
      .build();

    continiusNotification=notification; //记录通知

    // Send the notification.
    mNM.notify(NOTIFICATION, notification);
  }

  /**
  * Main initialization of the input method component.  Be sure to call
  * to super class.
  */
  @Override
  public void onCreate()
  {
    super.onCreate(); //创建超类。

    mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

    // String contentText = getString(R.string.app_name);

// 		showNotification(contentText);

		startHttpServer(); //启动HTTP服务器

		listenClipboard(); //监听剪贴板。
	} //public void onCreate()

	/**
	 * 监听剪贴板。
	 */
	private void listenClipboard()
	{
		ClipboardManager cb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

		cb.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {

        @Override
        /**
            * 主剪贴板内容发生变化。
            */
        public void onPrimaryClipChanged()
        {
            // 具体实现
            ClipboardManager cb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

            ClipData clipData=cb.getPrimaryClip(); //获取剪贴板数据。

            ClipData.Item item=clipData.getItemAt(0); //获取当前条目。

            if (item!=null) //条目不为空指针。
            {
                CharSequence charSequence=item.getText(); //获取字节序列。

                if (charSequence!=null) //字节序列不为空指针。
                {
                    String clipContent=charSequence.toString(); //获取剪贴板内容。

                    Log.d(TAG,"onPrimaryClipChanged,剪贴板内容："+clipContent); //Debug.

                } //if (charSequence!=null) //字节序列不为空指针。
            } //if (item!=null) //条目不为空指针。
        }  //public void onPrimaryClipChanged()
    });
} //private void listenClipboard()

	/**
	 * 启动HTTP服务器，用于对同一个局域网内其它平板的请求进行响应.
	 **/
	private void startHttpServer()
	{
		AsyncHttpServer server=new AsyncHttpServer(); //Create the async server.

		HttpServerRequestCallback callback=new HttpServerRequestCallback()
		{
			@Override
			public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response)
			{
				response.send("Hello!!!"); //是啊，今天休息。等下出去晒太阳。
			} //public void onRequest(AsyncHttpServerRequest request,AsyncHttpServerResponse response)
		};
		server.get("/", callback); //设置路径对应的回调对象.
	} //private void startHttpServer()

	/**
	 * 记住远端的IP。
	 */
	private void rememberRemoteIp()
	{

	} //private void rememberRemoteIp()

}
