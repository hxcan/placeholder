package com.stupidbeauty.blindbox.activity;

import com.stupidbeauty.blindbox.manager.ShareManager;
import com.stupidbeauty.voiceui.VoiceUi;
import com.stupidbeauty.codeposition.CodePosition;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.BufferedReader;
import com.koushikdutta.async.future.FutureCallback;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.stupidbeauty.hxlauncher.LauncherActivity;
import android.widget.Button;
import com.bumptech.glide.Glide;
import com.stupidbeauty.placeholder.R2;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import com.bumptech.glide.Glide;
import com.stupidbeauty.threeupgrade.DownloadRequestor;
import com.stupidbeauty.placeholder.R2;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.view.View;
import com.stupidbeauty.hxlauncher.manager.ActiveUserReportManager;
// import android.os.Debug;
// import com.stupidbeauty.hxlauncher.asynctask.LoadBuiltinVoicePackageNameMapTask;
import com.stupidbeauty.hxlauncher.asynctask.LoadVoicePackageNameMapTask;
import 	java.util.Timer;
// import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
// import android.app.ActivityOptions;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
// import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
// import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
// import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
// import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
// import android.net.ConnectivityManager;
// import android.net.NetworkInfo;
// import android.os.Build;
import android.os.Bundle;
import org.apache.commons.collections4.SetValuedMap;
import android.util.Pair;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
// import com.andexert.library.RippleView;
// import com.example.administrator.douyin.Love2;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.stupidbeauty.hxlauncher.activity.ApplicationInformationActivity;
import com.android.volley.RequestQueue;
import com.stupidbeauty.threeupgrade.AnswerAvailableEvent;
import com.stupidbeauty.placeholder.R2;
// import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.LanImeUncaughtExceptionHandler;
// import com.stupidbeauty.hxlauncher.bean.HxShortcutInfo;
// import com.stupidbeauty.hxlauncher.callback.LauncherAppsCallback;
// import com.stupidbeauty.hxlauncher.datastore.LauncherIconType;
import com.stupidbeauty.qtdocchinese.ArticleInfo;
// import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.stupidbeauty.hxlauncher.bean.ApplicationListData;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.io.FileUtils;
// import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import static android.content.Intent.ACTION_PACKAGE_CHANGED;
import static android.content.Intent.ACTION_PACKAGE_REPLACED;
import static android.content.Intent.EXTRA_PACKAGE_NAME;
// import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_DYNAMIC;
// import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_MANIFEST;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_PINNED;
import static com.stupidbeauty.hxlauncher.Constants.LanImeAction.PackageNameOfInputting;
import android.os.Process;

public class ApkInstallActivity extends Activity
{
  private VoiceUi voiceUi=null; //!< 语音交互对象。
  private static final String PACKAGE_INSTALLED_ACTION = "com.example.android.apis.content.SESSION_API_PACKAGE_INSTALLED"; //!< The aciton of package installed.
  private String packageName=""; //!< package name
  private String applicationName=""; //!< 应用程序名字
  // @BindView(R2.id.launchRipple) RippleView launchRipple; //!<用于转场动画的视图对象
  @BindView(R2.id.changeApplicationButtonion) Button changeApplicationButtonion; //!< open application
  @BindView(R2.id.doneButtonn) Button doneButtonn; //!< done button.
  @BindView(R2.id.applicationIconrightimageView2) ImageView applicationIconrightimageView2; //!<应用程序图标

  @BindView(R2.id.rightTextoperationMethodactTitletextView2) TextView rightTextoperationMethodactTitletextView2; //!<应用程序名字
  
  @BindView(R2.id.launcher_activity) RelativeLayout launcher_activity; //!<整个启动活动

  @BindView(R2.id.applicationNameTextView2) TextView applicationNameTextView2; //!< 介绍文字标签。

  private HashMap<String, Long> packageItemLastLaunchTimestampMap=new HashMap<>(); //!<包名加类名的字符串与最后一次启动时间戳之间的映射。
  private HashMap<String, String> serverVoiceCommandResponseIgnoreMap=null; //!<服务器的回复中，要忽略掉的关系映射

  private boolean foundActivity=false; //!<是否命中了活动。

  @BindView(R2.id.wallpaper) ImageView wallpaper; //!<墙纸视图。
  @BindView(R2.id.clickToGetRandomApplication2) TextView clickToGetRandomApplication2; //!< application name. text view.
  @BindView(R2.id.progressBar) ProgressBar progressBar; //!<进度条。

  private String voiceRecognizeResultString; //!<语音识别结果。

  int ret = 0;

  @BindView(R2.id.statustextView) TextView statustextView; //!<用来显示状态的文字标签。

  private boolean voiceEndDetected=false; //!<是否已经探测到用户声音结束。

  private final int MSG_LOAD_MORE = 2;
  private boolean mIsLastPage = true;

    private int mCurrMsg = -1;

    private RequestQueue mQueue; //!<Volley请求队列。
    
    private ArrayList<ArticleInfo> builtinShortcuts =null; //!< 内置快捷方式列表。
    
    /**
     * 根据包名启动应用程序。
     * @param packageName 包名。
     */
    private boolean launchApplicationByPackageName(String packageName)
    {
        boolean result=false; //启动结果
        PackageManager packageManager=getPackageManager(); //获取软件包管理器。

        Intent launchIntent= packageManager.getLaunchIntentForPackage(packageName); //获取当前软件包的启动意图。

        if (launchIntent!=null) //意图存在。
        {
          try //尝试启动活动，并且捕获可能的异常。
          {


            launchApplication(launchIntent); //启动活动。

            result=true; //成功
          } //try //尝试启动活动，并且捕获可能的异常。
          catch (ActivityNotFoundException exception)
          {
            exception.printStackTrace(); //报告错误。
          } //catch (ActivityNotFoundException exception)
        } //if (launchIntent!=null) //意图存在。
        //意图不存在，则说明对应的应用不存在，后续应当触发自动下载。
        //else //意图不存在，则说明对应的应用不存在，后续应当触发自动下载。

        return result;
    } //private void launchApplicationByPackageName(String packageName)

    @OnClick(R2.id.doneButtonn)
    /**
     * 显示应用程序信息活动
     * @param itemView 视图对象
     * @param packageName 包名
     */
    public void finishInstallOk()
    {
      Intent launchIntent=new Intent(this, LauncherActivity.class); //启动意图。

      startActivity(launchIntent); //启动活动。
      
      finish();
    } // public void finishInstallOk()

    /**
     * 启动应用。
     * @param launchIntent 启动用的意图。
     */
    @OnClick(R2.id.changeApplicationButtonion)
    public void launchApplication()
    {
      try //尝试启动活动，并且捕获可能的异常。
      {
        launchApplicationByPackageName(packageName); //按照包名启动。
      } //try //尝试启动活动，并且捕获可能的异常。
      catch (ActivityNotFoundException exception)
      {
        exception.printStackTrace(); //报告错误。
      } //catch (ActivityNotFoundException exception)
      catch (SecurityException exception) //安全异常。
      {
        exception.printStackTrace(); //报告错误。
      } //catch (SecurityException exception) //安全异常。
    } //private void launchApplication(Intent launchIntent)
    
    /**
     * 启动应用。
     * @param launchIntent 启动用的意图。
     */
    public boolean launchApplication(Intent launchIntent)
    {
      boolean result=false; //结果

      Log.d(TAG, "launchApplication, launch intent: " + launchIntent); //Debug.
      try //尝试启动活动，并且捕获可能的异常。
      {
        if (launchIntent!=null) //启动意图存在。
        {
          startActivity(launchIntent); //启动活动。
        } //                    if (launchIntent!=null) //启动意图存在。

        result=true; //启动成功
      } //try //尝试启动活动，并且捕获可能的异常。
      catch (ActivityNotFoundException exception)
      {
        exception.printStackTrace(); //报告错误。
      } //catch (ActivityNotFoundException exception)
      catch (SecurityException exception) //安全异常。
      {
        exception.printStackTrace(); //报告错误。
      } //catch (SecurityException exception) //安全异常。

      return result;
    } //private void launchApplication(Intent launchIntent)

    @OnClick(R2.id.shareIcon)
    public void shareViaText()
    {
      ShareManager shareManager=new ShareManager();
      
      shareManager.shareViaText(this, applicationName, packageName); // Share via text.
    
//       //       Chen xin
// 
//       /*Create an ACTION_SEND Intent*/
//       Intent intent = new Intent(android.content.Intent.ACTION_SEND);
// 
//       /*This will be the actual content you wish you share.*/
// 
//       String shareBody = getString(R.string.sharing_application) + applicationName + "\n" + getString(R.string.copyingTextAndOpenBlindBox) + "\n " + constructBlindBoxUrl(packagename) + " \n" + getString(R.string.blindBoxCanBeDownloadedAt);
// 
    } // public void shareViaText()

    /**
    * Show application information.
    */
    private void showApplicationInformation(String packageName)
    {
      HxLauncherApplication hxlauncherApplication=HxLauncherApplication.getInstance(); // 获取应用对象。
      
      HxLauncherApplication application=HxLauncherApplication.getInstance(); //获取应用程序对象。

      HashMap<String, Drawable> launchIconMap=application.getLaunchIconMap(); //获取启动图标缓存。

      Drawable applicationIcon=hxlauncherApplication.getApplicationIcon(packageName); // 获取图标。 local icon
      
      if (applicationIcon!=null) // The icon exists locally.
      {
        Glide.with(application).load("").placeholder(applicationIcon).into(applicationIconrightimageView2); // 显示图标。
      } // if (applicationIcon!=null) // The icon exists locally.
      else // Show online icon
      {
        String iconUrl=application.getIconForPackage(packageName); // Get icon url.
        
        Glide.with(application).load(iconUrl).placeholder(R2.drawable.adfed9a77be62c3c43a203c1305).into(applicationIconrightimageView2); //显示图标。
        //       Glide.with(application).load(iconUrl).into(applicationIconrightimageView2); // 显示图标。
      } // else // Show online icon
      
      Map<String, String> packageNameApplicationMap=hxlauncherApplication.getPackageNameApplicationNameMap(); // 获取包名与应用程序名字的映射

      if (packageNameApplicationMap!=null)
      {
        applicationName=packageNameApplicationMap.get(packageName); // 应用程序名字
      } // if (packageNameApplicationMap!=null)

      clickToGetRandomApplication2.setText(applicationName); // Show application name.
    } // private void showApplicationInformation(String packageName)

    @BindView(R2.id.articleListmy_recycler_view) RecyclerView mRecyclerView; //!<回收视图。

    private static final String TAG="ApkInstallActivity"; //!< 输出调试信息时使用的标记。
    
    /**
     * 要求显示系统的墙纸在本活动后面。
     */
    private void askShowSystemWallpaper()
    {
      WindowManager.LayoutParams p=getWindow().getAttributes();
      p.flags |= WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER;
    } //private void askShowSystemWallpaper()
    
    @Override
    /**
     * 活动被创建。
     */
    protected void onCreate(Bundle savedInstanceState)
    {
      super.onCreate(savedInstanceState);

      voiceUi=new VoiceUi(this); // 创建语音交互对象。

      askShowSystemWallpaper(); //要求显示系统的墙纸在本活动后面。

      requestWindowFeature(Window.FEATURE_NO_TITLE); //不显示标题栏。

      setContentView(R2.layout.apk_install_activity); // 显示界面。

      ButterKnife.bind(this); //视图注入。
      
      processIntent(getIntent()); // Process intent.
    } //protected void onCreate(Bundle savedInstanceState)
    
    private void processIntent(Intent intent)
    {
      Log.d(TAG, "processIntent, intent: " + intent); //Debug.
      String action=intent.getAction(); //获取动作。

      if (PACKAGE_INSTALLED_ACTION.equals(intent.getAction())) // Installation information.
      {
        Bundle extras = intent.getExtras();

        int status = extras.getInt(PackageInstaller.EXTRA_STATUS);
        String message = extras.getString(PackageInstaller.EXTRA_STATUS_MESSAGE);
        packageName=extras.getString(PackageInstaller.EXTRA_PACKAGE_NAME);
        
        if (packageName==null) // No package name provided
        {
          HxLauncherApplication application=HxLauncherApplication.getInstance(); //获取应用程序对象。
          packageName = application.getInstallingPackageName(); // Get the installing package name.
        } // if (packageName==null) // No package name provided
        
        showApplicationInformation(packageName); // Show application information.
            
        Log.d(TAG, "processIntent, status: " + status + ", message: " + message + ", extra: " + extras); // Debug.

        statustextView.setText(R2.string.installing); // Show install finished.

        switch (status) 
        {
          case PackageInstaller.STATUS_PENDING_USER_ACTION:
            // This test app isn't privileged, so the user has to confirm the install.
            changeApplicationButtonion.setVisibility(View.INVISIBLE);
            doneButtonn.setVisibility(View.INVISIBLE);
            
            Intent confirmIntent = (Intent) extras.get(Intent.EXTRA_INTENT);
            startActivity(confirmIntent);
            break;

          case PackageInstaller.STATUS_SUCCESS:
            statustextView.setText(R2.string.installFinished); // Show install finished.
            changeApplicationButtonion.setVisibility(View.VISIBLE);
            doneButtonn.setVisibility(View.VISIBLE);
            String mWordSeparators = getResources().getString(R2.string.installFinished); // 读取 说明 字符串。download failed

            voiceUi.say(mWordSeparators); // 说话，prepare install
            requestCleanStorage(); // Request clean storage.

            break;

          case PackageInstaller.STATUS_FAILURE:
          case PackageInstaller.STATUS_FAILURE_ABORTED:
          case PackageInstaller.STATUS_FAILURE_BLOCKED:
          case PackageInstaller.STATUS_FAILURE_CONFLICT:
          case PackageInstaller.STATUS_FAILURE_INCOMPATIBLE:
            handleFailed(message, status);
            break;

          case PackageInstaller.STATUS_FAILURE_INVALID:
            handleFailed(message, status);
            requestRemoveApkFile(packageName); // Request to remove the apk file.
            break;

          case PackageInstaller.STATUS_FAILURE_STORAGE:
            handleFailed(message, status);
            requestCleanStorage(); // Request clean storage.
            break;

          default:
            Toast.makeText(this, "Unrecognized status received from installer: " + status, Toast.LENGTH_SHORT).show();
        }
      } // if (PACKAGE_INSTALLED_ACTION.equals(intent.getAction())) // Installation information.
      else // Not installation information
      {
        statustextView.setText(R2.string.installing); // Show install finished.
      } // else // Not installation information
    } // private void processIntent(intent)
    
    /**
    * Request to remove the apk file.
    */
    private void requestRemoveApkFile(String packageName)
    {
      HxLauncherApplication hxlauncherApplication=HxLauncherApplication.getInstance(); // 获取应用对象。
      
      Log.d(TAG, CodePosition.newInstance().toString() + ", package name: " + packageName); //Debug.
      // 陈欣
      
      hxlauncherApplication.deleteApkFile(packageName); // Delkete the apk file of this package.
    } // private void requestRemoveApkFile(String packageName)
    
    /**
    * Request clean storage.
    */
    private void requestCleanStorage() 
    {
      HxLauncherApplication hxlauncherApplication=HxLauncherApplication.getInstance(); // 获取应用对象。
      
      hxlauncherApplication.startStorageCleaner();
    } // private void requestCleanStorage()
    
    /**
    * Handle failed.
    */
    private void handleFailed(String message, int status)
    {
      Toast.makeText(this, "Install failed! " + status + ", " + message, Toast.LENGTH_SHORT).show();
      
      HashMap<Integer, String> statusTextMap = new HashMap(); // The status text map.
      
      statusTextMap.put(PackageInstaller.STATUS_FAILURE_INVALID, "STATUS_FAILURE_INVALID"); // Invalid.
      
      
      String statusText = statusTextMap.get(status); // Get the status text.
      
      statustextView.setText(getResources().getString(R2.string.installFailed) + ": " + statusText); // Show install failed.
      doneButtonn.setVisibility(View.VISIBLE);
    } // private void handleFailed()

    @Override
    protected void onNewIntent(Intent intent)
    {
      Log.d(TAG, "onNewIntent, intent: " + intent); //Debug.
      super.onNewIntent(intent);

      processIntent(intent);
    }
}
