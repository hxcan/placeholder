package com.stupidbeauty.blindbox.manager;

import com.stupidbeauty.hxlauncher.LauncherActivity;
import android.widget.Button;
import com.bumptech.glide.Glide;
import com.stupidbeauty.threeupgrade.DownloadRequestor;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import com.stupidbeauty.upgrademanager.logic.VersionComparator;
import io.github.g00fy2.versioncompare.Version;
import com.stupidbeauty.hxlauncher.interfaces.LocalServerListLoadListener;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import com.stupidbeauty.voiceui.VoiceUi;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import android.text.TextUtils;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
// import android.content.Intent;
import android.Manifest;
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
import android.os.Vibrator;
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
import android.widget.Button;
import com.bumptech.glide.Glide;
import com.stupidbeauty.threeupgrade.DownloadRequestor;
import com.stupidbeauty.hxlauncher.Constants;
import com.stupidbeauty.placeholder.R2;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;
// import com.google.protobuf.ByteString;
import com.stupidbeauty.threeupgrade.AnswerAvailableEvent;
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
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import static android.content.Intent.ACTION_PACKAGE_CHANGED;
import static android.content.Intent.ACTION_PACKAGE_REPLACED;
import static android.content.Intent.EXTRA_COMPONENT_NAME;
import static android.content.Intent.EXTRA_PACKAGE_NAME;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_DYNAMIC;
import static android.content.pm.LauncherApps.ShortcutQuery.FLAG_MATCH_MANIFEST;

public class ShareManager
{
  private VoiceUi voiceUi=null; //!< 语音交互对象。
  // @BindView(R2.id.changeApplicationButtonion) Button changeApplicationButtonion; //!< The button of change application.
  public static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";
  public static final String VIEW_NAME_HEADER_TITLE = "detail:header:title";
  private AnimationDrawable rocketAnimation; //!<录音按钮变暗
  // @BindView(R.id.hitApplicationIcon) ImageView hitApplicationIcon; //!<命中的应用的图标。
  // @BindView(R.id.microphoneIcon) ImageView microphoneIcon; //!< 升级按钮图标。
  // @BindView(R.id.launcher_activity) RelativeLayout launcher_activity; //!<整个启动活动
  private HashMap<String, Long> packageItemLastLaunchTimestampMap=new HashMap<>(); //!<包名加类名的字符串与最后一次启动时间戳之间的映射。
  private HashMap<String, String> serverVoiceCommandResponseIgnoreMap=new HashMap<>(); //!<服务器的回复中，要忽略掉的关系映射
  private HashMap<String, ShortcutInfo> shortcutTitleInfoMap; //!<快捷方式的标题与快捷方式对象本身的映射。
  private HashMap<String, ShortcutInfo> shortcutIdInfoMap; //!<快捷方式的编号与快捷方式对象本身的映射
  private HashMap<String, Integer> packageNameItemNamePositionMap=new HashMap<>(); //!<包名加类名的字符串与图标位置之间的映射。
  private HashMap<String, Integer> packageNamePositionMap=new HashMap<>(); //!<包名字符串与图标位置之间的映射。
  private boolean sentVoiceShortcutAssociationData=false; //!< 是否已经成功发送语音指令关联快捷方式的数据。
  private static final String PERMISSION_FINE_LOCATIN = Manifest.permission.ACCESS_FINE_LOCATION; //!< 位置权限
  private MultiMap<String, PackageItemInfo> voicePackageNameMap; //!< 语音识别结果与包条目信息之间的映射关系。本设备独有的

  // @BindView(R.id.wallpaper) ImageView wallpaper; //!< 墙纸视图。
  // @BindView(R.id.progressBar) ProgressBar progressBar; //!< 进度条。
  // @BindView(R.id.applicationIconrightimageView2) ImageView applicationIconrightimageView2; //!< 应用图标图片。陈欣。
  // @BindView(R.id.statustextView) TextView statustextView; //!< 用来显示状态的文字标签。
  // @BindView(R.id.newVersionView) TextView newVersionView; //!< New version text view.
  private int recognizeCounter=0; //!< 识别计数器．
  private Vibrator vibrator;
  private String applicationName=""; //!< 应用程序名字
  private ImageView mHeaderImageView;
  private TextView mHeaderTitle;
  private String packagename; //!< 包名。陈欣。
  private String activityName; //!< 活动名字。
  private String cancelledPackageName = null; //!< cancelled package name.
  private RequestQueue mQueue; //!< Volley请求队列。

  private String constructBlindBoxUrl(String packagename) 
  {
    String result="tu:" + packagename;
      
    return result;
  } // constructBlindBoxUrl(packagename) 
    
  public void shareViaText(Context context, String applicationName, String packagename)
  {
    //       Chen xin

    /*Create an ACTION_SEND Intent*/
    Intent intent = new Intent(android.content.Intent.ACTION_SEND);

    /*This will be the actual content you wish you share.*/

    HxLauncherApplication hxlauncherApplication=HxLauncherApplication.getInstance(); // 获取应用对象。
    String shareBody = hxlauncherApplication.getString(R2.string.sharing_application) + applicationName + "\n " + constructBlindBoxUrl(packagename) + " \n" + constructBlindBoxDownloadUrlString();

    /*The type of the content is text, obviously.*/
    intent.setType("text/plain");
    /*Applying information Subject and Body.*/

    intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
    /*Fire!*/

    context.startActivity(Intent.createChooser(intent, ""));
  } // public void shareViaText()
  
  private String constructBlindBoxDownloadUrlString()
  {
    HxLauncherApplication hxlauncherApplication=HxLauncherApplication.getInstance(); // 获取应用对象。
    String result=hxlauncherApplication.getString(R2.string.blindBoxCanBeDownloadedAt);
    
    Map<String,String> packageNameUrlMap=hxlauncherApplication.getPackageNameUrlMap(); // 获取 map of package name to download url.

    if (packageNameUrlMap!=null) // 数据存在。
    {
      String installerUrl=packageNameUrlMap.get(hxlauncherApplication.getPackageName()); // 获取 download url.

      Log.d(TAG,"triggerUpgrade, installerUrl: "+ installerUrl+ ", package name: " + packagename); // Debug.

      if (installerUrl!=null) // 有 installer url.
      {
        Log.d(TAG,"triggerUpgrade, 183, package url: "+ installerUrl+ ", package name: " + packagename); // Debug.

//         String applicationName=packageNameApplicationMap.get(packagename); // 应用程序名字

//         requestDownloadPackage(installerUrl, applicationName, intalleryTye); // 下载应用程序安装包。
        result= hxlauncherApplication.getString(R2.string.blindBoxCanBeDownloadedAtPrompt) + installerUrl; // Insert most recent download url.
      } //if (installerUrl!=null) // 有国际化名字。
    } //if (packageNameUrlMap!=null) // 数据存在。

    return result;
  } // private String constructBlindBoxDownloadUrlString()

    private final DownloadRequestor downloadRequestor = new DownloadRequestor();

    private static final String TAG="ApplicationInformationA"; //!< 输出调试信息时使用的标记。
    private final String categoryName="default"; //!<要显示的分类的名字。
}

