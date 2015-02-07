package com.hust.qiudai.web.service;

import com.hust.qiudai.web.model.EmailInfo;


/**
 * 邮件服务
 * @author chuanrong
 *
 */
public interface ISendingEmailService {

	
	/**
	 * 发送邮件
	 * @param emailInfo：邮件信息
	 */
	public void sendingEmail(EmailInfo emailInfo);

}
