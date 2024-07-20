package com.stupidbeauty.placeholder;

import com.stupidbeauty.codeposition.CodePosition;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.BufferedReader;
import android.util.Log;
import android.media.MediaDataSource;
import com.google.gson.Gson;
import com.upokecenter.cbor.CBORObject;
import java.io.ByteArrayOutputStream;
import android.media.MediaDataSource;
import com.google.gson.Gson;
import com.upokecenter.cbor.CBORObject;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class VictoriaMediaDataSource extends MediaDataSource
{
  private VFile vfile=null; //!< 虚拟文件对象。
  private static final String TAG = "VictoriaMediaDataSource"; //!< 输出调试信息时使用的标记。
  
  public VictoriaMediaDataSource(VFile file)
  {
    this.vfile=file;
  } // public VictoriaMediaDataSource(VFile file)
  
  public long getSize ()
  {
    long actualSize = vfile.getLength(); // Get the size.
  
    Log.d(TAG, CodePosition.newInstance().toString() + ", size: " + actualSize); // Debug.

    return actualSize;
  } // public abstract long getSize ()
  
  public void close ()
  {
  } // public abstract void close ()

  @Override
  public int readAt ( long position, byte[] buffer, int offset, int size )
  {
    int result=0; // 读取个数。结果。

    Log.d(TAG, CodePosition.newInstance().toString()+ ", position: "+ position + ", offset: " + offset + ", size: " + size  + ", buffer length: " + buffer.length); // Debug.
    
    result = vfile.copyToByteArray(size, position, buffer, offset); // 向字节数组中复制。
    
    if (result < 0) // Reached the end.
    {
      result = -1; // normalize to -1.
    } // if (result < 0) // Reached the end.
                
    return result;
  } // public abstract int readAt (long position, 
}
