package com.stupidbeauty.placeholder.activity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.stupidbeauty.codeposition.CodePosition;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.BufferedReader;
import com.koushikdutta.async.future.FutureCallback;
import android.app.usage.UsageStatsManager;
import android.provider.Settings;
import org.apache.commons.collections4.SetValuedMap;
import android.util.Pair;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.app.usage.StorageStats;
import android.os.storage.StorageManager;
import android.os.Process;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.WallpaperManager;
import com.stupidbeauty.voiceui.VoiceUi;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.StatFs;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.net.Uri;
import com.stupidbeauty.hxlauncher.manager.ActiveUserReportManager;
import android.os.UserHandle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.StatFs;
import android.app.usage.StorageStatsManager;
import android.app.usage.UsageStats;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.stupidbeauty.qtdocchinese.ArticleInfo;
import com.stupidbeauty.placeholder.R2;
import com.stupidbeauty.placeholder.R;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.stupidbeauty.placeholder.adapter.ApplicationAdapter;

public class RemoveSimilarApplicaitonsActivity extends Activity 
{
  private static final String TAG="RemoveSimilarApplicaitonsActivity"; //!< 输出调试信息时使用的标记。
  private static final int REQUEST_CODE_USAGE_STATS = 1;
  @BindView(R2.id.recycler_view) RecyclerView recyclerView;

  private ApplicationAdapter mAdapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) 
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_remove_similar_applications);
    ButterKnife.bind(this);

    mAdapter = new ApplicationAdapter();
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(mAdapter);

    // Load the applications on startup
    loadApplications();
  }

    @OnClick(R2.id.close_button)
    public void onCloseButtonClick() {
        finish();
    }

    private void loadApplications() {
        // Implementation to find applications with the specified metadata
        List<ArticleInfo> apps = findSimilarApplications("com.stupidbeauty.placeholder.TARGET_PACKAGE");

        // Sort by size
        Collections.sort(apps, (a, b) -> Long.compare(b.getSize(), a.getSize()));

        // Update the UI
        mAdapter.setApplications(apps);
    }

    /**
     * Finds applications that have the specified metadata key and returns information about the applications
     * that are installed on the device.
     *
     * @param metadataKey The metadata key to search for.
     * @return A list of ArticleInfo objects containing information about the installed applications.
     */
    private List<ArticleInfo> findSimilarApplications(String metadataKey) {
        List<ArticleInfo> similarApps = new ArrayList<>();
        PackageManager packageManager = getPackageManager(); // Get the package manager for the current context

        // Get all installed applications
        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo appInfo : installedApps) {
            // Check if the application contains the metadata key
            if (appInfo.metaData != null && appInfo.metaData.containsKey(metadataKey)) {
                // Extract the target package name from the metadata value
                String targetPackageName = appInfo.metaData.getString(metadataKey);

                // Check if the target application is installed
                if (isPackageInstalled(targetPackageName, packageManager)) {
                    // Get the target application's information
                    ArticleInfo targetAppInfo = getApplicationInfo(targetPackageName, packageManager);
                    similarApps.add(targetAppInfo);
                }
            }
        }

        return similarApps;
    }

    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private ArticleInfo getApplicationInfo(String packageName, PackageManager packageManager) 
    {
      ApplicationInfo appInfo;
      ArticleInfo targetAppInfo = new ArticleInfo();
      
      try 
      {
        appInfo = packageManager.getApplicationInfo(packageName, 0);
        // Drawable icon = getDrawable(appInfo.icon);
        String appName = (String) packageManager.getApplicationLabel(appInfo);
        long size = getAppSize(packageName, packageManager); // Placeholder for getting app size

        targetAppInfo.setApplicationLabel(appName); // Using setApplicationLabel instead of constructor
        targetAppInfo.setPackageName(packageName);
        targetAppInfo.setActivityName(packageName); // Assuming the activity name is the same as the package name
        targetAppInfo.setLaunchIntent(packageManager.getLaunchIntentForPackage(packageName));
        targetAppInfo.setApplicationLabel(appName); // Reusing appName as applicationLabel, as it's already a CharSequence
        targetAppInfo.setAutoRun(false);
        targetAppInfo.setSize(size); // Set the size.

      }
      catch (PackageManager.NameNotFoundException e) 
      {
        // throw new RuntimeException("Could not find package: " + packageName, e);
        e.printStackTrace();
      }
      return targetAppInfo;
    }

    private void requestUsageStatsPermission() 
    {
      Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      if (!hasUsageStatsPermission()) 
      {
        // 如果权限未被授予，则引导用户前往设置页面
        showSettingsScreen();
        Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      }
      else 
      {
        // 如果权限已经被授予，则可以开始使用它
        Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
        onPermissionGranted();
      }
      Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
    }

    private boolean hasUsageStatsPermission() 
    {
      boolean result = false; // The result;
      Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
      if (usageStatsManager == null) 
      {
      }
      else // The usage stats manager exists.
      {
        Log.d(TAG, CodePosition.newInstance().toString()); //Debug.

        long currentTimeMillis = System.currentTimeMillis();
        long time10MinutesAgo = currentTimeMillis - (10 * 60 * 1000); // 10 minutes ago

        Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
        try 
        {
          // 尝试调用需要权限的方法
          Map<String, UsageStats> usageMap = usageStatsManager.queryAndAggregateUsageStats(time10MinutesAgo, currentTimeMillis);
          Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
          
          if (usageMap!=null) // Got the map successfully.
          {
            if (usageMap.size() > 0) // Their are items in the map.
            {
              Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
              result = true;
            } // if (usageMap.size() > 0) // Their are items in the map.
          } // if (usageMap!=null) // Got the map successfully.
          
          Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
        }
        catch (SecurityException e) 
        {
          Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
          // 捕获 SecurityException 表明权限未被授予
          // return false;
        }
        Log.d(TAG, CodePosition.newInstance().toString()); //Debug.
      } // else // The usage stats manager exists.
      
      return result;
    } // private boolean hasUsageStatsPermission() 

    private void showSettingsScreen() 
    {
      // 打开应用设置页面
      Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivityForResult(intent, REQUEST_CODE_USAGE_STATS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_USAGE_STATS) {
            if (hasUsageStatsPermission()) {
                onPermissionGranted();
            } else {
                onPermissionDenied();
            }
        }
    }

    private void onPermissionGranted() 
    {
      // 权限已被用户授予，可以继续使用
      // 在这里添加你需要执行的操作
      loadApplications();
    }

    private void onPermissionDenied() {
        // 权限被用户拒绝
        // 可以提示用户为什么需要这个权限
    }

    private long getAppSize(String packageName, PackageManager packageManager) 
    {
      // Check if the permission is granted
      if (!hasUsageStatsPermission()) // We do not have the permission.
      {
        // If you need to request the permission, do so here.
        // For simplicity, we'll just return 0L if the permission is not granted.
        
                // 请求 PACKAGE_USAGE_STATS 权限
        requestUsageStatsPermission();

        
        return 0L;
      } // if (!hasUsageStatsPermission()) // We do not have the permission.

      StorageStatsManager storageStatsManager = (StorageStatsManager) getSystemService(Context.STORAGE_STATS_SERVICE);
      PackageInfo packageInfo;
      long result = 0; // Result;

      try 
      {
          packageInfo = packageManager.getPackageInfo(packageName, 0);
        // Query the stats for the given package
        StorageStats storageStats = storageStatsManager.queryStatsForPackage(
            // Environment.getDefaultStorageUuid(),
            StorageManager.UUID_DEFAULT,
            packageName,
            // new UserHandle(UserHandle.myUserId())
            Process.myUserHandle()
        );
        
        // Return the total bytes used by the application
        
        result =  storageStats.getDataBytes() + storageStats.getAppBytes();
      }
      catch (PackageManager.NameNotFoundException e) 
      {
          e.printStackTrace();
      }
      catch (IOException e) 
      {
          e.printStackTrace();
      }
      catch (SecurityException e) 
      {
        // 捕获 SecurityException 表明权限未被授予
        // return false;
        requestUsageStatsPermission(); // Request the permission.
      }


      return result;
    }


}
