package com.stupidbeauty.hxlauncher;

import com.stupidbeauty.hxlauncher.activity.ApplicationInformationActivity;
// import com.stupidbeauty.hxlauncher.adapter.FlipAnimationAdapter;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import static android.content.Intent.EXTRA_COMPONENT_NAME;
import static android.content.Intent.EXTRA_PACKAGE_NAME;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_DYNAMIC;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_MANIFEST;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_PINNED;
import com.stupidbeauty.placeholder.R;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
// import com.stupidbeauty.hxlauncher.logic.ShutDownAt2100Logic;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.protobuf.InvalidProtocolBufferException;
import com.stupidbeauty.codeposition.CodePosition;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.BufferedReader;
import com.stupidbeauty.hxlauncher.listener.BuiltinFtpServerErrorListener; 
import android.os.Process;
import com.stupidbeauty.builtinftp.BuiltinFtpServer;
import com.stupidbeauty.farmingbookapp.PreferenceManagerUtil;
import java.util.Timer;
import java.util.TimerTask;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import java.util.TimerTask;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import java.io.ByteArrayOutputStream;
import android.content.Context;
import com.upokecenter.cbor.CBORObject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import com.stupidbeauty.hxlauncher.asynctask.VoicePackageNameMapSaveTask;
import com.stupidbeauty.farmingbookapp.PreferenceManagerUtil;
// import com.stupidbeauty.hxlauncher.AndroidApplicationMessage;
// import com.stupidbeauty.hxlauncher.BuildConfig;
import com.upokecenter.cbor.CBORException;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.huiti.msclearnfootball.AnswerAvailableEvent;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import com.stupidbeauty.hxlauncher.bean.HxShortcutInfo;
import com.stupidbeauty.hxlauncher.datastore.LauncherIconType;
import com.stupidbeauty.qtdocchinese.ArticleInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.stupidbeauty.hxlauncher.interfaces.LocalServerListLoadListener;
// import com.stupidbeauty.hxlauncher.interfaces.ShutDownAt2100LogicInterface;
// import com.stupidbeauty.hxlauncher.logic.ShutDownAt2100Logic;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.stupidbeauty.shutdownat2100.AmqpFunctionName;
import android.os.Process;
import java.util.Random;
import com.stupidbeauty.hxlauncher.manager.ActiveUserReportManager;
import android.os.Debug;
import com.stupidbeauty.grebe.DownloadRequestor;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import com.stupidbeauty.hxlauncher.service.DownloadNotificationService; 
import com.stupidbeauty.farmingbookapp.PreferenceManagerUtil;
import com.stupidbeauty.grebe.DownloadRequestor;
import com.stupidbeauty.hxlauncher.bean.ApplicationNameInternationalizationData;
import com.stupidbeauty.grebe.DownloadRequestorInterface;

public class RandomApplicationActivity extends Activity implements  LocalServerListLoadListener, DownloadRequestorInterface
{
  private int scoreSum; //!< The score sum of installed packages.
  private ArrayList<String> installedPackageNameList; //!< Installed package name list.
  private static final int DefaultScore=10; //!< Default score.
  private static final int RegretTimeSecond=10; //!< Waited enough time to run RandomApplication again. increase score
  private String lastPackageName; //!< The last launched package name.
  private long lastPackageTimeStamp; //!< The timestamp of last launching a package.
  private ActiveUserReportManager activeUserReportManager=null; //!< 活跃用户统计管理器。陈欣。
  private boolean mscIsInitialized=false; //!<讯飞语音识别是否已经初始化。
  private HashMap<String, Long> packageItemLastLaunchTimestampMap=new HashMap<>(); //!<包名加类名的字符串与最后一次启动时间戳之间的映射。
  private HashMap<String, Integer> packageNameItemNamePositionMap=new HashMap<>(); //!<包名加类名的字符串与图标位置之间的映射。
  private HashMap<String, Integer> packageNamePositionMap=new HashMap<>(); //!<包名字符串与图标位置之间的映射。
  private final DownloadRequestor downloadRequestor = new DownloadRequestor();
  PowerManager.WakeLock wakeLock=null; //!<游戏辅助唤醒锁。
  private boolean activityHasBeenResumed=false; //!<活动是否处于被继续的状态，即正常的运行状态。
  private boolean sentVoiceAssociationData=false; //!<是否已经成功发送语音指令关联应用程序数据。

  /**
  * Stop the download notification.
  */
  private void stopDownloadNotification()
  {
    Intent serviceIntent = new Intent(this, DownloadNotificationService.class); //创建意图。
    
    stopService(serviceIntent); // Stop the service.
  } // private void stopDownloadNotification()
    
  @Override
  /**
  * 报告，下载 finished。
  */
  public void reportDownloadFinished(String packageName)
  {
    // launchRipple.setVisibility(View.INVISIBLE); // Hide the downloading icon.
    // circularProgressBar.setProgress(0); // Reset progress.
    
    stopDownloadNotification(); // Stop the download notification.
  } // public void reportDownloadFinished(String packageName)
  
  @Override
  /**
  * 报告，下载 progress。
  */
  public void reportDownloadProgress(String packageName, long downloaded, long total) 
  {
    // circularProgressBar.setProgressMax(total);

    // circularProgressBar.setProgressWithAnimation(downloaded, 323l);
  } // public void reportDownloadProgress(String packageName, long downloaded, long total)
    
  @Override
  /**
  * 报告，下载失败。
  */
  public void  reportDownloadFailed(String packageName) 
  {
    HxLauncherApplication application=HxLauncherApplication.getInstance(); //获取应用程序对象。
    
    application.startCheckUpgrade(); // Start check upgrade again.

    // Chen xin. Voice ui download failec.
    // String mWordSeparators = getResources().getString(R.string.downloadFailed); // load explain text string. download failed

    // voiceUi.say(mWordSeparators); // say , download failed.
    // launchRipple.setVisibility(View.INVISIBLE); // Hide donwloading progress float icon.
    // circularProgressBar.setProgress(0); // Reset progress.
    stopDownloadNotification(); // Stop the download notification.
  } // public void  reportDownloadFailed(String packageName)
    
  public void setSentVoiceShortcutAssociationData(boolean sentVoiceShortcutAssociationData) 
  {
    this.sentVoiceShortcutAssociationData = sentVoiceShortcutAssociationData;
  }
  
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
     * 下载应用程序安装包。
     * @param internationalizationName 安装包路径。
     */
    private void requestDownloadPackage(String internationalizationName, String applicationName, String packageName)
    {
      boolean installed=checkInstalled(packageName); // 检查该应用是不是已经安装了。

      Log.d(TAG,"requestDownloadPackage, installed: "+ installed + ", url: " + internationalizationName + ", appliation name: " + applicationName + ", package name: " + packageName); //Debug.

      if (installed) // 该应用已经安装。
      {
      } // if (installed) // 该应用已经安装。
      else // 该应用尚未安装。
      {
        String string="Downloading " + applicationName; // 通知字符串，正在下载。陈欣。
          
        startTimeCheckService(applicationName); // 启动下载通知服务。陈欣。
        
        // launchRipple.setVisibility(View.VISIBLE); // Show donwloading progress float icon.
        // circularProgressBar.setProgress(0); // Reset progress.

        HxLauncherApplication application=HxLauncherApplication.getInstance(); //获取应用程序对象。
        
        if (packageName==null) // The package name does not exist
        {
          Map<String, String> apkUrlPackageNameMap = application.getApkUrlPackageNameMap(); // Get the map of apk url to package name.
          
          if (apkUrlPackageNameMap!=null) // The url to package name map exists
          {
            packageName = apkUrlPackageNameMap.get(internationalizationName); // Get the package name from apk url.
          } // if (apkUrlPackageNameMap!=null) // The url to package name map exists
        } // if (iconUrl==null) // The icon url does not exist
        
        if (packageName!=null) // The package name exists
        {
          String iconUrl=application.getIconForPackage(packageName); // Get icon url.
          Log.d(TAG, CodePosition.newInstance().toString()+  ", packgage name: " + packageName + ", icon url: " + iconUrl); // Debug.
          
          // Glide.with(application).load(iconUrl).placeholder(R.drawable.vector_66_11).into(applicationIconrightimageView2); //显示图标。

          Map<String, String> packageNameApplicationMap=application.getPackageNameApplicationNameMap(); // 获取包名与应用程序名字的映射

          // rightTextoperationMethodactTitletextView2.setText(applicationName); // Show applicaiton name.

          downloadRequestor.requestDownloadUrl(internationalizationName, internationalizationName, applicationName, packageName, this); //要求下载网址
        } // if (packageName!=null) // The package name exists
      } // else // 该应用尚未安装。
    } //private void requestDownloadPackage(String internationalizationName)

  /**
  * Check whether one package is installed.
  */
  public boolean checkInstalled(String packageName) 
  {
    PackageManager packageManager=getPackageManager(); //获取软件包管理器。
    
    boolean result=false; // 结果。
    
    try
    {
      PackageInfo packageInfo=packageManager.getPackageInfo(packageName,0); //获取对应的软件包信息。

      result=true;
    }
    catch (PackageManager.NameNotFoundException e) //未找到该软件包。
    {
      e.printStackTrace(); //报告错误。
    } //catch (PackageManager.NameNotFoundException e) //未找到该软件包。

    return result;
  } // private bool checkInstalled(String internationalizationName)

  private boolean sentVoiceShortcutAssociationData=false; //!<是否已经成功发送语音指令关联快捷方式的数据。
  private static final int PERMISSIONS_REQUEST = 1;
  private static final String PERMISSION_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
  private static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO; //!<录音权限。
  private boolean foundActivity=false; //!<是否命中了活动。

  private HashMap<String, Integer> voicePackageNameMap; //!< The map of package name and score.

  private HashMap<String, PackageItemInfo> voicePackageNameMapBultin; //!<内置的，语音识别结果与包条目信息之间的映射关系。
  private HashMap<String, HxShortcutInfo> voiceShortcutMapBultin; //!<内置的，语音识别结果与快捷方式编号之间的映射关系．

  private HashMap<String, PackageItemInfo> activityLabelPackageItemInfoMap=new HashMap<>(); //!<活动的标签，与活动本身信息之间的映射。
  private HashMap<String, String> internationalizationDataPackageNameMap=new HashMap<>(); //映射。应用程序的国际化名字与包名之间的映射。
  // private Bus bus; //!<总线 。

  // @BindView(R.id.progressBar) ProgressBar progressBar; //!<进度条。
  private String voiceRecognizeResultString; //!<语音识别结果。

  int ret = 0;

  // @BindView(R.id.statustextView) TextView statustextView; //!<用来显示状态的文字标签。
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

  private int shutDownHour=-1; //!<关机小时数。
  private int shutDownMinute=-1; //!<关机分钟数。
  private boolean exceededShutDownTime=false; //!<是否已经超过了关机时间。

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
        solveLauncherIntents(); //刷新已安装的应用程序。
    } //public void onLoadPackageInfoList()

    // @BindView(R.id.articleListmy_recycler_view) RecyclerView mRecyclerView; //!< 回收视图。
    // private ApplicationInformationAdapter mAdapter; //!<适配器。

    private static final String TAG="RandomApplicationActivity"; //!< 输出调试信息时使用的标记。
    private final String categoryName="default"; //!<要显示的分类的名字。
    // final Map<String,PackageCountObject> packageNameCountObjectMap=new HashMap<>(); //软件包名字与计数对象之间的映射。
    // final List<PackageCountObject> packageCountObjectList=new ArrayList<>(); //!<计数对象列表。

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

      requestWindowFeature(Window.FEATURE_NO_TITLE); //不显示标题栏。

      setContentView(R.layout.launcher_activity);

      ButterKnife.bind(this); //视图注入。

      createActiveUserReportManager(); // 创建管理器，活跃用户统计。陈欣

      bindAdapter(); //绑定适配器。

      launchSmart(); // Smart launch
      
      buildActivityLabelPackageItemInfoMap(); //构造映射，活动的标签，与活动的包条目信息之间的映射。用于语音识别之后快速命中活动。
    } //protected void onCreate(Bundle savedInstanceState)
 
    @Override
    protected void onNewIntent(Intent intent) 
    {
      Log.d(TAG, "onNewIntent"); //Debug.
      super.onNewIntent(intent);

      launchSmart(); // Smart launch
    }
    
    /**
    * Smart launch
    */
    private void launchSmart()
    {
      Log.d(TAG, CodePosition.newInstance().toString()+","+System.currentTimeMillis()); //Debug.
      loadLastPackageName(); // Load the package name launched last time.

      loadPackageScoreList(); // Load the package score list.

      Log.d(TAG, CodePosition.newInstance().toString()+","+System.currentTimeMillis()); //Debug.
      adjustLastPackageScore(); // Adjust the score of the last package.

      reqGameData(); //开始获取数据。
      
      Log.d(TAG, CodePosition.newInstance().toString()+","+System.currentTimeMillis()); //Debug.
      appendNewPackageScoreList(); // Append default score of new packages into the score list.

      boolean launchResult = launchRandomApplication(); // 启动随机应用．
      
      if (launchResult) // Launch success
      {
        Log.d(TAG, CodePosition.newInstance().toString()+","+System.currentTimeMillis()); //Debug.
        saveLastPackageName(); // Save last package name launched.

        savePackageScoreList(); // Save package score list.
      } // if (launchResult) // Launch success
      else // launch failed. usually, that package got removed
      {
        // launchSmart(); // Try to launch again.
        // Chen xin.
        String targetPackageName = getTargetPackageName(); // Get the target package name.
        
        showApplicationInformation(null, targetPackageName, null); // Show the application information activity.
      } // else // launch failed. usually, that package got removed

      Log.d(TAG, CodePosition.newInstance().toString()+","+System.currentTimeMillis()); //Debug.
    } // private void launchSmart()
    
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

      HxLauncherApplication hxLauncherApplication=HxLauncherApplication.getInstance();

      startActivity(launchIntent); //启动活动。
    } //public void showApplicationInformation(View itemView, String packageName)

    /**
    * Append default score of new packages into the score list.
    */
    private void appendNewPackageScoreList()
    {
      for(String packageName: installedPackageNameList) // Check package one by one
      {
        if (voicePackageNameMap.containsKey(packageName)) // Contains the package name.
        {
        } // if (voicePackageNameMap.contains(packageName)) // Contains the package name.
        else // Not contains. need append
        {
          voicePackageNameMap.put(packageName, DefaultScore); // Add into map.
          
          scoreSum+=DefaultScore;
        } // else // Not contains. need append
      } // for(String packageName: installedPackageNameList) // Check package one by one
    } // private void appendNewPackageScoreList()
    
    /**
    * Adjust the score of the last package.
    */
    private void adjustLastPackageScore() 
    {
      if (voicePackageNameMap.containsKey(lastPackageName))
      {
      int currentPackage=voicePackageNameMap.get(lastPackageName); // Query the package object.
      
      long currentTimeSecond=System.currentTimeMillis()/1000; // Get the current ime.
      
      long timeDiuff=currentTimeSecond-lastPackageTimeStamp; // Calculate time difference.
      
      if (timeDiuff>RegretTimeSecond) // Waited enough time to run RandomApplication again. increase score
      {
        currentPackage++; // increase score
        scoreSum++;
      }
      else // Decrese score
      {
        
        if (currentPackage>1)
        {
          currentPackage--; // decrease score
          scoreSum--;

        }
      } // else // Decrese score
      
      voicePackageNameMap.put(lastPackageName, currentPackage); // Update value.
      }
    } // private void adjustLastPackageScore()
    
    /**
    * Load the package name launched last time.
    */
    private void loadLastPackageName() 
    {
      lastPackageName=PreferenceManagerUtil.getLastPackageName(); // Get last time launched package name.
      lastPackageTimeStamp=PreferenceManagerUtil.getLastPackageTime(); // Get last time launched package time stamp. Second.
    } // private void loadLastPackageName()
    
    /**
    * Save last package name launched.
    */
    private void saveLastPackageName() 
    {
      PreferenceManagerUtil.setLastPackageName(lastPackageName); // 设置 last time launched package name.
      PreferenceManagerUtil.setLastPackageTime(lastPackageTimeStamp); // 设置 last time launched package time stamp. Second.
    } // private void saveLastPackageName()
    
    /**
     * 寻找语音识别与软件包映射文件。
     * @return 语音识别与软件包映射文件。
     */
    private  File findVoicePackageMapFile()
    {
      File result=null;

      File filesDir= getFilesDir();

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
            result.createNewFile(); //创建文件。
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
    * Load the package score list.
    */
    private void loadPackageScoreList() 
    {
      scoreSum=0; // Reset score sum.

      File photoFile= findVoicePackageMapFile(); //随机寻找一个照片文件。

      voicePackageNameMap=new HashMap<>(); // 创建映射。

      if (photoFile!=null) //不是空指针。
      {
        if (photoFile.exists()) //文件存在。
        {
          try
          {
            byte[] photoBytes= FileUtils.readFileToByteArray(photoFile); //将照片文件内容全部读取。

            CBORObject videoStreamMessage= CBORObject.DecodeFromBytes(photoBytes); //解析消息。
            
            String json_debug=videoStreamMessage.ToJSONString(); // Convert to sjon.

            Log.d(TAG, "loadPackageScoreList, 421, json: " + json_debug); //Debug.

            CBORObject textList=videoStreamMessage.get("map_"); // 文字列表。
            
            if (textList!=null) // Exists
            {
              int listSize=textList.size(); //获取数组长度。

              for(int messageCounter=0; messageCounter< listSize; messageCounter++) //一个个地解码出来
              {
                CBORObject currentText=textList.get(messageCounter); // 获取 Single map.

                String packageName=currentText.get("packageName").AsString(); // 获取 package name.
                int score=currentText.get("score").AsInt32(); // 获取 score.
                
                scoreSum+=score; // Add score sum.

                voicePackageNameMap.put(packageName, score); // 加入映射。
              } //for(int messageCounter=0; messageCounter< listSize; messageCounter++) //一个个地解码出来
            } // if (textList!=null) // Exists
          }
          catch (CBORException e)
          {
            e.printStackTrace();
          }
          catch (IOException e)
          {
            e.printStackTrace();
          } //catch (IOException e)
        } //if (photoFile.exists()) //文件存在。
      } //if (photoFile!=null) //不是空指针。
    } // private void loadPackageScoreList()
    
    /**
    * Save package score list.
    */
    private void savePackageScoreList() 
    {
      VoicePackageNameMapSaveTask translateRequestSendTask =new VoicePackageNameMapSaveTask(); //创建异步任务。

      translateRequestSendTask.execute(voicePackageNameMap); //执行任务。
    } // private void savePackageScoreList()

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
     * 启动随机应用．
     */
    private boolean launchRandomApplication()
    {
      int currntScoreSum=0; // Current score sum of iterated packages.
      int intentAmount=scoreSum; // 获取启动意图数量．
      Log.d(TAG, CodePosition.newInstance().toString()+","+System.currentTimeMillis()+", score sum: " + intentAmount); //Debug.

      Random random=new Random();

      int intentIndex=random.nextInt(intentAmount); //随机确定一个下标．
      Log.d(TAG, CodePosition.newInstance().toString()+","+System.currentTimeMillis()+", score target: " + intentIndex); //Debug.
      
      String packageToLaunche = getTargetPackageName(); // Package to launch.
      
      Set<String> packageNameList=voicePackageNameMap.keySet(); // Get package name list.
      Log.d(TAG, CodePosition.newInstance().toString()+","+System.currentTimeMillis()+", package name list: " + packageNameList); //Debug.
      
      Log.d(TAG, CodePosition.newInstance().toString()+","+System.currentTimeMillis() + ", score current: " + currntScoreSum); //Debug.

      boolean result = launchApplicationByPackageName(packageToLaunche); //启动应用．
      
      return result;
    } //private void launchRandomApplication()
    
    /**
    * Get the name of the target Package to launch.
    */
    private String getTargetPackageName()
    {
      String result = null;
    
      // 从应用的包信息中获取 meta-data 值
      try 
      {
        ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
        String apiKey = appInfo.metaData.getString("com.stupidbeauty.placeholder.TARGET_PACKAGE");
        
        
        result = apiKey;
        
        // String serverUrl = appInfo.metaData.getString("com.example.app.server_url");

        // 使用获取到的值...
        Log.d("ExampleApp", "API Key: " + apiKey);
        // Log.d("ExampleApp", "Server URL: " + serverUrl);
      }
      catch (PackageManager.NameNotFoundException e) 
      {
        e.printStackTrace();
      }
      
      return result;
    } // private String getTargetPackageName()
    
    /**
     * 根据包名启动应用程序。
     * @param packageName 包名。
     */
    private boolean launchApplicationByPackageName(String packageName)
    {
      Log.d(TAG, CodePosition.newInstance().toString()+","+System.currentTimeMillis() + ", trying to launch package: " + packageName); // Debug.
      boolean result=false; //启动结果
      PackageManager packageManager=getPackageManager(); //获取软件包管理器。

      Intent launchIntent= packageManager.getLaunchIntentForPackage(packageName); //获取当前软件包的启动意图。

      if (launchIntent!=null) //意图存在。
      {
        try //尝试启动活动，并且捕获可能的异常。
        {
          launchApplication(launchIntent); //启动活动。

          result=true; //成功
          Log.d(TAG, CodePosition.newInstance().toString()+","+System.currentTimeMillis() + ", successfully launched package: " + packageName); // Debug.
        } //try //尝试启动活动，并且捕获可能的异常。
        catch (ActivityNotFoundException exception)
        {
          exception.printStackTrace(); //报告错误。
        } //catch (ActivityNotFoundException exception)
      } //if (launchIntent!=null) //意图存在。
      else //意图不存在，则说明对应的应用不存在，后续应当触发自动下载。
      {
        // 你曾经安装并且使用过 《火箭处理逾期》，要不要重新下载最新版体验一下？com.xky.hjclyq
        Log.d(TAG, CodePosition.newInstance().toString()+","+System.currentTimeMillis() + ", package not found: " + packageName); // Debug.
      } // else //意图不存在，则说明对应的应用不存在，后续应当触发自动下载。

      return result;
    } //private void launchApplicationByPackageName(String packageName)

    /**
     * 初始化数据
     */
    private void reqGameData()
    {
      solveLauncherIntents(); //解析启动意图。

      Log.w(TAG, "reqGameData, 731, timestamp: " + System.currentTimeMillis()); //Debug.
    } //private void reqGameData()

    /**
     * 绑定适配器。
     */
    private void bindAdapter()
    {
      int columnsPerRow= getResources().getInteger(  R.integer.columnsPerRow); //每行的列数。

      RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(columnsPerRow,StaggeredGridLayoutManager.VERTICAL); //布局管理器。
    } //private void bindAdapter()

    @Override
    /**
     * 活动重新处于活跃状态。
     */
    protected void onResume()
    {
      super.onResume(); //超类继续工作。

      activityHasBeenResumed=true; //处于正常运行状态。
    } //protected void onResume()

    /**
     * 活动被暂停。
     */
    @Override
    protected void onPause()
    {
      super.onPause();

      activityHasBeenResumed=false; //不是处于正常运行状态。
    } //protected void onPause()

    /**
     * 检查，是否超过了关机时间。
     */
    private void checkWhetherExceededShutDownTime()
    {
      if (shutDownHour>=0) //载入了有效的关机时间。
      {
        GregorianCalendar t=new GregorianCalendar(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。

        GregorianCalendar thresholdTime=new GregorianCalendar(t.get(GregorianCalendar.YEAR),t.get(GregorianCalendar.MONTH),t.get(GregorianCalendar.DATE),shutDownHour,shutDownMinute); //阈值时间。

        if (t.after(thresholdTime)) //时间比阈值时间还要晚。
        {
          exceededShutDownTime=true;
        } //if (t.after(thresholdTime)) //时间比阈值时间还要晚。
      } //if (shutDownHour>=0) //载入了有效的关机时间。
    } //private void checkWhetherExceededShutDownTime()

    /**
     * 显示活动，停止使用手机。
     */
    private void showShutdownAt2100StopUsingPhone()
    {
      Intent launchIntent=new Intent(); //获取当前软件包的启动意图。
      launchIntent.setAction(Intent.ACTION_MAIN); //设置动作。

      String packageName="com.stupidbeauty.shutdownat2100androidnative"; //21点关机。
      String serviceName="com.stupidbeauty.shutdownat2100androidnative.StopUsingPhoneActivity"; //21点关机。

      ComponentName cn = new ComponentName(packageName, serviceName);
      launchIntent.setComponent(cn);

        launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //设置标志位。
        startActivity(launchIntent); //启动扫描窗口。

    } //private void showShutdownAt2100StopUsingPhone()


    /**
     * 振动。
     */
    private void vibrate()
    {
        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate( 100);

    } //private void vibrate()


    @Override
    /**
     * 活动被停止。被另一个活动完全覆盖。
     */
    protected void onStop()
    {
        super.onStop(); //超类停止。

        saveApplicationLaunchCount(); //保存应用的启动计数数据。
    } //protected void onStop()

    /**
     * 保存应用的启动计数数据。
     */
    private void saveApplicationLaunchCount()
    {
    } //private void saveApplicationLaunchCount()

    /**
     * 滚动到图标所在的位置。
     * @param packageItemInfopackageName 应用程序包名。
     * @param packageItemInfoname 活动的类名。
     */
    private void scrollToIconPosition(String packageItemInfopackageName, String packageItemInfoname)
    {
        int position = getItemPosition(packageItemInfopackageName, packageItemInfoname); //获取位置。
//        int position = mAdapter.getItemPosition(packageItemInfopackageName, packageItemInfoname); //获取位置。

        // mRecyclerView.smoothScrollToPosition(position); //丝滑滚动到位置。
    } //private void scrollToIconPosition(String packageItemInfopackageName, String packageItemInfoname)

    /**
     * 获取活动条目的位置。
     * @param packageItemInfopackageName 包名。
     * @param packageItemInfoname 活动类名。
     * @return 图标的位置编号。
     */
    public int getItemPosition(String packageItemInfopackageName, String packageItemInfoname)
    {
        Log.d(TAG, "getItemPosition, packageNameItemNamePositionMap: " + packageNameItemNamePositionMap); //Debug.
        int result=0; //结果。

        if (packageItemInfoname!=null) //有类名。
        {
            Integer resultInteger=packageNameItemNamePositionMap.get(packageItemInfopackageName+"/"+packageItemInfoname); //从映射中查找对应的数字。

            if (resultInteger!=null) //不是空指针。
            {
                result=resultInteger; //从映射中查找。
            } //if (resultInteger!=null) //不是空指针。
        } //if (packageItemInfoname!=null) //有类名。
        else //没有类名。
        {
          Log.d(TAG, "getItemPosition, packageNamePositionMap: " + packageNamePositionMap + ", package name: " + packageItemInfopackageName); //Debug.
          result=packageNamePositionMap.get(packageItemInfopackageName); //从映射中查找。
        } //else //没有类名。

        return result;
    } //public int getItemPosition(String packageItemInfopackageName, String packageItemInfoname)


    /**
     * 构造映射，活动的标签，与活动的包条目信息之间的映射。用于语音识别之后快速命中活动。
     */
    private void buildActivityLabelPackageItemInfoMap()
    {
      Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
      mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

      PackageManager packageManager=getPackageManager(); //获取软件包管理器。

      List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent, 0);
      Log.w(TAG, "findVoiceTargetApplicationNameAndLaunch, 1213, timestamp: " + System.currentTimeMillis()); //Debug.

      //按照活动的标题来匹配：
      for (ResolveInfo temp : appList) //遍历解析结果，并一个个加入到列表中。
      {
        Log.i(TAG, "buildActivityLabelPackageItemInfoMap, package and activity name = " + temp.activityInfo.packageName + "    " + temp.activityInfo.name); //Debug.

        CharSequence activityapplicationLabel=temp.activityInfo.loadLabel(packageManager); //获取文字标记。
        String activityapplicationLabelString=activityapplicationLabel.toString(); //转换成字符串。

        PackageItemInfo currentPackageItemInfo=new PackageItemInfo(); //当前的包条目信息对象。

        currentPackageItemInfo.packageName=temp.activityInfo.packageName;
        currentPackageItemInfo.name=temp.activityInfo.name; //记录活动名字。

        activityLabelPackageItemInfoMap.put(activityapplicationLabelString, currentPackageItemInfo); //加入映射。
      } //for (ResolveInfo temp : appList) //遍历解析结果，并一个个加入到列表中。
    } //private void buildActivityLabelPackageItemInfoMap()

    /**
     * 解析启动意图。
     */
    private void solveLauncherIntents()
    {
      ArrayList<ArticleInfo> articleInfoArrayList = new ArrayList<>();

      Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
      mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

      PackageManager packageManager=getPackageManager(); //获取软件包管理器。

      List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent, 0);

      int position=0; //条目的位置。
      
      installedPackageNameList=new ArrayList<>(); // Create list.

      //按照活动的标题来匹配：
      for (ResolveInfo temp : appList) //遍历解析结果，并一个个加入到列表中。
      {
        if (installedPackageNameList.contains(temp.activityInfo.packageName)) // Contains.
        {
        } // if (installedPackageNameList.contains(temp.activityInfo.packageName)) // Contains.
        else // Not contains.
        {
          installedPackageNameList.add(temp.activityInfo.packageName); // Add to list.
        } // else // Not contains.
      
        Intent activitylaunchIntent=new Intent(mainIntent);
        activitylaunchIntent.setClassName(temp.activityInfo.packageName, temp.activityInfo.name); //设置类名。

        CharSequence activityapplicationLabel=temp.activityInfo.loadLabel(packageManager); //获取文字标记。

        ArticleInfo currentApplication=new ArticleInfo(); //创建应用程序信息对象。

        currentApplication.setApplicationLabel(activityapplicationLabel); //设置应用程序文字。
        currentApplication.setLaunchIntent(activitylaunchIntent); //设置启动意图。
        currentApplication.setPackageName(temp.activityInfo.packageName); //设置包名。
        currentApplication.setActivityName(temp.activityInfo.name); //设置活动名字。

        articleInfoArrayList.add(currentApplication); //添加应用。

        packageNameItemNamePositionMap.put(temp.activityInfo.packageName+"/"+temp.activityInfo.name, position); //记录映射。
        packageNamePositionMap.put(temp.activityInfo.packageName, position); //记录映射。

        position++; //计数。
      } //for (ResolveInfo temp : appList) //遍历解析结果，并一个个加入到列表中。

      // mAdapter.setArticleInfoArrayList(articleInfoArrayList); //设置文章信息列表。
      // mAdapter.notifyDataSetChanged(); //通知数据变更。
    } //private void solveLauncherIntents()

    /**
     * 启动快捷方式。
     * @param shortcutInfo 要启动的快捷方式信息对象。
     */
    public void launchShortcut(ShortcutInfo shortcutInfo)
    {
      LauncherApps launcherApps=(LauncherApps) (getSystemService(Context.LAUNCHER_APPS_SERVICE)); //获取启动器应用对象。

      launcherApps.startShortcut(shortcutInfo, null, null); //启动快捷方式。
    } //public void launchShortcut(ShortcutInfo shortcutInfo)

    /**
     * 启动快捷方式。
     * @param shortcutInfo 要启动的快捷方式信息对象。
     */
    public void launchShortcut(String packageName, String shortcutId)
    {
      LauncherApps launcherApps=(LauncherApps) (getSystemService(Context.LAUNCHER_APPS_SERVICE)); //获取启动器应用对象。

      launcherApps.startShortcut(packageName, shortcutId, null, null, Process.myUserHandle()); //启动快捷方式。
    } //public void launchShortcut(ShortcutInfo shortcutInfo)

    /**
     * 启动应用。
     * @param launchIntent 启动用的意图。
     */
    public void launchApplication(Intent launchIntent)
    {

        Log.d(TAG, "launchApplication, launch intent: " + launchIntent); //Debug.
        try //尝试启动活动，并且捕获可能的异常。
        {
                startActivity(launchIntent); //启动活动。

        } //try //尝试启动活动，并且捕获可能的异常。
        catch (ActivityNotFoundException exception)
        {
            exception.printStackTrace(); //报告错误。
        } //catch (ActivityNotFoundException exception)
    } //private void launchApplication(Intent launchIntent)

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
     * 显示软件包列表。
     * @param packageInfoList 软件包列表。
     */
    private void showInstalledPackages(List<PackageInfo>  packageInfoList)
    {
        solveLauncherIntents(); //解析启动意图，并填充数据。
    } //void showInstalledPackages(List<PackageInfo>  packageInfoList)
}
