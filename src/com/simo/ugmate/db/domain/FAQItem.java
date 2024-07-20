package com.simo.ugmate.db.domain;
/**
 * 
 * @author jp.lin 2012.12.05
 *
 */
public class FAQItem {
	private int faqid;
	private String question;
	private String answer;
	private String cat;
	private String keywords;
	public FAQItem(String question, String answer, String cat, String keywords) {
		this.question = question;
		this.answer = answer;
		this.cat = cat;
		this.keywords = keywords;
	}
	public FAQItem() {
	}
	
	public void setFaqDetailID(int id){
		this.faqid = id;
	}
	
	public int getFaqDetailID(){
		return this.faqid;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	@Override
	public String toString() {
		return "FAQItem [question=" + question + ", answer=" + answer
				+ ", cat=" + cat + ", keywords=" + keywords + "]";
	}
	
	 
}
