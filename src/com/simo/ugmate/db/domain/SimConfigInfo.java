package com.simo.ugmate.db.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 手机卡信息。
 * @author root
 *
 */
public class SimConfigInfo 
{
	private String sim_type;
	private List<TabConfigInfo> tabList;

	public SimConfigInfo() {
		super();
		tabList = new ArrayList<TabConfigInfo>();
	}

	public String getSim_type() {
		return sim_type;
	}

	public void setSim_type(String sim_type) {
		this.sim_type = sim_type;
	}

	public List<TabConfigInfo> getTabList() {
		return tabList;
	}

	public void setTabList(List<TabConfigInfo> tabList) {
		this.tabList = tabList;
	}

} //public class SimConfigInfo
