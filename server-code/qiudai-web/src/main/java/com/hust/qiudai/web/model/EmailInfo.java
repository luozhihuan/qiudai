package com.hust.qiudai.web.model;
/**
 * 邮件信息
 * @author chuanrong
 *
 */
public class EmailInfo {
	
	
	/**发送方email地址**/
	private String sendEmail;
	
	/**发送方邮件密码**/
	private String sendEmailPassword;
	
	/**接收方邮件地址**/
	private String receiveEmail;
	
	/**邮件主题**/
	private String emailSubject;
	
	/**邮件内容**/
	private String emailContent;

	public String getSendEmail() {
		return sendEmail;
	}
	
	public EmailInfo(String sendEmail,String sendEmailPassword){
		this.sendEmail = sendEmail;
		this.sendEmailPassword = sendEmailPassword;
	}
	

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	public String getSendEmailPassword() {
		return sendEmailPassword;
	}

	public void setSendEmailPassword(String sendEmailPassword) {
		this.sendEmailPassword = sendEmailPassword;
	}

	public String getReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

}
