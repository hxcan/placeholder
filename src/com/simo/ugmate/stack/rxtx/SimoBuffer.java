package com.simo.ugmate.stack.rxtx;

public class SimoBuffer {

public	  byte[]  array =null;

public    int readsize=0;

public int arraylen = 0;

public  SimoBuffer (int len){
	    arraylen = len;
		array =new byte[len];
	}
/**
 * 
 * @param size 要删除的字节数量
 */
public void delete(int size){  
//	   Log.print("time1"+System.currentTimeMillis());
       System.arraycopy(array, size, array, 0, arraylen -size);
//       Log.print("time2"+System.currentTimeMillis());
       readsize-=size;
   }
  /**
   * 
   * @param data 将数据写入缓存
   */
   public  void  write(byte[] data){
	   
	   try {
		
		   synchronized (array) {
			   if(array.length - readsize >data.length){
					  System.arraycopy(data, 0, array, readsize, data.length);
					  readsize+=data.length;
					  
				   }else {   
					   return;
				   }
	} 
	}catch (Exception e) {
	return ;
	}
	   
   }
   
  
   
   
}
