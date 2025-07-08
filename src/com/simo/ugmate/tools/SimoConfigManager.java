package com.simo.ugmate.tools;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import android.util.Log;

import com.simo.ugmate.db.domain.ItemConfigInfo;
import com.simo.ugmate.db.domain.SimConfigInfo;
import com.simo.ugmate.db.domain.TabConfigInfo;

/**
 * 
 * @author jp.lin 2013.12.26 the util class use to judge if the config xml which
 *         get from gmate if contain the key, the key is means which view should
 *         show or not.
 */
public class SimoConfigManager {
	
	private static final String TAG = "SimoConfigManager";
	private static List<SimConfigInfo> mSimInfos; //!<手机卡信息列表。
	private static SimoConfigManager mInstance;
	private SimoConfigManager(){
		
	}
	public static SimoConfigManager getInstance(){
		if(mInstance == null){
			mInstance = new SimoConfigManager();
		}
		return mInstance;
	}
	
	public static boolean isReadConfigFileSuccess(){
		if(mSimInfos==null || mSimInfos.size()==0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 
	 * @param simKey
	 * @return
	 */
	public static boolean isContainSimKey(String simKey) {
		if(mSimInfos==null || mSimInfos.size()==0){
			return true;
		}else{
			for (int i = 0; i < mSimInfos.size(); i++) {
				SimConfigInfo info = mSimInfos.get(i);
				if(info.getSim_type().equals(simKey)){
					return true;
				}else{
					continue;
				}
			}
			return false;
		}
	}
	/**
	 * 
	 * @param tabKey
	 * @param simKey
	 * @return
	 */
	public static boolean isContainTabKeyAtSim(String tabKey, String simKey) {
		if(mSimInfos==null || mSimInfos.size()==0){
			return true;
		}else{
			for (int i = 0; i < mSimInfos.size(); i++) {
				SimConfigInfo info = mSimInfos.get(i);
				
				if(info.getSim_type().equals(simKey)){
					List<TabConfigInfo> tabList = info.getTabList();
					for (int j = 0; j < tabList.size(); j++) {
						TabConfigInfo tab = tabList.get(j);
						if(tab.getTab_type().equals(tabKey)){
							return true;
						}else{
							continue;
						}
					}
				}else{
					continue;
				}
			}
			return false;
		}
	}
	/**
	 * 
	 * @param itemKey
	 * @param tabKey
	 * @param simKey
	 * @return
	 */
	public static boolean isContainItemKeyInTabAtSimKey(String itemKey,
			String tabKey, String simKey) {
		if(mSimInfos==null || mSimInfos.size()==0){
			return true;
		}else{
			for (int i = 0; i < mSimInfos.size(); i++) {
				SimConfigInfo info = mSimInfos.get(i);
				
				if(info.getSim_type().equals(simKey)){
					List<TabConfigInfo> tabList = info.getTabList();
					for (int j = 0; j < tabList.size(); j++) {
						TabConfigInfo tab = tabList.get(j);
						if(tab.getTab_type().equals(tabKey)){
							List<ItemConfigInfo> items = tab.getItemList();
							for (int k = 0; k < items.size(); k++) {
								ItemConfigInfo item =items.get(k);
								if(item.getItem_type().equals(itemKey)){
									return true;
								}else{
									continue;
								}
							}
						}else{
							continue;
						}
					}
				}else{
					continue;
				}
			}
			return false;
		}
	}
	
	/*when disconnect clean sim infos*/
	/**
	 * 清空手机卡相关的信息。
	 */
	public static void cleanSimInfos()
	{
		Log.e("ddddd", "cleanSimInfos !!!!!"); //Debug.
		if(mSimInfos !=null) //手机卡信息对象存在。
		{
			mSimInfos.clear(); //清空。
			mSimInfos = null; //释放。
		} //if(mSimInfos !=null) //手机卡信息对象存在。
	} //public static void cleanSimInfos()
}
