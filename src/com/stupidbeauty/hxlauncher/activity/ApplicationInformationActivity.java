package com.stupidbeauty.hxlauncher.activity;

import io.github.g00fy2.versioncompare.Version;
import com.stupidbeauty.hxlauncher.interfaces.LocalServerListLoadListener;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import java.io.UnsupportedEncodingException;
import static com.stupidbeauty.blindbox.Constants.Networks.RabbitMQPassword;
import static com.stupidbeauty.blindbox.Constants.Networks.RabbitMQUserName;
import static com.stupidbeauty.blindbox.Constants.Networks.TRANSLATE_REQUEST_QUEUE_NAME;
import com.stupidbeauty.appstore.core.DownloadFailureReporter;
import com.stupidbeauty.blindbox.manager.ShareManager;
import com.stupidbeauty.voiceui.VoiceUi;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.LocaleList;
import android.os.Vibrator;
import com.stupidbeauty.upgrademanager.logic.VersionComparator;
import io.github.g00fy2.versioncompare.Version;
import com.stupidbeauty.hxlauncher.interfaces.LocalServerListLoadListener;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import android.text.TextUtils;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.stupidbeauty.placeholder.R;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.LocaleList;
// import android.os.Vibrator;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.stupidbeauty.threeupgrade.DownloadRequestor;
import com.stupidbeauty.hxlauncher.ApplicationInformationAdapter;
import com.stupidbeauty.hxlauncher.Constants;
import com.stupidbeauty.placeholder.R2;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.stupidbeauty.threeupgrade.AnswerAvailableEvent;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.g00fy2.versioncompare.Version;
import com.stupidbeauty.hxlauncher.interfaces.LocalServerListLoadListener;
// import com.stupidbeauty.hxlauncher.rpc.CloudRequestorZzaqwb;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import static android.content.Intent.ACTION_PACKAGE_CHANGED;
import static android.content.Intent.ACTION_PACKAGE_REPLACED;
import static android.content.Intent.EXTRA_COMPONENT_NAME;
import static android.content.Intent.EXTRA_PACKAGE_NAME;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_DYNAMIC;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_MANIFEST;

public class ApplicationInformationActivity extends Activity implements LocalServerListLoadListener
{
  private VoiceUi voiceUi=null; //!< 语音交互对象。
  private DownloadFailureReporter downloadFailureReporter=new DownloadFailureReporter(); //!< download failur reporetr.

  public static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";
  @BindView(R2.id.totalmbsionView) TextView totalmbsionView; //!< Total mb view.
  @BindView(R2.id.downloadedmbionView) TextView downloadedmbionView; //!< Downloade MB.
  @BindView(R2.id.textProgresstantLayout) RelativeLayout textProgresstantLayout; //!< Txt progress layout.
  private String applicationName=""; //!< 应用程序名字
  @BindView(R2.id.applicationName2) TextView applicationName2; //!< Application name text view.

  // View name of the header title. Used for activity scene transitions
  public static final String VIEW_NAME_HEADER_TITLE = "detail:header:title";

  private     AnimationDrawable rocketAnimation; //!<录音按钮变暗

  @BindView(R2.id.hitApplicationIcon) ImageView hitApplicationIcon; //!<命中的应用的图标。
  @BindView(R2.id.microphoneIcon) ImageView microphoneIcon; //!< 升级按钮图标。

  @BindView(R2.id.launcher_activity) RelativeLayout launcher_activity; //!<整个启动活动

  private HashMap<String, Long> packageItemLastLaunchTimestampMap=new HashMap<>(); //!<包名加类名的字符串与最后一次启动时间戳之间的映射。

    private HashMap<String, String> serverVoiceCommandResponseIgnoreMap=new HashMap<>(); //!<服务器的回复中，要忽略掉的关系映射

    private HashMap<String, ShortcutInfo> shortcutIdInfoMap; //!<快捷方式的编号与快捷方式对象本身的映射
    private HashMap<String, Integer> packageNameItemNamePositionMap=new HashMap<>(); //!<包名加类名的字符串与图标位置之间的映射。
    private HashMap<String, Integer> packageNamePositionMap=new HashMap<>(); //!<包名字符串与图标位置之间的映射。

    private boolean sentVoiceShortcutAssociationData=false; //!< 是否已经成功发送语音指令关联快捷方式的数据。
    private static final int PERMISSIONS_REQUEST = 1; //!< 权限请求标识

    private MultiMap<String, PackageItemInfo> voicePackageNameMap; //!< 语音识别结果与包条目信息之间的映射关系。本设备独有的

    @BindView(R2.id.wallpaper) ImageView wallpaper; //!< 墙纸视图。

    @BindView(R2.id.progressBar) ProgressBar progressBar; //!< 进度条。

    private String voiceRecognizeResultString; //!< 语音识别结果。

    int ret = 0;

    @BindView(R2.id.applicationIconrightimageView2) ImageView applicationIconrightimageView2; //!< 应用图标图片。陈欣。

    @BindView(R2.id.statustextView) TextView statustextView; //!< 用来显示状态的文字标签。
    @BindView(R2.id.newVersionView) TextView newVersionView; //!< New version text view.

    private int recognizeCounter=0; //!< 识别计数器．

    private Vibrator vibrator;

    private boolean voiceEndDetected=false; //!< 是否已经探测到用户声音结束。

    private int mPageNumber = 1; // {1, 1, 1};

    private final int MSG_LOAD_MORE = 2;

    private ImageView mHeaderImageView;
    private TextView mHeaderTitle;

    private String packagename; //!< 包名。陈欣。
    private String activityName; //!< 活动名字。
    private String cancelledPackageName = null; //!< cancelled package name.

    private RequestQueue mQueue; //!< Volley请求队列。

    @OnClick(R2.id.hitApplicationIcon)
    public void deleteItem()
    {
      cancelledPackageName = packagename; // Remember cancelled package name.
      downloadRequestor.cancelDownload(); // Cancel download.
      
      hitApplicationIcon.setVisibility(View.INVISIBLE);
      progressBar.setVisibility(View.INVISIBLE);
      microphoneIcon.setVisibility(View.VISIBLE);
      textProgresstantLayout.setVisibility(View.INVISIBLE);
    }

    /**
     * 手动开始语音识别。
     */
    @OnClick({R2.id.voiceAssistantLayout, R2.id.microphoneIcon})
    public void triggerUpgrade()
    {
      cancelledPackageName=null;

      HxLauncherApplication application=HxLauncherApplication.getInstance(); // 获取应用程序对象。

      Map<String,String> packageNameUrlMap=application.getPackageNameUrlMap(); // 获取国际化数据对象。
      Map<String, String> packageNameApplicationMap=application.getPackageNameApplicationNameMap(); // 获取包名与应用程序名字的映射

      triggerDownload(packageNameUrlMap, packageNameApplicationMap); // Trigger download
    } // public void manualStartVoiceRecognize()
    
    /**
    * Trigger download
    */
    private void triggerDownload(Map<String,String> packageNameUrlMap, Map<String, String> packageNameApplicationMap)
    {
      if (packageNameUrlMap!=null) // 数据存在。
      {
        String installerUrl=packageNameUrlMap.get(packagename); // 获取国际化名字。

        Log.d(TAG,"triggerUpgrade, installerUrl: "+ installerUrl+ ", package name: " + packagename); // Debug.

        if (installerUrl!=null) // 有 installer url.
        {
          Log.d(TAG,"triggerUpgrade, 183, package url: "+ installerUrl+ ", package name: " + packagename); // Debug.

          String applicationName=packageNameApplicationMap.get(packagename); // 应用程序名字

          requestDownloadPackage(installerUrl, applicationName); // 下载应用程序安装包。
        } //if (installerUrl!=null) // 有国际化名字。
      } //if (packageNameUrlMap!=null) // 数据存在。
    } // private void triggerDownload(Map<String,String> packageNameUrlMap, String intalleryTye)
    
  @OnClick(R2.id.shareIcon)
  public void shareViaText()
  {
    ShareManager shareManager=new ShareManager();
    
    shareManager.shareViaText(this, applicationName, packagename); // Share via text.
  } // public void shareViaText()

  /**
  * Request apkpure to show the information url.
  */
  private void requestApkPureInstall(String informationUrl) 
  {
    //       10-07 12:24:24.703  8816  8816 D ApplicationInformationB: triggerUpgrade, informationUrl: https://apkpure.com/三國殺名將傳-威力加強版/com.playbest.sgsmjz, package name: com.playbest.sgsmjz
    String[] separated = informationUrl.split("/");
    Log.d(TAG,"requestApkPureInstall, 224, information url: "+ informationUrl); // Debug.
    Log.d(TAG,"requestApkPureInstall, 227, application name: "+ separated[3]); // Debug.

    String applicatonNameEncoded=separated[3];

    try
    {
      applicatonNameEncoded=URLEncoder.encode(separated[3], "utf-8");
    }
    catch(UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    
    Log.d(TAG,"requestApkPureInstall, 230, application name encoded: "+ applicatonNameEncoded); // Debug.
    
    separated[3]=applicatonNameEncoded;
    
    String encodedUrl=TextUtils.join("/", separated);
    Log.d(TAG,"requestApkPureInstall, 235, url encoded: "+ encodedUrl); // Debug.
    
    Uri uriApk= Uri.parse(encodedUrl); // Parse the uri.
    Log.d(TAG,"requestApkPureInstall, 225, uri: "+ uriApk); // Debug.
    Intent apkPureIntent=new Intent(Intent.ACTION_VIEW, uriApk); // Create the intent.
    Log.d(TAG,"requestApkPureInstall, 227, intent: "+ apkPureIntent); // Debug.
    
    apkPureIntent.setClassName("com.apkpure.aegon", "com.apkpure.aegon.main.activity.SplashActivity"); // 设置类名。
    
    try
    {
      startActivity(apkPureIntent);
    }
    catch (ActivityNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (NullPointerException e) // 捕获空指针异常。
    {
      e.printStackTrace();
    }
  } // private void requestApkPureInstall(String informationUrl)

    /**
    * 报告，下载 progress。
    */
    public void reportDownloadProgress(String packageName, long downloaded, long total) 
    {
      progressBar.setVisibility(View.VISIBLE);
      textProgresstantLayout.setVisibility(View.VISIBLE); // Show text progress .
      
      double downloadedMb=((double)(downloaded))/(1024*1024);
      
      downloadedmbionView.setText(String.format(Locale.US, "%1$.1f", downloadedMb));
      
      double totalMb=((double)(total))/(1024*1024);
      
      totalmbsionView.setText(String.format(Locale.US, "%1$.1f", totalMb));
      
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) // progressbar setmin
      {
        progressBar.setMin(0);
      } //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //动态权限

      progressBar.setMax((int)(total));
      
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) // progressbar setprogress animate
      {
        progressBar.setProgress((int)(downloaded), true);
      } //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //动态权限
      else
      {
        progressBar.setProgress((int)(downloaded));
      }
    } // public void reportDownloadProgress(String packageName, long downloaded, long total)
    
    /**
    * 报告，下载 finished。
    */
    public void reportDownloadFinished(String packageName)
    {
      progressBar.setVisibility(View.INVISIBLE);
      textProgresstantLayout.setVisibility(View.INVISIBLE);
      
      hitApplicationIcon.setVisibility(View.INVISIBLE);
    } // public void reportDownloadFinished(String packageName)
    
    /**
     * 向应用程序注册本地服务器列表获取完毕的回调。
     */
    private void registerLocalServerListCallbackToApplication()
    {
      HxLauncherApplication baseApplication= HxLauncherApplication.getInstance(); //获取应用程序对象。

      baseApplication.addLocalServerListLoadListener(this); //将本对象加入到回调列表中。
    } //private void registerLocalServerListCallbackToApplication()

    /**
    * 报告，下载失败。
    */
    public void  reportDownloadFailed(String packageName) 
    {
      textProgresstantLayout.setVisibility(View.INVISIBLE);

      HxLauncherApplication application=HxLauncherApplication.getInstance(); //获取应用程序对象。
      Log.d(TAG,"reportDownloadFailed, 360, request check upgrade: "); //Debug.
      application.startCheckUpgrade(); // Start check upgrade again.
      microphoneIcon.setVisibility(View.VISIBLE);

      String mWordSeparators = getResources().getString(R.string.downloadFailed); // load explain text string. download failed

      voiceUi.say(mWordSeparators); // say , download failed.

      if (packageName.equals(cancelledPackageName)) // Ignore
      {
      }
      else // Go on
      {
        Map<String,String> internationalizationData=application.getPackageNameInformationUrlMap(); //获取国际化数据对象。

        if (internationalizationData!=null) //数据存在。
        {
          String internationalizationName=internationalizationData.get(packageName); //获取国际化名字。

          Log.d(TAG,"reportDownloadFailed, 372, package information url: "+ internationalizationName+ ","); //Debug.

          hitApplicationIcon.setVisibility(View.INVISIBLE); // hide stop button.
          
          
          downloadFailureReporter.reportDownloadFailure(packageName, RabbitMQUserName, RabbitMQPassword, TRANSLATE_REQUEST_QUEUE_NAME); // report download failure.
        } //if (internationalizationData!=null) //数据存在。
        triggerUpgrade(); // Try agina.
      } // else // Go on
    } // public void  reportDownloadFailed(String packageName)
    
    /**
     * 下载应用程序安装包。
     * @param internationalizationName 安装包路径。
     */
    private void requestDownloadPackage(String internationalizationName, String applicationName)
    {
      Log.w(TAG, "requestDownloadPackage, 200, timestamp: " + System.currentTimeMillis()); // Debug.

      downloadRequestor.setLauncherActivity(this); // Set calling activity.
      
      Uri packageUri = Uri.parse(internationalizationName); // Parse the uri.
      
      downloadRequestor.requestDownloadUrl(packageUri, internationalizationName, applicationName, packagename); // 要求下载网址
      
      hitApplicationIcon.setVisibility(View.VISIBLE);
      microphoneIcon.setVisibility(View.INVISIBLE);
    } //private void requestDownloadPackage(String internationalizationName)

    private final DownloadRequestor downloadRequestor = new DownloadRequestor();

    /**
    * 报告， installing。
    */
    public void reportInstalling(String packageName)
    {
      progressBar.setVisibility(View.INVISIBLE);
      textProgresstantLayout.setVisibility(View.INVISIBLE);
      
      hitApplicationIcon.setVisibility(View.INVISIBLE);
    } // public void reportDownloadFinished(String packageName)
    
    /**
     * 报告结果，翻译请求的发送结果。
     * @param result 是否发送成功。
     */
    public void processApplicationInfoLoadResult(List<PackageInfo> result)
    {
    } //public void processApplicationInfoLoadResult(Boolean result)

    private ApplicationInformationAdapter mAdapter; //!<适配器。

    private static final String TAG="ApplicationInformationA"; //!< 输出调试信息时使用的标记。
    private final String categoryName="default"; //!<要显示的分类的名字。

    @Override
    protected void onNewIntent(Intent intent)
    {
      Log.d(TAG, "onNewIntent, intent: " + intent); //Debug.
      super.onNewIntent(intent);

      processIntent(intent); // 处理意图。陈欣。
    }

    @Override
    /**
     * 活动被创建。
     */
    protected void onCreate(Bundle savedInstanceState)
    {
      super.onCreate(savedInstanceState);

      getWindow().setEnterTransition(null);

      setContentView(R.layout.application_information_activity); //设置视图

      ButterKnife.bind(this); //视图注入。

      voiceUi=new VoiceUi(this); // 创建语音交互对象。

      mHeaderImageView = findViewById(R.id.applicationIconrightimageView2);
      mHeaderTitle = findViewById(R.id.rightTextoperationMethodactTitletextView2);

      mHeaderImageView.setTransitionName( VIEW_NAME_HEADER_IMAGE);
      mHeaderTitle.setTransitionName( VIEW_NAME_HEADER_TITLE);

      registerLocalServerListCallbackToApplication(); // Register data update.

      Intent intent=getIntent(); // 获取意图。

      processIntent(intent);

      triggerUpgrade(); // Trigger the upgrade.
    } //protected void onCreate(Bundle savedInstanceState)

    /**
     * 处理事件，软件包列表载入完毕。
     */
    @Override
    public void onLoadPackageInfoList()
    {
      checkUpgrade(packagename); // 检查这个软件包是否可以升级。
      
      showApplicationInformation(); // Show appliccation information.
    } //public void onLoadPackageInfoList()

    /**
    * Show application information.
    */
    private void showApplicationInformation()
    {
      HxLauncherApplication application = HxLauncherApplication.getInstance(); //获取应用程序对象。

      String currentVersionName= application.getVersionName(packagename); // 获取版本名字。
      
      statustextView.setText(currentVersionName); // Show curent version name.

      Drawable result = null; //获取缓存绘图对象。
      String iconUrl=application.getIconForPackage(packagename); // Get icon url.

      Glide.with(application).load(iconUrl).placeholder(result).into(applicationIconrightimageView2); //显示图标。
      Map<String, String> packageNameApplicationMap=application.getPackageNameApplicationNameMap(); // 获取包名与应用程序名字的映射

      applicationName=""; // 应用程序名字
      
      if (packageNameApplicationMap!=null)
      {
        applicationName=packageNameApplicationMap.get(packagename); // 应用程序名字
      } // if (packageNameApplicationMap!=null)
      
      if ((applicationName==null) || (applicationName.isEmpty())) // It is empty
      {
        applicationName=getString(R.string.querying); // Querying.
      } // if ((applicationName==null) || (applicationName.isEmpty())) // It is empty

      Log.d(TAG, "showApplicationInformation. 556. package name: " + packagename + ", appliactoin name: " + applicationName); // Debug.

      applicationName2.setText(applicationName); // Show application name.
    } // private void showApplicationInformation()

    private void processIntent(Intent intent) 
    {
      packagename = intent.getStringExtra(EXTRA_PACKAGE_NAME); // 记录包名。陈欣。

      mHeaderTitle.setText(packagename);

      checkUpgrade(packagename); // 检查这个软件包是否可以升级。
      
      showApplicationInformation(); // Show appliccation information.
    }

    /**
     * 检查这个软件包是否可以升级。
     * @param packageName 软件 包名。陈欣
     */
    private void checkUpgrade(String packageName)
    {
      HxLauncherApplication hxlauncherApplication=HxLauncherApplication.getInstance(); // 获取应用对象。

      String currentVersionName=hxlauncherApplication.getHighestVersionNameByType(packageName, Constants.VersionNameType.Existing); // 获取版本名字。 The highest in package cluster.

      String availableVersonName = hxlauncherApplication.getHighestVersionNameByType(packageName, Constants.VersionNameType.Available); // 获取可用的版本名字。

      Log.d(TAG, "checkUpgrade. avaialable version: " + availableVersonName + ", current version: " + currentVersionName); //Debug.

      Version availableVersion= new Version(availableVersonName); // 已有版本对象。

      VersionComparator versionComparator=new VersionComparator(); // Create version comparator.

      if ((versionComparator.isHigerVersion(availableVersonName, currentVersionName))  || (hxlauncherApplication.isPackageInWhiteList(packageName))) // There is a higher version.
      {
        showUpgradeIcon(); // 显示升级按钮
        
        newVersionView.setText(availableVersonName); // Show new version name.
      } //if (availableVersonName > currentVersionName) // 有新版本
      else // 无新版本
      {
        hideUpgradeIcon(); // 隐藏升级按钮。
        
        newVersionView.setText(""); // Show nothing.
      } //else // 无新版本
    } //private void checkUpgrade(String packageName)

    /**
     * 隐藏升级按钮。
     */
    private void hideUpgradeIcon()
    {
      microphoneIcon.setVisibility(View.INVISIBLE); // 隐藏。
    } //private void hideUpgradeIcon()

    /**
     * 显示升级按钮
     */
    private void showUpgradeIcon()
    {
        microphoneIcon.setVisibility(View.VISIBLE); // 显示。陈欣。
    } //private void showUpgradeIcon()

    /**
     * 记录启动时间戳。
     * @param launchIntent 启动意图。
     */
    private void rememberLaunchTimestamp(Intent launchIntent)
    {
        String packageName=launchIntent.getComponent().getPackageName(); //获取包名。
        String activityName=launchIntent.getComponent().getClassName(); //获取活动的类名。

        HxLauncherApplication hxLauncherApplication= HxLauncherApplication.getInstance(); //获取应用程序对象。
        HashMap<Integer, Integer> seekBarValueCoolDownTimeMap=hxLauncherApplication.getSeekBarValueCoolDownTimeMap(); //获取映射。
//        int calculatedCoolDownTime=seekBarValueCoolDownTimeMap.get(seekBarValue); //计算出实际的冷却时间，秒数。

        HashMap<String, Integer> packageItemLaunchCoolDownMap=hxLauncherApplication.getPackageItemLaunchCoolDownMap(); //获取映射。包条目信息字符串与实际冷却时间秒数之间的映射。

        long currentTimestamp=System.currentTimeMillis(); //当前的时间戳。

        packageItemLastLaunchTimestampMap.put(packageName+"/"+activityName, currentTimestamp); //记录。
    } //private void rememberLaunchTimestamp(Intent launchIntent)

}
