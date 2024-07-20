package com.stupidbeauty.hxlauncher;

import java.util.ArrayList;
import java.util.HashMap;
import com.stupidbeauty.hxlauncher.asynctask.VoicePackageNameMapSaveTask;
import com.stupidbeauty.farmingbookapp.PreferenceManagerUtil;
import com.stupidbeauty.hxlauncher.AndroidApplicationMessage;
import java.util.List;

/**
* Protobuf type {@code com.stupidbeauty.hxlauncher.VoicePackageMapMessage}
*/
public  final class VoicePackageMapMessage
{
  private static final long serialVersionUID = 0L;

  public VoicePackageMapMessage() 
  {
    map_ = new ArrayList<>();
  }
  
  public void addMap(VoicePackageMapItemMessage item)
  {
    map_.add(item);
  } // public void addMap(VoicePackageMapItemMessage item)

  public static final int MAP_FIELD_NUMBER = 1;
  private java.util.List<com.stupidbeauty.hxlauncher.VoicePackageMapItemMessage> map_;
  
  public List<VoicePackageMapItemMessage> getMap_()
  {
    return map_;
  }
  
  /**
  * <pre>
  *映射数据体。
  * </pre>
  *
  * <code>repeated .com.stupidbeauty.hxlauncher.VoicePackageMapItemMessage map = 1;</code>
  */
  public int getMapCount() 
  {
    return map_.size();
  }
  
  private byte memoizedIsInitialized = -1;
}
