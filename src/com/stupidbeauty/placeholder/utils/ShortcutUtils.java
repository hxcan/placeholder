package com.stupidbeauty.placeholder.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.stupidbeauty.placeholder.R;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import androidx.annotation.RequiresApi;
import com.stupidbeauty.farmingbookapp.PreferenceManagerUtil;
import java.util.Timer;
import java.util.TimerTask;
import android.Manifest;
import android.annotation.SuppressLint;
import java.util.Collections;

public class ShortcutUtils {
    private static final String TAG = "ShortcutUtils";
    private static final String TARGET_PACKAGE_META_KEY = "com.stupidbeauty.placeholder.TARGET_PACKAGE";

    /**
     * 读取目标包名
     * @param context 应用上下文
     * @return 目标包名
     */
    private static String getTargetPackageName(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                return appInfo.metaData.getString(TARGET_PACKAGE_META_KEY);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to load meta-data", e);
        }
        return null;
    }
    
        /** 
     * 获取目标应用程序的标题
     * @param context 应用上下文
     * @param packageName 目标应用程序的包名
     * @return 目标应用程序的标题
     */
    private static String getTargetAppTitle(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            return packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to get application title", e);
        }
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void updateUninstallShortcut(Context context) {
        if (!PreferenceManagerUtil.isShortcutUpdated()) {
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);

            if (shortcutManager != null) {
                String targetPackageName = getTargetPackageName(context);
                if (targetPackageName == null) {
                    Log.e(TAG, "Target package name is null. Cannot create uninstall shortcut.");
                    return;
                }

                String appName = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
                String newLabel = "卸载 " + appName;

                ShortcutInfo updatedShortcut = new ShortcutInfo.Builder(context, "uninstall_shortcut")
                        .setShortLabel(newLabel)
                        .setLongLabel(newLabel)
                        .setIcon(Icon.createWithResource(context, R.drawable.ic_uninstall))
                        .setIntent(new Intent(Intent.ACTION_DELETE, Uri.parse("package:" + targetPackageName)))
                        .build();

                shortcutManager.updateShortcuts(Collections.singletonList(updatedShortcut));

                // 记录已经更新状态
                PreferenceManagerUtil.setShortcutUpdated(true);
            }
        }
    }
    
        @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createUninstallShortcut(Context context) {
        // 检查是否已经创建过快捷方式
        if (!PreferenceManagerUtil.isShortcutUpdated()) {
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);

            if (shortcutManager != null) {
                String targetPackageName = getTargetPackageName(context);
                if (targetPackageName == null) {
                    Log.e(TAG, "Target package name is null. Cannot create uninstall shortcut.");
                    return;
                }

                String targetAppTitle = getTargetAppTitle(context, targetPackageName);
                if (targetAppTitle == null) {
                    Log.e(TAG, "Target app title is null. Cannot create uninstall shortcut.");
                    return;
                }

                String shortcutLabel = "卸载 " + targetAppTitle;

                // 创建卸载Intent
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, Uri.parse("package:" + targetPackageName));

                // 创建ShortcutInfo对象
                ShortcutInfo shortcut = new ShortcutInfo.Builder(context, "uninstall_shortcut_id")
                        .setShortLabel(shortcutLabel)
                        .setLongLabel(shortcutLabel)
                        .setIcon(Icon.createWithResource(context, R.drawable.ic_uninstall)) // 请确保你有一个名为ic_uninstall的图标资源
                        .setIntent(uninstallIntent)
                        .build();

                // 添加动态快捷方式
                shortcutManager.addDynamicShortcuts(Collections.singletonList(shortcut));

                // 记录已经创建快捷方式的状态
                PreferenceManagerUtil.setShortcutUpdated(true);
            }
        }
    }

}
