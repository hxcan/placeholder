package com.stupidbeauty.victoriafresh;

import com.stupidbeauty.codeposition.CodePosition;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.BufferedReader;
// import com.stupidbeauty.shutdownat2100.helper.ShutDownAt2100Manager;
import java.util.List;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import android.content.Context;
import android.util.Log;
import android.media.MediaDataSource;
import com.google.gson.Gson;
import com.upokecenter.cbor.CBORObject;
import java.util.HashMap;
import android.view.View;
import android.os.AsyncTask;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import java.io.File;
import java.io.IOException;
import java.io.File;
import android.annotation.SuppressLint;
import com.upokecenter.cbor.CBORException;
import android.os.Environment;
import android.util.Log;
import com.upokecenter.cbor.CBORObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import android.annotation.SuppressLint;
import com.upokecenter.cbor.CBORException;

public class VictoriaFresh
{
  private File externalDataFile=null; // 打开文件
  private HashMap<Integer, String> fileIdPathMap=new HashMap(); //!< file id to file path map.
  private static final String TAG="VictoriaFresh"; //!< 输出调试信息时使用的标记。

//       def getTimeObject(packagedFile) #构造时间戳对象
//     Chen xin
      /**
      * 构造时间戳对象
      */
      private long getTimeObject(VFile packagedFile)
      {
//         #         seconds=packagedFile.timestamp.seconds #获取秒数
//         seconds=packagedFile['timestamp']['seconds'] #获取秒数
//         seconds=packagedFile['timestamp']['seconds'] #获取秒数
        
//         #         microSeconds=packagedFile.timestamp.nanos/ 1000.0 #获取毫秒数
//         microSeconds=packagedFile['timestamp']['nanos'] / 1000.0 #获取毫秒数
        
//         timeObject=Time.at(seconds, microSeconds) #构造时间对象
        long timeObject=packagedFile.getTimestamp();
        
        return timeObject;
      }
        
//     end #getTimeObject(packagedFile) #构造时间戳对象

  /**
  * 释放各个文件
  */
  public void releaseFilesExternalDataFile(byte[] victoriaFreshPackagedFileString, String externalDataFileName, Context baseApplication)
  {
    CBORObject packagedFile=CBORObject.DecodeFromBytes(victoriaFreshPackagedFileString); // 解码
        
    externalDataFile=new File(externalDataFileName); // 打开文件
    
    VFile packagedFileObject=new VFile(packagedFile,  externalDataFile);
    Log.d(TAG, CodePosition.newInstance().toString()+ ", created v file: "+ packagedFileObject + ", this: " + this); // Debug.

    File downloadFolder = baseApplication.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);

    String dataFileNamePrefix =downloadFolder.getPath();
      
    releaseFileExternalDataFile(dataFileNamePrefix, packagedFileObject); // 释放一个文件 
    
    Log.d(TAG, CodePosition.newInstance().toString()+ ", releasing v file: "+ packagedFileObject + ", this: " + this); // Debug.
    packagedFileObject=null; // release memory.
  } // public void releaseFilesExternalDataFile(byte[] replyByteArray, String victoriaFreshDataFile)
  
  private void  writeFileExternalDataFile(String pathPrefix, VFile packagedFile) //写入文件
  {
    long timeObject=getTimeObject(packagedFile); // 构造时间戳对象
        
    boolean is_duplicate=packagedFile.isDuplicate(); // 是否是重复文件。

    int fileId=packagedFile.getId(); // 获取文件编号。

    byte[] victoriaFreshData= null; // 读取原始相同文件的内容。

    if (is_duplicate) // 是重复文件。
    {
      String sameFileId=packagedFile.getSameFileId(); // 找到相同文件的编号。
          
      String sameFilePath=fileIdPathMap.get(sameFileId); // 获取原始相同文件的路径。
          
      //           Chen xin
      //           victoriaFreshData=File.read(sameFilePath) # 读取原始相同文件的内容。
      File sameFilePathFile=new File(sameFilePath);
      try
      {
        victoriaFreshData= FileUtils.readFileToByteArray(sameFilePathFile); // 读取原始相同文件的内容。
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }
    }
    else // 不是重复文件。
    {
      //         chen xin
      //           @externalDataFile.seek(packagedFile['file_start_index']) #定位到起始位置
      //           
      //           victoriaFreshData=@externalDataFile.read(packagedFile['file_length']) #读取内容

//           @externalDataFile.seek(packagedFile['file_start_index']) #定位到起始位置
          
          victoriaFreshData=packagedFile.getFileContent(); //读取内容
        }
//         end # if is_duplicate # 是重复文件。
        
//         pathToMake=pathPrefix + '/' + packagedFile['name'] #构造文件名
        String pathToMake=pathPrefix + '/' + packagedFile.getFileName(); //构造文件名
        
            Log.d(TAG, "writeFileExternalDataFile, data file name: " + pathToMake); // Debug.

        
//         @fileIdPathMap[fileId]=pathToMake # 记录文件编号与路径之间的映射。
        fileIdPathMap.put(fileId, pathToMake); // 记录文件编号与路径之间的映射。

//         begin

//           victoriaFreshDataFile=File.new(pathToMake , "wb", packagedFile['permission']) #数据文件。
//           victoriaFreshDataFile.syswrite(victoriaFreshData) #写入文件。
//           victoriaFreshDataFile.close #关闭文件。

          File victoriaFreshDataFile=new File(pathToMake ); //数据文件。

//           victoriaFreshDataFile.syswrite(victoriaFreshData) #写入文件。
try 
{
        FileUtils.writeByteArrayToFile(victoriaFreshDataFile, victoriaFreshData);
}
catch(IOException e)
{
  e.printStackTrace();
}
//           victoriaFreshDataFile.close #关闭文件。

//           FileUtils.touch pathToMake, :mtime => timeObject #设置修改时间
//         Chen xin
            victoriaFreshDataFile.setLastModified(timeObject);

//           FileUtils.touch pathToMake, :mtime => timeObject #设置修改时间

//           permissionNumber=packagedFile['permission'] #获取权限数字
          int permissionNumber=packagedFile.getPermission(); //获取权限数字

//           if (permissionNumber.nil?) #不带权限字段
          if (permissionNumber==-1) //不带权限字段
          {
          }
//           elsif #带权限字段
          else //带权限字段
          {
//           Chen xin
//             FilePath hudsonFilePath=new FilePath(victoriaFreshDataFile);
//             try
//             {
//             hudsonFilePath.chmod(permissionNumber);
//             }
//             catch(IOException e)
//             {
//               e.printStackTrace();
//             }
//             catch (InterruptedException e)
//             {
//               e.printStackTrace();
//             }
//             File.chmod(permissionNumber, pathToMake) #设置权限
          }
//           end #if (permissionNumber.nil?) #不带权限字段
//         rescue Errno::ENOENT # File not exist
//           puts "Rescued by Errno::ENOENT statement. #{pathToMake}" #报告错误
//         rescue Errno::EACCES # File permission error
//             puts "Rescued by Errno::EACCES statement. #{pathToMake}" #报告错误
//         end
      }
//     end #def writeFileExternalDataFile(pathPrefix, packagedFile) #写入文件

    /**
    * 创建符号链接
    */
//     def makeSymlinkExternalDataFile(pathPrefix, packagedFile)
    private void makeSymlinkExternalDataFile(String pathPrefix, VFile packagedFile)
    {
//         @externalDataFile.seek(packagedFile['file_start_index']) #定位到起始位置
        
//         victoriaFreshData=@externalDataFile.read(packagedFile['file_length']) #读取内容
// #         victoriaFreshData=contentString[ packagedFile['file_start_index'], packagedFile['file_length'] ] #获取内容
        String victoriaFreshData=packagedFile.getFileTextContent(); // 读取内容
        
//         pathToMake=pathPrefix + '/' + packagedFile['name'] #构造文件名
        String pathToMake=pathPrefix + '/' + packagedFile.getFileName(); // 构造文件名
        
//         puts("data: #{victoriaFreshData}, path: #{pathToMake}") #Debug

//         begin #创建符号链接
//         Chen xin
//             FileUtils.symlink(victoriaFreshData, pathToMake, force: true) #创建符号链接
        Path pathToMakePath=Paths.get(pathPrefix, packagedFile.getFileName());
        Path victoriaFreshDataPath=Paths.get(victoriaFreshData);
        try
        {
            Files.createSymbolicLink(pathToMakePath, victoriaFreshDataPath); //创建符号链接
        }
        catch(IOException e)
        {
          e.printStackTrace();
        }
//         rescue Errno::EACCES => e #权限受限
//             puts "Rescued by Errno::EACCES statement. #{pathToMake}" #报告错误
//         end #begin #创建符号链接
        
//         permissionNumber=packagedFile['permission'] #获取权限数字
        int permissionNumber=packagedFile.getPermission(); // 获取权限数字
        
//         if (permissionNumber.nil?) #不带权限字段
        if (permissionNumber==-1) // 不带权限字段
        {
        }
//         elsif #带权限字段
        else //带权限字段
        {
//             #             File.chmod(permissionNumber, pathToMake) #设置权限
//             begin #尝试修改链接本身的权限
//             chen xin.
//                 File.lchmod(permissionNumber, pathToMake) #设置权限

            File pathToMakeFile=new File(pathToMake);
//             FilePath hudsonFilePath=new FilePath(pathToMakeFile);
//             try
//             {
//             hudsonFilePath.chmod(permissionNumber);
//             }
//             catch(IOException e)
//             {
//               e.printStackTrace();
//             }
//             catch (InterruptedException e)
//             {
//               e.printStackTrace();
//             }

//             rescue NotImplementedError #未实现
//                 puts 'File.lchmod not implemented' #Debug
//             rescue Errno::ENOTSUP => e
//                 puts "Rescued by Errno::ENOTSUP statement. #{pathToMake}" #报告错误
//             end #begin #尝试修改链接本身的权限
        }
//         end #if (permissionNumber.nil?) #不带权限字段
    }
//     end #def makeSymlinkExternalDataFile(pathPrefix, packagedFile)

//     def makeDirectory(pathPrefix, packagedFile) #创建目录
    /**
    * 创建目录
    */
    private void makeDirectory(String pathPrefix, VFile packagedFile)
    {
//         timeObject=getTimeObject(packagedFile) #构造时间戳对象
//       chen xin
        long timeObject=getTimeObject(packagedFile); //构造时间戳对象
        
        
//         #         puts 'mkdir' #Debug
//         pathToMake=File.join(pathPrefix, packagedFile['name'])
        String pathToMake=pathPrefix + "/" + packagedFile.getFileName();
        
//         #         puts  pathToMake #Debug.
        
//         if (Dir.exist?(pathToMake)) #目录已经存在
        File pathToMakeFile=new File(pathToMake);
        if (pathToMakeFile.exists()) //目录已经存在
        {
        }
//         else #目录 不存在
        else // 目录 不存在
        {
//             begin
//             Chen xin
            pathToMakeFile.mkdir(); //=> 0
//             rescue Errno::EILSEQ => e # File name invalid
//             puts "Rescued by Errno::EILSEQ statement. #{pathToMake}" # 报告错误
// 
//             end
        }

//         end #if (Dir.exist?(pathToMake)) #目录已经存在

//         begin
//         Chen xin
//             FileUtils.touch pathToMake, :mtime => timeObject # 设置修改时间
                        pathToMakeFile.setLastModified(timeObject);

//         rescue Errno::EILSEQ => e # File name invalid
//             puts "Rescued by Errno::EILSEQ statement. #{pathToMake}" # 报告错误
// 
//         end


//         permissionNumber=packagedFile['permission'] #获取权限数字
        int permissionNumber=packagedFile.getPermission(); //获取权限数字
        
//         if (permissionNumber.nil?) #不带权限字段
        if (permissionNumber==-1) //不带权限字段
        {
        }
//         elsif #带权限字段
        else //带权限字段
        {


//             begin
//           Chen xin
//                 File.chmod(permissionNumber, pathToMake) #设置权限
//             FilePath hudsonFilePath=new FilePath(pathToMakeFile);
//             try 
//             {
//             hudsonFilePath.chmod(permissionNumber);
//             }
//             catch(IOException e)
//             {
//               e.printStackTrace();
//             }
//             catch (InterruptedException e)
//             {
//               e.printStackTrace();
//             }

//             rescue Errno::ENOENT => e # File not exist
//                 puts "Rescued by Errno::ENOENT statement. #{pathToMake}" # 报告错误
// 
//             end


        }
//         end #if (permissionNumber.nil?) #不带权限字段
    }
//     end #makeDirectory(pathPrefix, packagedFile) #创建目录

  
//       def releaseFileExternalDataFile(pathPrefix, packagedFile) #释放一个文件
    /**
    * 释放一个文件
    */
    private void releaseFileExternalDataFile(String pathPrefix, VFile packagedFile) 
    {
      VFile packagedFileObject=(packagedFile);
      
//         if packagedFile['is_file'] #是文件，则直接写入文件
        if (packagedFileObject.isFile()) //是文件，则直接写入文件
        {
//           writeFileExternalDataFile(pathPrefix, packagedFile) #写入文件
          writeFileExternalDataFile(pathPrefix, packagedFileObject); // 写入文件
        }
//         elsif packagedFile['is_symlink'] #是符号链接，则创建符号链接
        else if (packagedFileObject.isSymlink()) //是符号链接，则创建符号链接
        {
//             makeSymlinkExternalDataFile(pathPrefix, packagedFile) #创建符号链接
          makeSymlinkExternalDataFile(pathPrefix, packagedFileObject); // 创建符号链接
        }
//         else #是目录，则创建目录，并递归处理
        else  // 是目录，则创建目录，并递归处理
        {
//           makeDirectory(pathPrefix, packagedFile) #创建目录
          makeDirectory(pathPrefix, packagedFileObject); //创建目录

//           direcotryPathPrefix=pathPrefix  + '/' + packagedFile['name'] #构造针对该目录的路径前缀
          String direcotryPathPrefix=pathPrefix  + '/' + packagedFileObject.getFileName(); // 构造针对该目录的路径前缀

//           subFiles=packagedFile['sub_files'] #获取子文件列表。
          List<VFile> subFiles=packagedFileObject.entryList(); // 获取子文件列表。

          int subFileCounter=0;
          
//           subFiles.each do |currentSubFile| #一个个子文件地释放
//           subFiles.each do |currentSubFile| #一个个子文件地释放
          for(subFileCounter=0; subFileCounter< subFiles.size(); subFileCounter++)
          {
            VFile currentSubFile=subFiles.get(subFileCounter);

//             releaseFileExternalDataFile(direcotryPathPrefix, currentSubFile) #释放子文件
            releaseFileExternalDataFile(direcotryPathPrefix, currentSubFile); // 释放子文件
          }
//           end #subFiles.each do |currentSubFile| #一个个子文件地释放
        }
//         end #if packagedFile.is_file #是文件，则直接写入文件
    }
//     end #def releaseFileExternalDataFile(pathPrefix, packagedFile) #释放一个文件
}
