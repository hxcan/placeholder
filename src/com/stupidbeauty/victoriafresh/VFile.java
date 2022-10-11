package com.stupidbeauty.victoriafresh;

import android.content.Context;
import android.util.Log;
import android.media.MediaDataSource;
import com.google.gson.Gson;
import com.upokecenter.cbor.CBORObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 虚拟文件。
 * @author root 蔡火胜。
 */
public class VFile
{
  private String fileName; //!<文件名。
  private Context context; //!< 上下文。 Optional. May deal with totaaly external files.
  private File externalDataFile; //!< External data file.
  private final CBORObject vfsFileMessage; //!<此虚拟文件对应的虚拟文件消息对象。
  private static final String TAG="VFile"; //!<输出调试信息时使用的标记。
  private BufferedInputStream ins = null; //!< buffered 输入流。

  /**
  * 读取文件全部内容。
  * @return 文件全部内容，以字符串的形式返回。
  */
  public String getFileTextContent()
  {
    byte[] fileContent=readFileContent(vfsFileMessage); //读取文件内容。
    String result=null; //结果。

    if (fileContent!=null) //不是空指针。
    {
      result=new String(fileContent); //转换成字符串。
    } //if (fileContent!=null) //不是空指针。

    return result;
  } //public String getFileTextContent()

    /**
     * 读取文件全部内容。
     * @return 文件全部内容，以字节数组的形式返回。
     */
    public byte[] getFileContent()
    {
      byte[] fileContent=readFileContent(vfsFileMessage); //读取文件内容。

      return fileContent;
    } //public String getFileTextContent()
    
    /**
    * 获取起始位置。
    */
    public int getStartOffset()
    {
      int result=0;
      
      if (vfsFileMessage!=null) //有对应的消息对象。
      {
        int indexStart=vfsFileMessage.get("file_start_index").AsInt32(); //获取起始位置的下标。
        
        result=indexStart;
      }
      
      return result;
    } // public int getStartOffset()
    
    /**
    * 获取长度。
    */ 
    public int getLength()
    {
      int result=0;
      if (vfsFileMessage!=null) //有对应的消息对象。
      {
        int fileLength=vfsFileMessage.get("file_length").AsInt32(); //获取文件长度。
        
        result = fileLength;
      }
      
      return result;
    } // public int getLength()

    /**
     * 复制文件内容，并且最多复制这么长。
     * @param vfsFileMessage 文件内容消息对象。
     * @param copiedLength 复制的文件内容起始偏移位置。
     * @param MaxCopyOneTimeFileLength 最多复制的内容长度。
     * @return 复制得到的字节数组。
     */
    private byte[] readFileContent(CBORObject vfsFileMessage, int copiedLength , int MaxCopyOneTimeFileLength)
    {
      byte[] result=null; //结果。

      if (vfsFileMessage!=null) //有对应的消息对象。
      {
        int indexStart=vfsFileMessage.get("file_start_index").AsInt32(); //获取起始位置的下标。

        int fileLength=vfsFileMessage.get("file_length").AsInt32(); //获取文件长度。

//         InputStream rawins = context.getResources().openRawResource( victoriaFreshDataFileId); //打开输入流。
//         BufferedInputStream ins = new BufferedInputStream( rawins); //打开输入流。

        //跳过前面不需要的字节：
        int at=indexStart; //要跳过的字节数。

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();

        byte buf[]=new byte[65536];
        int len;

        try
        {
          //跳过整个VFS中这个文件之前的内容：
          while(at>0) //还没完全跳过。
          {
            long amt= ins.skip(at); //做一次跳过动作。

            at-=amt; //记录剩余要跳过的字节数。
          } //while(at>0) //还没完全跳过。

          //跳过指定的起始位置之前的内容。
          at=copiedLength; //获取要跳过的本文件内容的长度。
          while(at>0) //还没完全跳过。
          {
            long amt= ins.skip(at); //做一次跳过动作。

            at-=amt; //记录剩余要跳过的字节数。
          } //while(at>0) //还没完全跳过。

          //下面是要读取指定长度的数据。
          int readedFileLength=0; //已经读取的文件内容长度。
          int tailLength=fileLength-copiedLength; //计算出尾部的剩余文件长度。
          int thisTimeMaxReadLength=Math.min(tailLength, MaxCopyOneTimeFileLength); //此次的最大读取长度。

          while (readedFileLength<thisTimeMaxReadLength) //还没读取到指定长度的数据。
          {
            int remainLength=thisTimeMaxReadLength-readedFileLength; //还有这么多长度要读取。

            int thisTimeReadLength=Math.min(remainLength, buf.length); //这一轮要读取的字节数。

            len=ins.read(buf,0,thisTimeReadLength); //继续读取内容。

            readedFileLength+=len; //记录已经读取的字节数。

            outputStream.write(buf,0,len); //写入到输入流中。
          } //while ((len=ins.read(buf)) != -1) //还没读取到指定长度的数据。

          outputStream.close();
          ins.reset();

          result=outputStream.toByteArray(); //转换成字节数组。
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      } //if (vfsFileMessage!=null) //有对应的消息对象。

      return  result;
    } //private byte[] readFileContent(FileMessageContainer.FileMessage vfsFileMessage, int copiedLength , int MaxCopyOneTimeFileLength)

    /**
     * 读取整个文件的内容。
     * @param vfsFileMessage 文件消息对象。
     * @return 读取的文件内容字节数组。
     */
//    private byte[] readFileContent(FileMessageContainer.FileMessage vfsFileMessage)
    private byte[] readFileContent(CBORObject vfsFileMessage)
    {
      int startAt0=0; //起始位置，从0字节开始读取。

      byte[] result=null; //结果。

      if (vfsFileMessage!=null) //有对应的消息对象。
      {
        int fileLength=vfsFileMessage.get("file_length").AsInt32(); //获取文件长度。

        result=readFileContent(vfsFileMessage, startAt0, fileLength); //结果。
      } //if (vfsFileMessage!=null) //有对应的消息对象。

      return  result;
    } //private byte[] readFileContent(FileMessageContainer.FileMessage vfsFileMessage)
    
    /**
    * 向字节数组中复制。
    */
    public int copyToByteArray(int size, long position, byte[] buffer, int offset)
    {
      Log.d(TAG, "copyToByteArray, size: " + size + ", position: " + position + ", offset: " + offset); // Debug.
      
      int actualSize=size; // 实际尺寸。
      
      byte[] wholeFile=readFileContent(vfsFileMessage); // 全部读取。
      
      int positionInt=(int)(position); // 转换成整数型 。
      
      actualSize=Math.min(size, (wholeFile.length-positionInt)); // 限制尺寸。
      
      System.arraycopy(wholeFile, positionInt, buffer, offset, actualSize); // 复制字节数组。
      
      return actualSize;
    } // public int copyToByteArray(int size, long position, byte[] buffer, int offset)

    /**
     * 将文件复制到当前应用的私有目录中去。
     * @param targetFileName 相对于私有目录的路径。
     */
    public void copy(String targetFileName)
    {
      String absolutePath=context.getFilesDir().getAbsolutePath()+"/"+targetFileName; //绝对路径。

      copyToAbsolutePath(absolutePath); //复制到绝对路径。
    } //private void copy(String targetFileName)

    /**
     * 将文件复制到绝对路径中去。
     * @param targetFileName 绝对路径的目标文件名。
     */
    public void copyToAbsolutePath(String targetFileName)
    {
      File targetFile=new File(targetFileName); //目标文件。

        try //尝试复制，并且捕获可能的异常。
        {
          boolean fileCreateResult=targetFile.createNewFile(); //创建新文件。

          if (fileCreateResult) //创建成功。
          {
            OutputStream targetOutStream=new FileOutputStream(targetFile); //创建输出流。

            byte[] fileContent; //读取到的文件内容。

            int fileLength=vfsFileMessage.get("file_length").AsInt32(); //获取文件长度。

            int copiedLength=0; //已经复制的长度。
            int MaxCopyOneTimeFileLength = 28 * 1024 * 1024; //28M，最大一次性复制的文件长度。

            if (fileLength>= MaxCopyOneTimeFileLength) //超过一次性复制的最大长度。
            {
              while (copiedLength<fileLength) //未复制完。
              {
                fileContent=readFileContent(vfsFileMessage, copiedLength , MaxCopyOneTimeFileLength); //复制文件内容，并且最多复制这么长。

                targetOutStream.write(fileContent); //输出内容。

                copiedLength+=fileContent.length; //记录已经复制的总长度。
              } //while (copiedLength<fileLength) //未复制完。
            } //if (fileLength>=MaxCopyOneTimeFileLength) //超过一次性复制的最大长度。
            else //未超过一次性复制的最大长度。
            {
              fileContent=readFileContent(vfsFileMessage); //读取文件内容。

              targetOutStream.write(fileContent); //输出内容。
            } //else //未超过一次性复制的最大长度。

            targetOutStream.close(); //关闭输出文件。
          } //if (fileCreateResult) //创建成功。
        } //try //尝试复制，并且捕获可能的异常。
        //catch (FileNotFoundException e) //文件未找到。
        catch (IOException e) //输入输出错误。
        {
          e.printStackTrace(); //报告错误。
        } //catch (IOException e) //输入输出错误。
    } //private void copy(String targetFileName)

    private int victoriaFreshDataFileId=0; //!<VictoriaFreSh数据文件编号。

//    private VFile(Context context, FileMessageContainer.FileMessage vfsFileMessage)
    private VFile(Context context, CBORObject vfsFileMessage)
    {
      this.context = context;
      this.vfsFileMessage = vfsFileMessage;

      victoriaFreshDataFileId=context.getResources().getIdentifier("victoriafreshdata", "raw", context.getPackageName()); //获取数据文件编号。
      victoriaFreshIndexFileId=context.getResources().getIdentifier("victoriafresh", "raw", context.getPackageName()); //获取索引文件编号。
      
      InputStream rawins = context.getResources().openRawResource( victoriaFreshDataFileId); //打开输入流。
      ins = new BufferedInputStream( rawins); //打开输入流。
      try
      {
      ins.mark(ins.available());
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }
    }

    public VFile(CBORObject vfsFileMessage, File dataFile)
    {
      this.vfsFileMessage = vfsFileMessage;
      this.externalDataFile= dataFile;

      try
      {
      FileInputStream rawins = new FileInputStream( dataFile); //打开输入流。
      ins = new BufferedInputStream( rawins); //打开输入流。
      ins.mark(ins.available());
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }
//       catch(FileNotFoundException e)
//       {
//         e.printStackTrace();
//       }
}

    /**
     * 是否存在。
     * @return 此文件是否存在。
     */
    public boolean exists()
    {
        boolean result=false; //结果。

        if (vfsFileMessage!=null) //确实存在该文件。
        {
            result=true; //文件存在。
        } //if (targetFileMessage!=null) //确实存在该文件。


        return result;
    } //private boolean exists()
    
    /**
    * 获取权限数字
    */
    public int getPermission() 
    {
//           permissionNumber=packagedFile['permission'] #获取权限数字
      return vfsFileMessage.get("permission").AsInt32();
    } // public int getPermission()

    public long getTimestamp()
    {
      long result=-1;
      
      CBORObject timestampObject=vfsFileMessage.get("timestamp");
      
      long seconds=timestampObject.get("seconds").AsInt32();
      long milleseconds=(long)(timestampObject.get("nanos").AsInt32()/ 1000.0 / 1000.0); // Get millescones.
      
      result=seconds*1000+milleseconds;
      
      return result;
    }
    
    /**
    * 找到相同文件的编号。
    */
    public String getSameFileId() 
    {
//           sameFileId=packagedFile['same_file_id'] # 找到相同文件的编号。
      return vfsFileMessage.get("same_file_id").AsString();
    } // public String getSameFileId()
    
    /**
    * 获取文件编号。
    */
    public int getId() 
    {
//         fileId=packagedFile['id'] # 获取文件编号。
      return vfsFileMessage.get("id").AsInt32();
    } // public String getId()
    
    /**
     * 获取文件名。
     * @return 文件名。
     */
    public String getFileName()
    {
//        return vfsFileMessage.getName();
        return vfsFileMessage.get("name").AsString();
    } //public String getFileName()
    
    /**
    * 是否是重复文件。
    */
    public boolean isDuplicate() 
    {
//         is_duplicate=packagedFile['is_duplicate'] # 是否是重复文件。
      CBORObject isDuplicateObject=vfsFileMessage.get("is_duplicate");
      boolean result=false;
      
      if (isDuplicateObject!=null)
      {
        result=isDuplicateObject.AsBoolean();
      }
      
      return result;
    } // public boolean isDuplicate()
    
    /**
    * 是符号链接，则创建符号链接
    */
    public boolean isSymlink() 
    {
//         elsif packagedFile['is_symlink'] #是符号链接，则创建符号链接
      CBORObject isDuplicateObject=vfsFileMessage.get("is_symlink");
      boolean result=false;
      
      if (isDuplicateObject!=null)
      {
        result=isDuplicateObject.AsBoolean();
      }
      
      return result;
    } // public boolean isSymlink()

    /**
     * 获取信息，是否是文件。
     * @return 本节点是否是文件。
     */
    public boolean isFile()
    {
//        return vfsFileMessage.getIsFile(); //是否是文件。
//         return vfsFileMessage.get("is_file").AsBoolean(); //是否是文件。
      CBORObject isDuplicateObject=vfsFileMessage.get("is_file");
      boolean result=false;
      
      if (isDuplicateObject!=null)
      {
        result=isDuplicateObject.AsBoolean();
      }
      
      return result;

    } //public boolean isFile()

    /**
     * 找到目标文件消息对象。
     * @param videoStreamMessage 要从中寻找子文件的文件对象。
     * @param relativeName 相对文件名。
     * @return 寻找到的结果。
     */
    private CBORObject findRelative(CBORObject videoStreamMessage,String relativeName)
    {
        CBORObject result=null; //结果。

        String[] pathSegments=relativeName.split("/"); //以／分割。

        String subFileName=pathSegments[0]; //直接子文件名。

        boolean foundSubFile=false; //是否找到了对应的子文件。
        CBORObject subFile=null; //找到的直接子文件。

        if (videoStreamMessage.get("is_file").AsBoolean()) //本身是文件。
        {
            if (videoStreamMessage.get("name").AsString().equals(subFileName)) //正是这个文件。
            {
                foundSubFile=true; //找到了子文件。
                subFile=videoStreamMessage; //子文件就是自己。
            } //if (videoStreamMessage.getName().equals(subFileName)) //正是这个文件。
        } //if (videoStreamMessage.getIsFile()) //本身是文件。
        else //本身是目录。
        {
            Collection<CBORObject> subFilesList=videoStreamMessage.get("sub_files").getValues();

            for (CBORObject currentSubFile: subFilesList) //一个个子文件地比较其文件名。
            {
                if (currentSubFile.get("name").AsString().equals(subFileName)) //正是这个文件。
                {
                    foundSubFile=true; //找到了子文件。
                    subFile=currentSubFile; //记录。

                    break; //跳出。
                } //if (currentSubFile.getName().equals(subFileName)) //正是这个文件。
            } //for (FileMessageContainer.FileMessage currentSubFile:videoStreamMessage.getSubFilesList()) //一个个子文件地比较其
        } //else //本身是目录。

        if (foundSubFile) //找到了子文件。
        {
            if (relativeName.contains("/")) //是一个多层的路径，还要继续找。
            {
                int firstSlash=relativeName.indexOf("/"); //取出第一个／的下标。
                String pathTail=relativeName.substring(firstSlash+1); //取后面的相对路径。

                if (pathTail.isEmpty()) //后续路径为空白，也不用再找下去了。
                {
                    result=subFile; //就是这个文件。
                } //if (pathTail.isEmpty()) //后续路径为空白，也不用再找下去了。
                else //不为空白，继续找。
                {
                    result=findRelative(subFile,pathTail); //让子文件继续按照后面一砣相对文件名来寻找。
                } //else //不为空白，继续找。
            } //if (relativeName.contains("/")) //是一个多层的路径，还要继续找。
            else //直接就是子文件名。
            {
                result=subFile; //就是这个文件。
            } //else //直接就是子文件名。
        } //if (foundSubFile) //找到了子文件。

        return  result;
    } //private FileMessageContainer.FileMessage findRelative(FileMessageContainer.FileMessage videoStreamMessage,String 

    /**
     * 获取子文件列表。
     * @return 子文件列表。
     */
    public List<VFile> entryList()
    {
      ArrayList<VFile> result=new ArrayList<>(); //结果。

      Collection<CBORObject> subFilesList=vfsFileMessage.get("sub_files").getValues();

      for (CBORObject currentSubFile: subFilesList) //一个个子文件地比较其文件名。
      {
        VFile currentFile=null; //创建一个虚拟文件。
        
        if (context!=null)
        {
        currentFile=new VFile(context,currentSubFile); //创建一个虚拟文件。
        }
          else
          {
        currentFile=new VFile(currentSubFile, externalDataFile); //创建一个虚拟文件。
          }

        result.add(currentFile); //加入结果中。
      } //for (FileMessageContainer.FileMessage currentSubFile:videoStreamMessage.getSubFilesList()) //一个个子文件地比较其文件名。

      return  result;
    } //public List<VFile> entryList()

    /**
     * 获取子文件列表。
     * @return 子文件列表。
     */
    public String entryListJson()
    {
      EntryListJsonMessage entryListJsonMessage=new EntryListJsonMessage();

      List<VFile> result=entryList(); //结果。

      for (VFile currentFile: result) //一个个子文件地处理。
      {
        entryListJsonMessage.addEntry(currentFile.getFileName()); //加入结果中。
      } //for (FileMessageContainer.FileMessage currentSubFile:videoStreamMessage.getSubFilesList()) //一个个子文件地比较其文件名。

      Gson gson=new Gson();
      String resultJson=gson.toJson(entryListJsonMessage);

      return  resultJson;
    } //public List<VFile> entryList()

    /**
     * 构造函数。
     * @param context 上下文。
     * @param fileName 虚拟文件名。
     */
    public VFile(Context context, String fileName)
    {
      this.fileName = fileName;
      this.context=context; //记录上下文。

      victoriaFreshDataFileId=context.getResources().getIdentifier("victoriafreshdata", "raw", context.getPackageName()); //获取数据文件编号。
      victoriaFreshIndexFileId=context.getResources().getIdentifier("victoriafresh", "raw", context.getPackageName()); //获取索引文件编号。
      
      InputStream rawins = context.getResources().openRawResource( victoriaFreshDataFileId); //打开输入流。
      ins = new BufferedInputStream( rawins); //打开输入流。
      try
      {
      ins.mark(ins.available());
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }


      vfsFileMessage=loadVfsFile(); //载入对应的虚拟文件。
    } //public VFile(Context context, String fileName)
    
    /**
    * 获取媒体数据源。
    */
    public MediaDataSource getMediaDataSource()
    {
      MediaDataSource result=new VictoriaMediaDataSource(this);
      
      return result;
    } // public MediaDataSource getMediaDataSource()

    /**
     * 构造函数。
     * @param context 上下文。
     * @param fileName 虚拟文件名。
     * @param victoriaFreshDataFileIdToUse 用于容纳虚拟文件实际内容的打包资源文件的资源编号。
     * @param victoriaFreshIndexFileIdToUse 虚拟文件索引文件的资源编号。
     */
    public VFile(Context context, int victoriaFreshIndexFileIdToUse,int victoriaFreshDataFileIdToUse, String fileName)
    {
      this.fileName = fileName;
      this.context=context; //记录上下文。

      victoriaFreshDataFileId=victoriaFreshDataFileIdToUse; //获取数据文件编号。
      victoriaFreshIndexFileId=victoriaFreshIndexFileIdToUse; //获取索引文件编号。

      InputStream rawins = context.getResources().openRawResource( victoriaFreshDataFileId); //打开输入流。
      ins = new BufferedInputStream( rawins); //打开输入流。
      try
      {
      ins.mark(ins.available());
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }

      vfsFileMessage=loadVfsFile(); //载入对应的虚拟文件。
    } //public VFile(Context context, String fileName)

    private int victoriaFreshIndexFileId=0; //!<VictoriaFreSh索引文件编号。

    /**
     * 载入对应的虚拟文件。
     * @return 载入之后得到的消息对象
     */
    private CBORObject loadVfsFile()
    {
      CBORObject result=null; //结果。

      InputStream ins = context.getResources().openRawResource( victoriaFreshIndexFileId); //打开输入流。

      ByteArrayOutputStream outputStream=new ByteArrayOutputStream();

      byte buf[]=new byte[65536];
      int len;

      try
      {
        while ((len=ins.read(buf)) != -1)
        {
          outputStream.write(buf,0,len);
        }

        outputStream.close();
        ins.close();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

      //解析消息：
      byte[] payloadData=outputStream.toByteArray(); //获取负载字节数组。

      String relativeName=fileName.substring(2); //去除开头两个。

      CBORObject videoStreamMessage= CBORObject.DecodeFromBytes(payloadData); //解析消息。

      result=findRelative(videoStreamMessage,relativeName); //找到目标文件消息对象。

      return  result;
    } //private FileMessageContainer.FileMessage loadVfsFile(String fileName)
} //public class VFile
