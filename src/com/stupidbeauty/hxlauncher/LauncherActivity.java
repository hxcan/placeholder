package com.stupidbeauty.hxlauncher;

import com.stupidbeauty.placeholder.R2;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import com.stupidbeauty.threeupgrade.service.UpgradeManagerJobService5040;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import com.stupidbeauty.hxlauncher.manager.ActiveUserReportManager;
import android.os.Debug;
import com.stupidbeauty.hxlauncher.asynctask.LoadPreferenceTask;
import com.stupidbeauty.hxlauncher.asynctask.ReqGameDataTask;
import com.stupidbeauty.hxlauncher.asynctask.BindAdapterTask;
import com.stupidbeauty.hxlauncher.asynctask.LoadVoicePackageNameMapTask;
import 	java.util.Timer;
import java.util.TimerTask;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.LocaleList;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.Settings;
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
// import com.stupidbeauty.hxlauncher.adapter.FlipAnimationAdapter;
// import com.stupidbeauty.hxlauncher.bean.VoiceCommandHitDataObject;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.stupidbeauty.threeupgrade.AnswerAvailableEvent;
// import com.huiti.msclearnfootball.VoiceRecognizeResult;
import com.stupidbeauty.builtinftp.BuiltinFtpServer;
import com.stupidbeauty.hxlauncher.service.DownloadNotificationService; 
// import com.stupidbeauty.farmingbookapp.PreferenceManagerUtil;
// import com.stupidbeauty.hxlauncher.asynctask.TranslateRequestSendTask;
// import com.stupidbeauty.comgooglewidevinesoftwaredrmremover.app.LanImeUncaughtExceptionHandler;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import com.stupidbeauty.hxlauncher.external.ShutDownAt2100Manager;
import com.stupidbeauty.qtdocchinese.ArticleInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.stupidbeauty.hxlauncher.interfaces.LocalServerListLoadListener;
import com.stupidbeauty.hxlauncher.bean.ApplicationListData;
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
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_PINNED;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_PINNED_BY_ANY_LAUNCHER;
import static com.stupidbeauty.hxlauncher.Constants.Actions.LegacyInstallShortcut;
import static com.stupidbeauty.hxlauncher.Constants.LanImeAction.PackageNameOfInputting;
import com.stupidbeauty.hxlauncher.listener.BuiltinFtpServerErrorListener; 
import android.os.Process;

public class LauncherActivity extends Activity implements  LocalServerListLoadListener
{
  private BuiltinFtpServerErrorListener builtinFtpServerErrorListener=null; //!< the builtin ftp server error listener.  Chen xin.
  private BuiltinFtpServer builtinFtpServer=null; //!< The builtin ftp server.

  // @BindView(R2.id.launchRipple) RippleView launchRipple; //!<用于转场动画的视图对象

  @BindView(R2.id.applicationIconrightimageView2) ImageView applicationIconrightimageView2; //!<应用程序图标

  @BindView(R2.id.rightTextoperationMethodactTitletextView2) TextView rightTextoperationMethodactTitletextView2; //!<应用程序名字
  
  private ActiveUserReportManager activeUserReportManager=null; //!< 活跃用户统计管理器。陈欣。

  private AnimationDrawable rocketAnimation; //!<录音按钮变暗

  private ShutDownAt2100Manager shutDownAt2100Manager=new ShutDownAt2100Manager(); //!<管理与21点关机之间的事务。

  @BindView(R2.id.hitApplicationIcon) ImageView hitApplicationIcon; //!<命中的应用的图标。

  @BindView(R2.id.launcher_activity) RelativeLayout launcher_activity; //!<整个启动活动

  @BindView(R2.id.voiceAssistantLayout) RelativeLayout voiceAssistantLayout; //!<语音助手 布局。 陈欣

  // @BindView(R2.id.loveAnimation) Love2  loveAnimation; //!<点赞爱心动画

  @BindView(R2.id.microphoneIcon) ImageView microphoneIcon; //!<麦克风图标。
  @BindView(R2.id.applicationNameTextView2) TextView applicationNameTextView2; //!< 介绍文字标签。

  private boolean mscIsInitialized=false; //!<讯飞语音识别是否已经初始化。
  private HashMap<String, Long> packageItemLastLaunchTimestampMap=new HashMap<>(); //!<包名加类名的字符串与最后一次启动时间戳之间的映射。

  private HashMap<String, String> serverVoiceCommandResponseIgnoreMap=null; //!<服务器的回复中，要忽略掉的关系映射

  private HashMap<String, ShortcutInfo> shortcutTitleInfoMap; //!<快捷方式的标题与快捷方式对象本身的映射。
  private HashMap<String, ShortcutInfo> shortcutIdInfoMap; //!<快捷方式的编号与快捷方式对象本身的映射
  private HashMap<String, Integer> packageNameItemNamePositionMap=new HashMap<>(); //!<包名加类名的字符串与图标位置之间的映射。
  private HashMap<String, Integer> packageNamePositionMap=new HashMap<>(); //!<包名字符串与图标位置之间的映射。

  AutoRunManager autoRunManager=new AutoRunManager(this); //!<自动启动管理器。
  PowerManager.WakeLock wakeLock=null; //!<游戏辅助唤醒锁。
  private boolean activityHasBeenResumed=false; //!<活动是否处于被继续的状态，即正常的运行状态。
  private boolean sentVoiceAssociationData=false; //!<是否已经成功发送语音指令关联应用程序数据。

  private List<ShortcutInfo> shortcutInfos=null; //!< 快捷方式列表。
  
  private ArrayList<ArticleInfo> articleInfoArrayList = null; //!< 应用程序信息列表。
  
  private boolean builtinShortcutsVisible= true; //!< 内置 快捷方式是否可见。
    
    /**
    * 设置 内置 快捷方式是否可见。
    */
    public void setBuiltinShortcutsVisible (boolean builtinShortcutsVisible) 
    {
        this.builtinShortcutsVisible=builtinShortcutsVisible;
        
        if (mAdapter!=null)
        {
            mAdapter.toggleBuiltinShortcutsVisible(builtinShortcutsVisible); //切换，内置快捷方式是否可见。
        } ////!< 内置 快捷方式是否可见。
    } //public void setBuiltinShortcutsVisible (boolean builtinShortcutsVisible)
    
    /**
    * 应用程序信息列表。
    */
    public void setArticleInfoArrayList (ArrayList<ArticleInfo> articleInfoArrayList ) 
    {
        this.articleInfoArrayList=articleInfoArrayList;
        
        if (mAdapter!=null)
        {
            applyArticleInfoArrayList(); // 应用应用程序信息列表。
        } ////!< 应用程序信息列表。
    } //public void setArticleInfoArrayList (ArrayList<ArticleInfo> articleInfoArrayList )
    
    /**
    * 包名字符串与图标位置之间的映射。
    */
    public void setPackageNamePositionMap (HashMap<String, Integer> packageNamePositionMap)
    {
        this.packageNamePositionMap=packageNamePositionMap;
    }
    
        public void setServerVoiceCommandResponseIgnoreMap (HashMap<String, String> serverVoiceCommandResponseIgnoreMap) //!<服务器的回复中，要忽略掉的关系映射
        {
            this.serverVoiceCommandResponseIgnoreMap=serverVoiceCommandResponseIgnoreMap;
        }


    public void setSentVoiceShortcutAssociationData(boolean sentVoiceShortcutAssociationData) {
        this.sentVoiceShortcutAssociationData = sentVoiceShortcutAssociationData;
    }

    private boolean sentVoiceShortcutAssociationData=false; //!<是否已经成功发送语音指令关联快捷方式的数据。
    private static final int PERMISSIONS_REQUEST = 1; //!<权限请求标识

    private static final String PERMISSION_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO; //!<录音权限。
    private static final String PERMISSION_FINE_LOCATIN = Manifest.permission.ACCESS_FINE_LOCATION; //!<位置权限

    private boolean foundActivity=false; //!<是否命中了活动。

    private SetValuedMap<String, PackageItemInfo> voicePackageNameMap=null; //!<语音识别结果与包条目信息之间的映射关系。本设备独有的
    
    /**
    * 内置的，语音识别结果与包条目信息之间的映射关系。
    */
    public void setVoicePackageNameMapBuiltin( SetValuedMap<String, PackageItemInfo> voicePackageNameMapBultin)
    {
        this.voicePackageNameMapBultin=voicePackageNameMapBultin;
    } //public setVoicePackageNameMapBuiltin( SetValuedMap<String, PackageItemInfo> voicePackageNameMapBultin)
    
    private SetValuedMap<String, PackageItemInfo> voicePackageNameMapBultin; //!<内置的，语音识别结果与包条目信息之间的映射关系。
    private HashMap<String, String> internationalizationDataPackageNameMap=new HashMap<>(); //映射。应用程序的国际化名字与包名之间的映射。

    @BindView(R2.id.wallpaper) ImageView wallpaper; //!<墙纸视图。

    @BindView(R2.id.progressBar) ProgressBar progressBar; //!<进度条。

    private String voiceRecognizeResultString; //!<语音识别结果。

    int ret = 0;

    @BindView(R2.id.statustextView) TextView statustextView; //!<用来显示状态的文字标签。

    private String recordSoundFilePath; //!<录音文件路径．

    private int recognizeCounter=0; //!<识别计数器．

    private Vibrator vibrator;

    private boolean voiceEndDetected=false; //!<是否已经探测到用户声音结束。

    private int mPageNumber = 1;//{1, 1, 1};

    private final int MSG_REFRESH   = 1;
    private final int MSG_LOAD_MORE = 2;
    private boolean mIsLastPage = true;

    private int mCurrMsg = -1;

    private RequestQueue mQueue; //!<Volley请求队列。
    
    private ArrayList<ArticleInfo> builtinShortcuts =null; //!< 内置快捷方式列表。
    
    private void applyBuiltinShortcuts()
    {
        mAdapter.setBuiltinShortcuts(builtinShortcuts); //设置文章信息列表。
        mAdapter.notifyDataSetChanged(); //通知数据变更。
    } //private void applyBuiltinShortcuts()
    
    /**
    * 设置内置快捷方式列表。
    */
    public void setBuiltinShortcuts (ArrayList<ArticleInfo> builtinShortcuts ) 
    {
        this.builtinShortcuts= builtinShortcuts;
        
        Log.d(TAG, "setBuiltinShortcuts, builtinShortcuts length: "  + builtinShortcuts.size()); // Debug.
        
        if (mAdapter!=null)
        {
            applyBuiltinShortcuts();
        }
    } //public void setBuiltinShortcuts (ArrayList<ArticleInfo> builtinShortcuts )
    
    /**
    * 设置语音识别结果与包条目信息之间的映射关系。本设备独有的
    */
    public void  setVoicePackageNameMap (SetValuedMap<String, PackageItemInfo> voicePackageNameMap) 
    {
      this.voicePackageNameMap=voicePackageNameMap;
    } //public void  setVoicePackageNameMap (SetValuedMap<String, PackageItemInfo> voicePackageNameMap)
    
    public void setInternationalizationDataPackageNameMap(HashMap<String, String>  internationalizationDataPackageNameMap)
    {
      this.internationalizationDataPackageNameMap=internationalizationDataPackageNameMap;
    
      Log.d(TAG, "setInternationalizationDataPackageNameMap, map: " + this.internationalizationDataPackageNameMap); // Debug.
    }
    
    /**
    * 选择随机端口。
    */
    private int chooseRandomPort() 
    {
      int randomIndex=4322; // 随机选择一个文件。

      return randomIndex;
    } //private int chooseRandomPort()

    /**
     * 显示应用程序信息活动
     * @param itemView 视图对象
     * @param packageName 包名
     */
    public void showApplicationInformation(View itemView, String packageName, String activityName)
    {
      Intent launchIntent=new Intent(this, ApplicationInformationActivity.class); //启动意图。

      launchIntent.putExtra(EXTRA_PACKAGE_NAME, packageName);
      launchIntent.putExtra(EXTRA_COMPONENT_NAME, activityName); // 设置部件名字。

      // launchRipple.setX(itemView.getX()+mRecyclerView.getX());
      // launchRipple.setY(itemView.getY()+mRecyclerView.getY());
      // launchRipple.getLayoutParams().width=itemView.getWidth();
      // launchRipple.getLayoutParams().height=itemView.getHeight();

      ImageView itemApplicationIconView=(ImageView)itemView.findViewById(R2.id.applicationIconrightimageView2);

      Drawable iconDrawableTransition=itemApplicationIconView.getDrawable();

      HxLauncherApplication hxLauncherApplication=HxLauncherApplication.getInstance();

      applicationIconrightimageView2.setImageDrawable(iconDrawableTransition);
      applicationIconrightimageView2.setX(itemApplicationIconView.getX());
      applicationIconrightimageView2.setY(itemApplicationIconView.getY());
      applicationIconrightimageView2.getLayoutParams().width=itemApplicationIconView.getWidth();
      applicationIconrightimageView2.getLayoutParams().height=itemApplicationIconView.getHeight();

      TextView itemApplicationIconText=(TextView)itemView.findViewById(R2.id.rightTextoperationMethodactTitletextView2);

      rightTextoperationMethodactTitletextView2.setText(itemApplicationIconText.getText());

      startActivity(launchIntent); //启动活动。
    } //public void showApplicationInformation(View itemView, String packageName)

    /**
     * 设置结果，发送语音关联数据的结果。
     * @param result 发送结果。
     */
    public void setSendVoiceAssociationDataResult(Boolean result)
    {
      sentVoiceAssociationData=result; //记录。
    } //public void setSendVoiceAssociationDataResult(Boolean result)

    /**
     * Report that the operation has failed.
     * @param string 服务器回复的结果说明文字。
     */
    protected void reportOperationFail(String string)
    {
      Toast.makeText(HxLauncherApplication.getAppContext(), string, Toast.LENGTH_LONG).show();   //做一个提示，Failed adding address ,please retry.
    } //protected void reportOperationFail()

    /**
     * 处理事件，软件包列表载入完毕。
     */
    @Override
    public void onLoadPackageInfoList()
    {
//       solveLauncherIntents(); // 刷新已安装的应用程序。
      reqGameData(); // Request game data.
    } //public void onLoadPackageInfoList()

    @BindView(R2.id.articleListmy_recycler_view) RecyclerView mRecyclerView; //!<回收视图。

    private ApplicationInformationAdapter mAdapter; //!<适配器。

    private static final String TAG="LauncherActivity"; //!<输出调试信息时使用的标记。
    private final String categoryName="default"; //!<要显示的分类的名字。
    
    /**
    *  设置应用程序信息适配器。
    */
    public void  setApplicationInformationAdapter (ApplicationInformationAdapter mAdapter)
    {
      this.mAdapter=mAdapter;

      if (articleInfoArrayList!=null) // 应用程序列表存在
      {
        applyArticleInfoArrayList(); // 应用应用程序列表。
      } //if (articleInfoArrayList!=null) // 应用程序列表存在
        
        mAdapter.toggleBuiltinShortcutsVisible(builtinShortcutsVisible); //切换，内置快捷方式是否可见。

        if (builtinShortcuts!=null)
        {
            applyBuiltinShortcuts();
        }
    } //public void  setApplicationInformationAdapter (ApplicationInformationAdapter mAdapter)
    
    /**
    * 应用应用程序列表。
    */
    private void applyArticleInfoArrayList() 
    {
      mAdapter.setArticleInfoArrayList(articleInfoArrayList); //设置文章信息列表。
      mAdapter.notifyDataSetChanged(); //通知数据变更。
    } //private void applyArticleInfoArrayList()

    /**
     * 要求显示系统的墙纸在本活动后面。
     */
    private void askShowSystemWallpaper()
    {
      WindowManager.LayoutParams p=getWindow().getAttributes();
      p.flags |= WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER;
    } //private void askShowSystemWallpaper()
    
    /**
    * 启动内置 FTP 服务器。
    */
    private void startBultinFtpServer()
    {
      builtinFtpServer=new BuiltinFtpServer(this); //!< The builtin ftp server.
      // builtinFtpServerErrorListener=new BuiltinFtpServerErrorListener(this); // Create the builtin ftp server error listener. 
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
      timerObj.schedule(timerTaskObj, 2000); // 延时启动。
    } //private void scheduleStartBuiltinFtpServer()

    @Override
    /**
     * 活动被创建。
     */
    protected void onCreate(Bundle savedInstanceState)
    {
      super.onCreate(savedInstanceState);

      askShowSystemWallpaper(); //要求显示系统的墙纸在本活动后面。

      requestWindowFeature(Window.FEATURE_NO_TITLE); //不显示标题栏。

      setContentView(R2.layout.launcher_activity); // 显示界面。

      ButterKnife.bind(this); //视图注入。

      checkPermission(); //检查权限。

      scheduleStartBuiltinFtpServer(); // 计划启动内置 FTP 服务器。

      registerBroadcastReceiver(); //注册广播事件接收器。

      bindAdapter(); //绑定适配器。

      registerLocalServerListCallbackToApplication(); // Register data update.

      reqGameData(); // 开始获取数据。

      loadPreference(); //载入选项。
      
      scheduleUpgradeManagerJob(); // Schedule the upgrade manager job.
    } //protected void onCreate(Bundle savedInstanceState)
    
    /**
    * Schedule the upgrade manager job.
    */
    private void scheduleUpgradeManagerJob()
    {
      HxLauncherApplication baseApplication= HxLauncherApplication.getInstance(); //获取应用程序对象。
      
      Context context = baseApplication;
      
      ComponentName serviceComponent = new ComponentName(context, UpgradeManagerJobService5040.class);
      JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
      builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
      builder.setPersisted(true); // Persisted.
      
      JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
      
      jobScheduler.schedule(builder.build());
    } // private void scheduleUpgradeManagerJob()

    /**
     * 载入选项。
     */
    private void loadPreference()
    {
      LoadPreferenceTask translateRequestSendTask =new LoadPreferenceTask(); //创建异步任务。

      translateRequestSendTask.execute(this, mAdapter); //执行任务。
    } //private void loadPreference()

    /**
     * 构造映射，快捷方式的标题与快捷方式对象之间的映射。
     * @param shortcutInfos 快捷方式列表。
     */
    public void buildShortcutTitleInfoMap(List<ShortcutInfo> shortcutInfos)
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) //26之后才有钉住的快捷方式。
        {
            shortcutTitleInfoMap=new HashMap<>(); //创建映射。
            shortcutIdInfoMap=new HashMap<>(); //创建映射。

            for(ShortcutInfo shortcutInfo: shortcutInfos) //一个个地显示。
            {
                String title = shortcutInfo.getShortLabel().toString(); //获取标题。
                String shortcutId= shortcutInfo.getId(); //获取编号

                shortcutTitleInfoMap.put(title, shortcutInfo); //加入映射。
                shortcutIdInfoMap.put(shortcutId, shortcutInfo); //加入映射。
            } //for(ShortcutInfo shortcutInfo: shortcutInfos) //一个个地显示。
        } //if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) //26之后才有钉住的快捷方式。
    } //private void buildShortcutTitleInfoMap(List<ShortcutInfo> shortcutInfos)

    /**
     * 显示系统壁纸。
     */
    private void showSystemWallpaper()
    {
        try {
            WallpaperManager wallpaperManager=(WallpaperManager) (getSystemService(Context.WALLPAPER_SERVICE)); //获取壁纸管理器。

            Drawable wallpaperDrawable=wallpaperManager.getDrawable(); //获取绘图对象。
//        wallpaperDrawable.

            wallpaper.setImageDrawable(wallpaperDrawable); //显示绘图对象。

        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
    } //private void showSystemWallpaper()

    /**
     * 考虑要不要执行自动启动动作。
     */
    private void assessAutoRun()
    {
       autoRunManager.assessAutoRun(); //由自动启动管理器来管理这件事。
    } //private void assessAutoRun()

    /**
     * 检查权限。
     */
    private void checkPermission()
    {
      if (hasPermission()) 
      {
      }
      else 
      {
        requestPermission();
      }
    } //private void checkPermission()

    /**
     * 请求获取权限
     */
    private void requestPermission()
    {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //动态权限
      {
        if ( shouldShowRequestPermissionRationale(PERMISSION_STORAGE)  ) //应当告知原因。
        {
          Toast.makeText(this, "Camera AND storage permission are required for this demo", Toast.LENGTH_LONG).show();
        } //if ( shouldShowRequestPermissionRationale(PERMISSION_STORAGE)  || shouldShowRequestPermissionRationale(PERMISSION_RECORD_AUDIO)) //应当告知原因。
        requestPermissions(new String[] {PERMISSION_STORAGE }, PERMISSIONS_REQUEST);
      } //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //动态权限
    } //private void requestPermission()

    private boolean hasPermission()
    {
        boolean result=false; //结果。

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //安卓6.
        {
            result= checkSelfPermission(PERMISSION_STORAGE) == PackageManager.PERMISSION_GRANTED; //存储权限。

            if (result) //存储权限已有。
            {
                result=(checkSelfPermission(PERMISSION_RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED); //录音权限。
            } //if (result) //存储权限已有。
        } //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) //安卓6.
        else //旧版本。
        {
            result=true; //有权限。
        } //else //旧版本。

        return result;
    } //private boolean hasPermission()

    /**
     * 显示系统墙纸。
     */
    private void loadSystemWallpaper()
    {
        WallpaperManager wallpaperManager=WallpaperManager.getInstance(this); //获取墙纸管理器。

        Drawable wallpaperDrawable=wallpaperManager.getDrawable(); //获取绘图对象。

        wallpaper.setImageDrawable(wallpaperDrawable); //显示墙纸。
    } //private void loadSystemWallpaper()

    /**
     * 寻找语音识别与软件包映射文件。
     * @return 语音识别与软件包映射文件。
     */
    private  File findVoicePackageMapFile()
    {
        File result=null;

        File filesDir=getFilesDir();

        if (filesDir==null) //该目录不存在。
        {
        } //if (filesDir==null) //该目录不存在。
        else //该目录存在。
        {
            result=new File(filesDir.getAbsolutePath()+"/voicePackageNameMap.proto"); //指定文件名。

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

    /**
     * 初始化数据
     */
    private void reqGameData()
    {
      ReqGameDataTask translateRequestSendTask =new ReqGameDataTask(); // 创建异步任务。

      translateRequestSendTask.execute(this, mAdapter); //执行任务。
    } //private void reqGameData()

    /**
     * 向应用程序注册本地服务器列表获取完毕的回调。
     */
    private void registerLocalServerListCallbackToApplication()
    {
      HxLauncherApplication baseApplication= HxLauncherApplication.getInstance(); //获取应用程序对象。

      baseApplication.addLocalServerListLoadListener(this); //将本对象加入到回调列表中。
    } //private void registerLocalServerListCallbackToApplication()

    /**
     * 显示图标列表
     */
    private void showIconList()
    {
        mRecyclerView.setVisibility(View.VISIBLE);

        mRecyclerView.setRotationY(-30);
        mRecyclerView.setRotationX(-30);

        mRecyclerView.animate().rotationY(0).rotationX(0).start();
    } //private void showIconList()

    /**
     * 绑定适配器。
     */
    private void bindAdapter()
    {
        BindAdapterTask translateRequestSendTask =new BindAdapterTask(); //创建异步任务。

        translateRequestSendTask.execute(this, mRecyclerView); //执行任务。
    } //private void bindAdapter()

    private void showStaggeredGridLayoutManager() 
    {
        int columnsPerRow= getResources().getInteger(  R2.integer.columnsPerRow); //每行的列数。

        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(columnsPerRow,StaggeredGridLayoutManager.VERTICAL); //布局管理器。
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    /**
     * 解除注册广播接收器。
     */
    private void unregisterBroadcastReceiver()
    {
        LocalBroadcastManager lclBrdcstMngr=LocalBroadcastManager.getInstance(this); //Get the broadcast manager instance.
        lclBrdcstMngr.unregisterReceiver(mBroadcastReceiver); //注册接收器。

        unregisterReceiver(mBroadcastReceiver);
    } //private void unregisterBroadcastReceiver()

    /**
     * 注册广播事件接收器。
     */
    private void registerBroadcastReceiver()
    {
        long startTimestamp=System.currentTimeMillis(); // 记录开始时间戳。

        IntentFilter intentFilter = new IntentFilter(); //创建意图过滤器。

        intentFilter.addAction(Constants.NativeMessage.APPLICATION_LAUNCHED); //应用程序被启动。
        
        LocalBroadcastManager lclBrdcstMngr=LocalBroadcastManager.getInstance(this); //Get the broadcast manager instance.
        lclBrdcstMngr.registerReceiver(mBroadcastReceiver, intentFilter); //注册接收器。

        //注册全局的广播接收器：
        //软件包增加：
        IntentFilter packageChangeIntentFilter=new IntentFilter(); //创建意图过滤器。
        packageChangeIntentFilter.addAction(Intent.ACTION_PACKAGE_ADDED); //应用被添加。
        packageChangeIntentFilter.addDataScheme("package"); //加入数据模式。

        registerReceiver(mBroadcastReceiver, packageChangeIntentFilter); //注册广播事件接收器。

        //软件包变化：
        IntentFilter packageChangeIntentFilter1=new IntentFilter(); //创建意图过滤器。
        packageChangeIntentFilter1.addAction(ACTION_PACKAGE_CHANGED); //应用被改变
        packageChangeIntentFilter1.addDataScheme("package"); //加入数据模式。

        registerReceiver(mBroadcastReceiver, packageChangeIntentFilter1); //注册广播事件接收器。

        //软件包变化：
        IntentFilter packageChangeIntentFilter2=new IntentFilter(); //创建意图过滤器。
        packageChangeIntentFilter2.addAction(ACTION_PACKAGE_REPLACED); //应用被改变
        packageChangeIntentFilter2.addDataScheme("package"); //加入数据模式。

        registerReceiver(mBroadcastReceiver, packageChangeIntentFilter2); //注册广播事件接收器。

        //壁纸变化：
        IntentFilter wallpaperChangeIntentFilter=new IntentFilter(); //创建意图过滤器。
        wallpaperChangeIntentFilter.addAction(Intent.ACTION_WALLPAPER_CHANGED); //壁纸发生变化。

        registerReceiver(mBroadcastReceiver, wallpaperChangeIntentFilter); //注册广播事件接收器。

        //兰心输入法正在为某个软件包输入：
        IntentFilter lanimeInputtingIntentFilter=new IntentFilter(); //创建意图过滤器。

        registerReceiver(mBroadcastReceiver, lanimeInputtingIntentFilter); //注册广播事件接收器。

        //传统的安装快捷方式：
        IntentFilter legacyShortcutIntentFilter=new IntentFilter(); //创建意图过滤器。
        legacyShortcutIntentFilter.addAction(LegacyInstallShortcut); //安装快捷方式。

        registerReceiver(mBroadcastReceiver, legacyShortcutIntentFilter); //注册广播事件接收器。

        long endTimestamp=System.currentTimeMillis(); // 记录开始时间戳。
    } //private void registerBroadcastReceiver()

    /**
     * 广播接收器。
     */
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()
    {
        private final String TAG="BroadcastReceiver"; //!<输出调试信息时使用的标记。

        @SuppressWarnings("ConstantConditions")
        @Override
        /**
         * 接收到广播。
         */
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction(); //获取广播中带的动作字符串。

            Log.d(TAG,"1587, onReceive,got broadcast:"+action + ", equals package_added?: " + (Intent.ACTION_PACKAGE_ADDED.equals(action))); //Debug.

            if ((Intent.ACTION_PACKAGE_ADDED.equals(action)) || (Intent.ACTION_PACKAGE_CHANGED.equals(action))  || (ACTION_PACKAGE_REPLACED.equals(action)) ) //应用被安装。
            {
                Bundle extras=intent.getExtras(); //获取参数包。
                int uid=extras.getInt(Intent.EXTRA_UID); //获取该软件包对应的用户编号。

                showNewlyAddedPackage(uid); //显示新安装的软件包。
            } //else if (Intent.ACTION_PACKAGE_ADDED.equals(action)) //应用被安装。
        } //public void onReceive(Context context, Intent intent)
    }; //private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()

    /**
     * 切换，是否使用蜂窝布局。
     * @param uid 是否使用蜂窝布局。
     */
    public void toggleHIveLayout(Boolean uid)
    {
        if (uid) //要使用蜂窝布局
        {
            showStaggeredGridLayoutManager(); //显示瀑布布局
        } //if (uid) //要使用蜂窝布局
        else //不使用蜂窝布局
        {
            showStaggeredGridLayoutManager(); //显示瀑布布局
        } //else //不使用蜂窝布局
    } //private void toggleHIveLayout(Boolean uid)

    /**
     * 切换，内置快捷方式是否可见。
     * @param uid 是否可见。
     */
    private void toggleBuiltinShortcutsVisible(boolean uid)
    {
        mAdapter.toggleBuiltinShortcutsVisible(uid); //切换可见性。

//        updateItemPositionMap(); //整体更新条目的位置映射

        mAdapter.notifyDataSetChanged(); //通知数据变化。
    } //private void toggleBuiltinShortcutsVisible(boolean uid)

    /**
     * 钉住快捷方式。
     * @param shortcutInfo 要钉住的快捷方式。
     */
    private void pinShortcut(ShortcutInfo shortcutInfo)
    {
        Log.d(TAG, "pinShortcut. This: " + this); //Debug.

        mAdapter.addShortcut(shortcutInfo); //向适配器添加快捷方式。

        mAdapter.updateItemPositionMap(); //整体更新条目的位置映射

        mAdapter.notifyDataSetChanged(); //通知数据变更。

    } //private void pinShortcut(ShortcutInfo shortcutInfo)

    /**
     * 显示新安装的软件包。
     * @param uid 软件包的用户编号。
     */
    public void showNewlyAddedPackage(int uid)
    {
      PackageManager packageManager=getPackageManager(); //获取软件包管理器。

      String[] packageNames=packageManager.getPackagesForUid(uid); //获取对应的软件包列表。

//       solveLauncherIntents(); //重新解析启动器意图列表
      reqGameData(); // refresh.
    } //public void showNewlyAddedPackage(int uid)

    @Override
    /**
     * 活动重新处于活跃状态。
     */
    protected void onResume()
    {
      super.onResume(); //超类继续工作。

      activityHasBeenResumed=true; //处于正常运行状态。

      HxLauncherApplication hxLauncherApplication=HxLauncherApplication.getInstance(); //获取应用程序对象。
      hxLauncherApplication.releaseWakeLock(); //释放唤醒锁。

      createActiveUserReportManager(); // 创建管理器，活跃用户统计。陈欣
    } //protected void onResume()
    
    /**
    * 创建管理器，活跃用户统计。陈欣
    */
    private void createActiveUserReportManager()
    {
      if (activeUserReportManager==null) // 还不存在管理器。
      {
        activeUserReportManager=new ActiveUserReportManager(); // 创建管理器。
        
        activeUserReportManager.startReportActiveUser(); // 开始报告活跃用户。
      } //if (activeUserReportManager==null)
    } //private void createActiveUserReportManager()

    /**
     * 活动被暂停。
     */
    @Override
    protected void onPause()
    {
        super.onPause();

        activityHasBeenResumed=false; //不是处于正常运行状态。
    } //protected void onPause()

    @Override
    /**
     * 活动正在被销毁。
     */
    protected void onDestroy()
    {
        super.onDestroy();

        unregisterBroadcastReceiver(); //解除注册广播接收器。
    } //protected void onDestroy()

    /**
     * 显示命中了应用的图标。
     * @param mappedPackageName 应用的包名。
     */
    private void showHitApplication(String mappedPackageName)
    {
        PackageManager packageManager=getPackageManager(); //获取软件包管理器。

        String activityTitle=""; //获取标题．

        try 
        {
            Drawable activityDrawable=packageManager.getApplicationIcon(mappedPackageName); //获取图标。

            hitApplicationIcon.setVisibility(View.VISIBLE);
            hitApplicationIcon.setImageDrawable(activityDrawable); //显示图标。

            animateHitApplication(); //动画旋转，命中应用。陈欣

        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        } //catch (PackageManager.NameNotFoundException e)

    } //private void showHitApplication(String mappedPackageName)

    /**
     * 动画旋转，命中应用。
     */
    private void animateHitApplication()
    {
        hitApplicationIcon.animate().rotationYBy(360).start();

        float x=hitApplicationIcon.getX()+voiceAssistantLayout.getX();
        float y=hitApplicationIcon.getY()+voiceAssistantLayout.getY();

        // if (loveAnimation!=null) //找到了视图
        {
            // loveAnimation.animateHeart(x, y); //显示点赞爱心动画
        } //if (loveAnimation!=null) //找到了视图
    } //private void animateHitApplication()

    /**
     * 显示点赞爱心动画
     * @param itemViewgetX X坐标
     * @param itemViewgetY Y坐标
     */
    public void animateHeart(float itemViewgetX, float itemViewgetY)
    {
        float x=itemViewgetX;
        float y=itemViewgetY;

        // if (loveAnimation!=null) //找到了视图
        {
            // loveAnimation.animateHeart(x, y); //显示点赞爱心动画
        } //if (loveAnimation!=null) //找到了视图

    } //public void animateHeart(float itemViewgetX, float itemViewgetY)

    /**
     * 显示命中了快捷方式的图标。
     * @param packageName 包名
     * @param activityName 快捷方式编号
     */
    private void showHitShortCut(String packageName, String activityName)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) //25之后才有快捷方式
        {
            LauncherApps launcherApps=(LauncherApps)(getSystemService(Context.LAUNCHER_APPS_SERVICE));

            ShortcutInfo shortcutInfo=shortcutIdInfoMap.get(activityName); //获取快捷方式对象

            if (shortcutInfo!=null) //快捷方式信息对象存在
            {
                String articleTitle=shortcutInfo.getShortLabel().toString(); //获取文章标题。

                Drawable applicationIcon=launcherApps.getShortcutIconDrawable(shortcutInfo, 0); //获取图标。

                hitApplicationIcon.setVisibility(View.VISIBLE);
                hitApplicationIcon.setImageDrawable(applicationIcon); //显示图标。

                animateHitApplication(); //动画旋转，命中应用。陈欣
            } //if (shortcutInfo!=null) //快捷方式信息对象存在
        } //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) //21之后才有启动应用
    } //private void showHitShortCut(String packageName, String activityName)

    /**
	 * 启动时间检查服务。
	 */
	private void startTimeCheckService(String applicationName) 
	{
		Intent serviceIntent = new Intent(this, DownloadNotificationService.class); //创建意图。
		
		serviceIntent.putExtra("applicationName", applicationName);

		startService(serviceIntent); //启动服务。
	} //private void startTimeCheckService()

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
                showHitApplication(packageName); //显示命中了应用的图标。

                launchApplication(launchIntent); //启动活动。

                result=true; //成功
            } //try //尝试启动活动，并且捕获可能的异常。
            catch (ActivityNotFoundException exception)
            {
                exception.printStackTrace(); //报告错误。
            } //catch (ActivityNotFoundException exception)
        } //if (launchIntent!=null) //意图存在。

        return result;
    } //private void launchApplicationByPackageName(String packageName)

    /**
     * 启动快捷方式。
     * @param shortcutInfo 要启动的快捷方式信息对象。
     */
    public boolean launchShortcut(String packageName, String shortcutId)
    {
        boolean launchSuccess=false; //是否成功启动。

        try //尝试启动快捷方式
        {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N_MR1) //25之后才有快捷方式编号。
            {
                LauncherApps launcherApps=(LauncherApps) (getSystemService(Context.LAUNCHER_APPS_SERVICE)); //获取启动器应用对象。

                launcherApps.startShortcut(packageName, shortcutId, null, null, Process.myUserHandle()); //启动快捷方式。

                launchSuccess=true; //成功启动。

            } //if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N_MR1) //25之后才有快捷方式编号。
        } //try //尝试启动快捷方式
        catch (ActivityNotFoundException e)
        {
            e.printStackTrace();
        } //catch (ActivityNotFoundException e)

        if (launchSuccess) //成功启动。
        {

        } //if (launchSuccess) //成功启动。
        else //未能成功启动。
        {
            launchSuccess= launchApplicationByPackageName(packageName); //启动应用程序本身。
        } //else //未能成功启动。

        return launchSuccess;
    } //public void launchShortcut(ShortcutInfo shortcutInfo)

    /**
     * 启动应用。
     * @param launchIntent 启动用的意图。
     */
    public boolean launchApplication(Intent launchIntent)
    {
      boolean result=false; //结果

      try //尝试启动活动，并且捕获可能的异常。
      {
        if (launchIntent!=null) //启动意图存在。
        {
          boolean allowedToLaunch=checkLaunchCoolDownTime(launchIntent); //检查启动的冷却时间。

          if (allowedToLaunch) //允许启动。
          {
            boolean exported=checkExported(launchIntent); //检查该个活动是否被导出了。

            if (exported) //是导出了。
            {
              startActivity(launchIntent); //启动活动。
            } //if (exported) //是导出了。e
            else //未导出。启动该应用的默认活动。
            {
              String packageName=launchIntent.getComponent().getPackageName(); //获取包名。

              launchApplicationByPackageName(packageName); //按照包名启动。
            } //else //未导出。
          } //if (allowedToLaunch) //允许启动。
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

    /**
     * 检查该个活动是否被导出了。
     * @param launchIntent 要检查的意图。
     * @return 活动是否被导出了。
     */
    private boolean checkExported(Intent launchIntent)
    {
        boolean result=true; //结果。

        if (launchIntent!=null) //启动意图存在。
        {
            String packageName=launchIntent.getComponent().getPackageName(); //获取包名。
            String activityName=launchIntent.getComponent().getClassName(); //获取活动的类名。

            String activity=activityName; //活动名字。

            ComponentName cm = new ComponentName(packageName, activity); //构造组件名字对象。


            PackageManager packageManager=getPackageManager(); //获取包管理器。

            try {


                ActivityInfo activityInfo=packageManager.getActivityInfo(cm, 0); //获取活动信息。

                result=activityInfo.exported; //是否导出。

            }
            catch (PackageManager.NameNotFoundException e)
            {
                e.printStackTrace();
            } //catch (PackageManager.NameNotFoundException e)

        } //if (launchIntent!=null) //启动意图存在。
        else //启动意图不存在。
        {
            result=false; //未导出。
        } //else //启动意图不存在。



        return result;
    } //private boolean checkExported(Intent launchIntent)

    /**
     * 主动崩溃。
     */
    private void crashIntended()
    {
       startActivity(null);
    } //private void crashIntended()

    /**
     * 检查启动的冷却时间。
     * @param launchIntent 启动意图。
     * @return 根据冷却时间，是否允许启动。
     */
    private boolean checkLaunchCoolDownTime(Intent launchIntent)
    {
        boolean result=false; //结果，是否允许启动。

        String packageName=""; //包名。
        String activityName=""; //活动类名。

        if (launchIntent!=null) //意图存在。
        {
             packageName=launchIntent.getComponent().getPackageName(); //获取包名。

             activityName=launchIntent.getComponent().getClassName(); //获取活动的类名。
        } //if (launchIntent!=null) //意图存在。

        if (activityName.startsWith(".")) //相对名字。
        {
            activityName=packageName+activityName; //构造完整名字。
        } //if (activityName.startsWith(".")) //相对名字。

        HxLauncherApplication hxLauncherApplication= HxLauncherApplication.getInstance(); //获取应用程序对象。
        HashMap<Integer, Integer> seekBarValueCoolDownTimeMap=hxLauncherApplication.getSeekBarValueCoolDownTimeMap(); //获取映射。

        HashMap<String, Integer> packageItemLaunchCoolDownMap=hxLauncherApplication.getPackageItemLaunchCoolDownMap(); //获取映射。包条目信息字符串与实际冷却时间秒数之间的映射。

        if (packageItemLaunchCoolDownMap!=null) // 映射存在。
        {
            if (packageItemLaunchCoolDownMap.containsKey(packageName+"/"+activityName)) //有这个键。
            {
                int launchCoolDownTime=packageItemLaunchCoolDownMap.get(packageName+"/"+activityName); //获取冷却时间。

                if (launchCoolDownTime<0) //永远禁止。
                {
                    result=false; //不允许启动。
                } //if (launchCoolDownTime<0) //永远禁止。
                else //没有永远禁止。
                {
                    if (packageItemLastLaunchTimestampMap.containsKey(packageName+"/"+activityName)) //有记录上次启动时间。
                    {
                        long lastLaunchTimeStamp=packageItemLastLaunchTimestampMap.get(packageName+"/"+activityName); //获取上次的启动时间戳。

                        long currentTimestamp=System.currentTimeMillis(); //当前的时间戳。

                        if ((lastLaunchTimeStamp+launchCoolDownTime*1000)>currentTimestamp) //还未超过冷却时间。
                        {
                        } //if ((lastLaunchTimeStamp+launchCoolDownTime*1000)>currentTimestamp) //还未超过冷却时间。
                        else  //已经超过冷却时间。
                        {
                            result=true; //允许启动。
                        } //else  //已经超过冷却时间。
                    } //if (packageItemLastLaunchTimestampMap.containsKey(packageName+"/"+activityName)) //有记录上次启动时间。
                    else //没有记录上次启动时间。
                    {
                        result=true; //允许启动。
                    } //else //没有记录上次启动时间。
                } //else //没有永远禁止。
            } //if (packageItemLaunchCoolDownMap.containsKey(packageName+"/"+activityName)) //有这个键。
            else //没有这个键，未限制。
            {
                result=true;
            } //else //没有这个键，未限制。
        }
        else
        {
            result=true;
        }

        return result;
    } //private boolean checkLaunchCoolDownTime(Intent launchIntent)

    /**
     * 获取唤醒锁。
     */
    private void acquireWakeLock()
    {
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "HxLauncher::WakeLockForGames");

        Log.d(TAG,"acquireWakeLock, wake lock: "+wakeLock + ", this: " + this); //Debug.

        wakeLock.acquire();
    } //private void acquireWakeLock()

    /**
     * 检查并更正启动意图中的类名。
     * @param launchIntent 要检查的启动意图。
     */
    private void checkAndCorrectClassNameInLauncherIntent(Intent launchIntent)
    {
        String className=launchIntent.getComponent().getClassName(); //获取类名。

        int indexOfDollar=className.indexOf('$'); //寻找美元符号。

        if (indexOfDollar>0) //存在美元符号。
        {
            int indexBeforeDollar=indexOfDollar-1; //复制到前一个字符为止。
            String correctedClassName=className.substring(0, indexBeforeDollar); //切出开头的部分。

            String packageName=launchIntent.getComponent().getPackageName(); //获取包名。

            launchIntent.setClassName(packageName, correctedClassName); //设置类名。
        } //if (indexBeforeDollar>0) //存在美元符号。
    } //private void checkAndCorrectClassNameInLauncherIntent(Intent launchIntent)
}
