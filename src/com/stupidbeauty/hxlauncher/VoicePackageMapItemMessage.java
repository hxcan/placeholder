package com.stupidbeauty.hxlauncher;

/**
* Protobuf type {@code com.stupidbeauty.hxlauncher.VoicePackageMapItemMessage}
*/
public  final class VoicePackageMapItemMessage
{
  private static final long serialVersionUID = 0L;
  
  public VoicePackageMapItemMessage() 
  {

    packageName_ = "";

    activityName_ = "";

    iconType_ = 0;
  }

    public static final int VOICERECOGNIZERESULT_FIELD_NUMBER = 1;

    public static final int PACKAGENAME_FIELD_NUMBER = 2;
    private volatile java.lang.Object packageName_;
    /**
     * <pre>
     *对应的包名。
     * </pre>
     *
     * <code>string packageName = 2;</code>
     */
    public java.lang.String getPackageName() {
      java.lang.Object ref = packageName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        packageName_ = s;
        return s;
      }
    }

    public static final int PACKAGEURL_FIELD_NUMBER = 3;

    public static final int ACTIVITYNAME_FIELD_NUMBER = 4;
    private volatile java.lang.Object activityName_;
    /**
     * <pre>
     *活动名字。
     * </pre>
     *
     * <code>string activityName = 4;</code>
     */
    public java.lang.String getActivityName() {
      java.lang.Object ref = activityName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        activityName_ = s;
        return s;
      }
    }

    public static final int SHORTCUTID_FIELD_NUMBER = 5;
//     private volatile java.lang.Object shortcutId_;

    public static final int ICONTYPE_FIELD_NUMBER = 6;

    /**
     * Score.
     */
    public int getScore() 
    {
      return iconType_;
    }
    
    /**
    * increase score
    */
    public void increaseScore() 
    {
      iconType_=iconType_+1;
    } // public void increaseScore()
    
    /**
    * decrease score
    */
    public void decreaseScore() 
    {
      iconType_=iconType_-1; // Decreae.
      
      if (iconType_<=0)
      {
        iconType_=1;
      }
    } // public void decreaseScore()

    private byte memoizedIsInitialized = -1;

    /**
    * <pre>
    *对应的包名。
    * </pre>
    *
    * <code>string packageName = 2;</code>
    */
    public void setPackageName( java.lang.String value) 
    {
      if (value == null) 
      {
        throw new NullPointerException();
      }
  
      packageName_ = value;
    }

    private int iconType_ = 0; //!< Scoer.

    /**
    * Score.
    */
    public void setScore(int value) 
    {
      iconType_ = value;
    }
  }
