package com.stupidbeauty.hxlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoRunManager
{
    private static final String TAG="AutoRunManager"; //!<输出调试信息时使用的标记。

    private final LauncherActivity context; //!<上下文。

    public AutoRunManager(LauncherActivity context1)
    {
        context=context1; //记录上下文。

    }

    /**
     * 考虑要不要执行自动启动动作。
     */
    public void assessAutoRun()
    {
        HxLauncherApplication baseApplication= HxLauncherApplication.getInstance(); //获取应用程序对象。

        boolean firstRun=baseApplication.isFirstRunAfterBoot(); //获取标志，是启动后初次运行。

        if (firstRun) //是初次运行。
        {
            baseApplication.setFirstRunAfterBoot(false); //不再是初次运行了。
        } //if (firstRun) //是初次运行。
    } //private void assessAutoRun()

    /**
     * 随机寻找一个照片文件。
     * @return 随机寻找的一个照片文件。
     */
    @SuppressWarnings("StatementWithEmptyBody")
    private  File findRandomPhotoFile()
    {
        File result=null;

        File filesDir=context.getFilesDir();

        Log.d(TAG, "findRandomPhotoFile, files dir: "+ filesDir); //Debug.

        if (filesDir==null) //该目录不存在。
        {

        } //if (filesDir==null) //该目录不存在。
        else //该目录存在。
        {
            result=new File(filesDir.getAbsolutePath()+"/packageAutoRunMap.otz"); //指定文件名。
//            R

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






}


