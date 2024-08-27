package com.stupidbeauty.placeholder.activity;

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

public class RemoveSimilarApplicaitonsActivity extends AppCompatActivity {

    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;

    private ApplicationAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

private ArticleInfo getApplicationInfo(String packageName, PackageManager packageManager) {
    ApplicationInfo appInfo;
    try {
        appInfo = packageManager.getApplicationInfo(packageName, 0);
        Drawable icon = getDrawable(appInfo.icon);
        String appName = (String) packageManager.getApplicationLabel(appInfo);
        long size = getAppSize(packageName, packageManager); // Placeholder for getting app size

        ArticleInfo targetAppInfo = new ArticleInfo();
        targetAppInfo.setApplicationLabel(appName); // Using setApplicationLabel instead of constructor
        targetAppInfo.setPackageName(packageName);
        targetAppInfo.setActivityName(packageName); // Assuming the activity name is the same as the package name
        targetAppInfo.setLaunchIntent(packageManager.getLaunchIntentForPackage(packageName));
        targetAppInfo.setApplicationLabel(appName); // Reusing appName as applicationLabel, as it's already a CharSequence
        targetAppInfo.setAutoRun(false);

        return targetAppInfo;
    } catch (PackageManager.NameNotFoundException e) {
        throw new RuntimeException("Could not find package: " + packageName, e);
    }
}

    private long getAppSize(String packageName, PackageManager packageManager) {
        // Check if the permission is granted
        if (checkSelfPermission(Manifest.permission.PACKAGE_USAGE_STATS) != PackageManager.PERMISSION_GRANTED) 
        {
            // If you need to request the permission, do so here.
            // For simplicity, we'll just return 0L if the permission is not granted.
            return 0L;
        }

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


        return result;
    }


}
