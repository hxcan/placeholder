package com.simo.ugmate.db.domain;

import java.util.ArrayList;
import java.util.List;

public class TabConfigInfo {
	private String tab_type;
	private List<ItemConfigInfo> itemList;

	public TabConfigInfo() {
		super();
		itemList = new ArrayList<ItemConfigInfo>();
	}

	public String getTab_type() {
		return tab_type;
	}

	public void setTab_type(String tab_type) {
		this.tab_type = tab_type;
	}

	public List<ItemConfigInfo> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemConfigInfo> itemList) {
		this.itemList = itemList;
	}

}
