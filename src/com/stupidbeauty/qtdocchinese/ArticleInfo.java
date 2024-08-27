package com.stupidbeauty.qtdocchinese;


import android.content.Intent;

public class ArticleInfo {
    private boolean autoRun = false;
    private String packageName;
    private String activityName;
    private Intent launchIntent;
    private CharSequence applicationLabel;
    private long size; // New attribute for the app size

    public boolean isAutoRun() {
        return autoRun;
    }

    public void setAutoRun(boolean autoRun) {
        this.autoRun = autoRun;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Intent getLaunchIntent() {
        return launchIntent;
    }

    public void setLaunchIntent(Intent launchIntent) {
        this.launchIntent = launchIntent;
    }

    public CharSequence getApplicationLabel() {
        return applicationLabel;
    }

    public void setApplicationLabel(CharSequence applicationLabel) {
        this.applicationLabel = applicationLabel;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
