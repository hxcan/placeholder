package com.stupidbeauty.victoriafresh;

import android.media.MediaDataSource;
import com.google.gson.Gson;
import com.upokecenter.cbor.CBORObject;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class VictoriaMediaDataSource extends MediaDataSource
{
  private VFile vfile=null; //!< 虚拟文件对象。
  
  public VictoriaMediaDataSource(VFile file)
  {
    this.vfile=file;
  } // public VictoriaMediaDataSource(VFile file)
  
  public long getSize ()
  {
    return vfile.getLength();
  } // public abstract long getSize ()
  
  public void close ()
  {
  } // public abstract void close ()


  public int readAt 
  (
    long position, 
    byte[] buffer, 
    int offset, 
    int size
  )
  {
    int result=0; // 读取个数。结果。
                
    result=vfile.copyToByteArray(size, position, buffer, offset); // 向字节数组中复制。
                
    return result;
  } // public abstract int readAt (long position, 
//                 byte[] buffer, 
//                 int offset, 
//                 int size)
}
