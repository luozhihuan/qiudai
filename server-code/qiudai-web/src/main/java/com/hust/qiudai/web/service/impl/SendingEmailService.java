package com.hust.qiudai.web.service.impl;

import javax.inject.Named;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.hust.qiudai.web.email.SimpleMailSender;
import com.hust.qiudai.web.model.EmailInfo;
import com.hust.qiudai.web.service.ISendingEmailService;

@Named
public class SendingEmailService implements ISendingEmailService {
	
	
	public void sendingEmail(EmailInfo emailInfo) {

		SimpleMailSender sender = new SimpleMailSender(emailInfo.getSendEmail(), emailInfo.getSendEmailPassword());
		try {
			sender.send(emailInfo.getReceiveEmail(), emailInfo.getEmailSubject(), emailInfo.getEmailContent());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
