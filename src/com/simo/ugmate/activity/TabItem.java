package com.simo.ugmate.activity;

import android.content.Intent;

/**
 * 
 * @author ljp packaging paramaters of every tab item
 */
public class TabItem {
	private String title; // tab item title
	private int icon; // tab item icon
	private int bg; // tab item background
	private Intent intent; // tab item intent
	private String tabSpecId;// id for tab spec
	private boolean isVisual;// set if the tab is visual

	public TabItem(String title, String tabId, int icon, int bg, boolean visual, Intent intent) {
		super();
		this.title = title;
		this.tabSpecId = tabId;
		this.icon = icon;
		this.bg = bg;
		this.isVisual = visual;
		this.intent = intent;
	}

	public String getTitle() {
		return title;
	}

	public void setTabSpecId(String tabSpecId) {
		this.tabSpecId = tabSpecId;
	}

	public String getTabSpecId() {
		return tabSpecId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public int getBg() {
		return bg;
	}

	public void setBg(int bg) {
		this.bg = bg;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	public boolean isVisual() {
		return isVisual;
	}

	public void setVisual(boolean isVisual) {
		this.isVisual = isVisual;
	}
	
	
}
