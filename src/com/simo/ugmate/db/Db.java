package com.simo.ugmate.db;

import java.util.List;
import java.util.Map;

import android.net.Uri;

import com.simo.ugmate.db.domain.FAQItem;

/**
 * @author siuming 6.10.2013
 *
 */
public interface Db {
	
	public void close();
	
	/** *********************** Settings *********************** */
	public int addSettings(Map<String, Object> map);
	
	public int deleteSettingsById(int id);
	
	public int deleteSettingsAll();
	
	public Map<String, Object> findSettingsById(int id);
	
	public List<Map<String, Object>> findSettingsByKey(String key);
	
	public List<Map<String, Object>> findSettingsAll();
	
	public int updateSettingsById(int id, String info);
	
	public int updateSettings(Map<String, Object> map);

	/** *********************** Communication *********************** */
	public int addCommunication(Map<String, Object> map);
	
	public int deleteCommunicationById(int id);
	
	public int deleteCommunicationAllByCid(int cid);
	
	public Map<String, Object> findCommunicationById(int id);
	
	public List<Map<String, Object>> findCommunicationByCid(int cid);
	
	public List<Map<String, Object>> findCommunicationByNumber(String number);
	
	public int updateCommunicationReaded(int cid);

	/** *********************** Conversation *********************** */
	public int deleteConversationById(int id);
	
	public int deleteConversationAll();
	
	public List<Map<String, Object>> findConversationAll();
	
	public List<Map<String, Object>> findConversationByMissAndUnread();
	
	public Map<String, Object> findConversationById(int id);
	
	public Map<String, Object> findConversationByNumber(String number);
	
	public int addConversationDraft(String number, String draft);
	
	public int deleteConversationDraft(int id);
	
	public int updateConversationDraftById(int id, String draft);
	
	public int updateConversationDraftByNumber(String number, String draft);
	
	public void updateCoversationReaded(int id);
	
	/** *********************** Contacts *********************** */
	public int addContacts(Map<String, Object> map);
	
	public int deleteContactsById(int id);
	
	public int deleteContactsAll();
	
	public Map<String, Object> findContactsById(int id);
	
	public  Map<String, Object> findContactsByName(String name);
	
	public List<Map<String, Object>> findContactsByNumber(String number);

	public List<Map<String, Object>> findContactsByType(int type);// note
	
	public List<Map<String, Object>> findContactsByType(int type, String text); //note
	
	public void updateContact(Map<String, Object> map);
	
	/** *********************** ContactsField *********************** */
	public int addContactsField(Map<String, Object> map);
	
	public int deleteContactsFieldByCid(int cid);
	
	public int deleteContactsFieldById(int id);
	
	public List<Map<String, Object>> findContactsFieldByCid(int cid);
	
	public void updateContactField(Map<String, Object> map);
	
	/** *********************** Country *********************** */
	public Map<String, Object> findCountryByMCC(int mcc);
	
	/** *********************** Local phone DB *********************** */
	public Uri findRingStone(String number);
	public byte[] findContactPhoto(int photoId);
	public void copyContacts();
	
	/** *********************** simo_faq_detail DB *********************** */
	public List<String> getFaqCategoryNamesByLangID(int langId); //!<按照语言编号来获取常见问题类别名字列表。
	public List<FAQItem> getFaqItemsByCategoryName(String name, int langid);
	public List<FAQItem> findFaqItemsByKeyword(String keyword,int langId);
	public void removeFaqItemsByLandID(int langId);
	public void insertFaqItems(List<FAQItem> items, int langId);
	
}
