package com.stupidbeauty.hxlauncher.external;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.stupidbeauty.hxlauncher.Constants;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import com.stupidbeauty.shutdownat2100.AmqpFunctionName;
// import com.stupidbeauty.shutdownat2100.AmqpMessage;
import com.stupidbeauty.shutdownat2100.ShutDownAt2100ConfigurationMessage;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.GregorianCalendar;

public class ShutDownAt2100Manager
{
    private int shutDownMinute=-1; //!<关机分钟数。
    private int shutDownHour=-1; //!<关机小时数。

    private boolean exceededShutDownTime=false; //!<是否已经超过了关机时间。

    public boolean isExceededShutDownTime() {
        return exceededShutDownTime;
    }

    /**
     * 检查关机时间。
     */
    public void checkShutDownTime()
    {
        //是否超过了关机时间。


        checkWhetherExceededShutDownTime(); //检查，是否超过了关机时间。

        if (exceededShutDownTime) //是超过了关机时间。
        {
//            executeShutDown(); //执行关机过程。

            showShutdownAt2100StopUsingPhone(); //显示活动，停止使用手机。
        } //if (exceededShutDownTime) //是超过了关机时间。

    } //private void checkShutDownTime()


    /**
     * 显示活动，停止使用手机。
     */
    private void showShutdownAt2100StopUsingPhone()
    {
        Intent launchIntent=new Intent(); //获取当前软件包的启动意图。
        launchIntent.setAction(Intent.ACTION_MAIN); //设置动作。
//    launchIntent.addCategory(Intent.CATEGORY_LAUNCHER); //设置分类 。

        String packageName="com.stupidbeauty.shutdownat2100androidnative"; //21点关机。
        String serviceName="com.stupidbeauty.shutdownat2100androidnative.StopUsingPhoneActivity"; //21点关机。

        ComponentName cn = new ComponentName(packageName, serviceName);
        launchIntent.setComponent(cn);

        launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //设置标志位。

        Context context= HxLauncherApplication.getAppContext();

        try {
            context.startActivity(launchIntent); //启动扫描窗口。
        }
        catch (ActivityNotFoundException e)
        {
            e.printStackTrace();
        }

    } //private void showShutdownAt2100StopUsingPhone()


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
     * 载入21点关机的配置信息。
     */
    public void loadShutDownAt2100Configuration()
    {
        File goddessCameraDirectory=new File(Constants.DirPath.FARMING_BOOK_APP_SD_CARD_PATH); //女神相机目录。

        goddessCameraDirectory.mkdirs(); //创建目录。

        File configurationFile=new File(goddessCameraDirectory, "shutdownat2100.oot"); //配置文件对象。

        try {
            if (configurationFile.exists()) //文件存在。
            {
                byte[] messageContent= FileUtils.readFileToByteArray(configurationFile); //读取内容。

                //获取关机小时数。
            } //if (configurationFile.exists()) //文件存在。

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    } //private void loadShutDownAt2100Configuration()



}
