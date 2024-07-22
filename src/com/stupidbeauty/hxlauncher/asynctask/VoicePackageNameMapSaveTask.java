package com.stupidbeauty.hxlauncher.asynctask;

import com.upokecenter.cbor.CBORObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import java.io.ByteArrayOutputStream;
import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.os.AsyncTask;
import android.util.Log;
import com.stupidbeauty.hxlauncher.VoicePackageMapItemMessage;
// import com.stupidbeauty.hxlauncher.VoicePackageMapMessage;
import com.google.protobuf.ByteString;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.stupidbeauty.farmingbookapp.PreferenceManagerUtil;
// import com.stupidbeauty.hxlauncher.AndroidApplicationMessage;
// import com.stupidbeauty.hxlauncher.BuildConfig;
// import com.stupidbeauty.hxlauncher.VoiceCommandHitDataMessageProtos;
// import com.stupidbeauty.hxlauncher.VoicePackageMapItemMessageProtos;
// import com.stupidbeauty.hxlauncher.VoicePackageMapMessageProtos;
import com.stupidbeauty.hxlauncher.application.HxLauncherApplication;
import com.stupidbeauty.hxlauncher.datastore.LauncherIconType;
import java.util.Set;
import java.util.HashSet;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Random;
// import static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.Constants.Networks.RabbitMQPassword;
// import static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.Constants.Networks.RabbitMQUserName;
// import static com.stupidbeauty.comgooglewidevinesoftwaredrmremover.Constants.Networks.TRANSLATE_REQUEST_QUEUE_NAME;
// import static com.stupidbeauty.hxlauncher.HxLauncherIconType.PbActivityIconType;
// import static com.stupidbeauty.hxlauncher.HxLauncherIconType.PbShortcutIconType;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Hxcan
 * @since Mar 13, 2014
 */
public final class VoicePackageNameMapSaveTask extends AsyncTask<Object, Void, Boolean>
{
  private static final String TAG="VoicePackageNameMapSaveTask"; //!<输出调试信息时使用的标记。

  @Override
  protected Boolean doInBackground(Object... params)
  {
    //参数顺序：
    //            private MultiMap<String, PackageItemInfo> voicePackageNameMap; //!<语音识别结果与包条目信息之间的映射关系。本设备独有的
    //            voiceRecognizeResultString, packageName, activityName, recordSoundFilePath, iconType, iconTitle

    Boolean result=false; //结果，是否成功。

    //            String subject=(String)(params[0]); //获取识别结果文字内容。
    HashMap<String, Integer> voicePackageNameMap=(HashMap<String, Integer>)(params[0]); // 获取映射对象。 package name mapped to score.

    // VoicePackageMapMessage translateRequestMessage= new VoicePackageMapMessage(); // 创建一个消息对象。

    for(String currentVoiceRecognizeResult: voicePackageNameMap.keySet()) // 一个个地保存。
    {
      Integer coll = voicePackageNameMap.get(currentVoiceRecognizeResult); // Score.

      VoicePackageMapItemMessage translateRequestMessageBuilder= new VoicePackageMapItemMessage(); // Create a map item.

      translateRequestMessageBuilder.setPackageName(currentVoiceRecognizeResult); // 设置 package name.
      translateRequestMessageBuilder.setScore(coll); // 设置 score.

      Set<String> displaynameSet=new HashSet<>(); // 显示名字集合。

      // translateRequestMessage.addMap(translateRequestMessageBuilder); //添加映射关系。
    } //for(String currentVoiceRecognizeResult: voicePackageNameMap.keySet()) //一个个地保存。

    Log.d(TAG,"1091, saveVoicePackageNameMap, answer value: "); //Debug.

    boolean addPhotoFile=false; //Whether to add photo file

    Log.d(TAG,"1129, saveVoicePackageNameMap, answer value: "); //Debug.

    // CBORObject cborObject= CBORObject.FromObject(translateRequestMessage); //创建对象

    // byte[] array=cborObject.EncodeToBytes();

    
    File photoFile=findVoicePackageMapFile(); //寻找语音识别与软件包映射文件。

    updateVoicePackageNameMapVersion(); //更新语音命中应用映射数据文件的版本

    return result;
  }

    /**
     * 更新语音命中应用映射数据文件的版本
     */
    private void updateVoicePackageNameMapVersion()
    {
      // PreferenceManagerUtil.setVoicePackageNameMapVersion(BuildConfig.VERSION_CODE); //设置语音命中数据文件的版本号
    } //private void updateVoicePackageNameMapVersion()

    /**
     * 寻找语音识别与软件包映射文件。
     * @return 语音识别与软件包映射文件。
     */
    private  File findVoicePackageMapFile()
    {
      File result=null;

      Context context= HxLauncherApplication.getAppContext(); //获取上下文

      File filesDir=context.getFilesDir();

      Log.d(TAG, "1459, findRandomPhotoFile, files dir: "+ filesDir); //Debug.

      if (filesDir==null) //该目录不存在。
      {
      } //if (filesDir==null) //该目录不存在。
      else //该目录存在。
      {
        result=new File(filesDir.getAbsolutePath()+"/voicePackageNameMap.proto"); //指定文件名。

        Log.d(TAG, "1469, findRandomPhotoFile, files exists: "+ result.exists() + ", size: " + result.length()); //Debug.

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
     * 报告结果。
     * @param result 结果。是否成功。
     */
    @Override
    protected void onPostExecute(Boolean result)
    {
    } //protected void onPostExecute(Boolean result)
  }
