package com.stupidbeauty.threeupgrade;

// import com.stupidbeauty.hxlauncher.datastore.LauncherIconType;

public class AnswerAvailableEvent
{
	private String shortcutId; //!<快捷方式编号．

	public String getShortcutId() {
		return shortcutId;
	}

	public void setShortcutId(String shortcutId) {
		this.shortcutId = shortcutId;
	}

	private final String answerValue; //!<答案值。
	private String activityName; //!<活动名字。

	public String getActivityName() {
		return activityName;
	}

	public String getAnswerValue() {
		return answerValue;
	}

	public AnswerAvailableEvent(String context, String activityName)
	{
 
		answerValue=context; //记录答案值。
		this.activityName=activityName; //记录活动名字。
	} // public AnswerAvailableEvent(String context, String activityName)
}
