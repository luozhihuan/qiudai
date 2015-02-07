package com.hust.qiudai.web.service.impl;

import java.util.Random;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hust.qiudai.web.db.dao.IFindPasswordDao;
import com.hust.qiudai.web.model.EmailInfo;
import com.hust.qiudai.web.service.IFindPasswordService;
import com.hust.qiudai.web.service.ISendingEmailService;
import com.hust.qiudai.web.util.CommonUtil;
import com.hust.qiudai.web.util.VariableUtil;

@Named
public class FindPasswordService implements IFindPasswordService {

	@Inject
	private IFindPasswordDao iFindPasswordDao;
	
	@Inject
	private ISendingEmailService iSendingEmailService;

	
	private static final Log log = LogFactory.getLog(FindPasswordService.class);
	
	public String findPassword() {
		log.info("找回密码");
		try {
			HttpServletRequest request = CommonUtil.getHttpServletRequest();

			/** 获取账邮箱 **/
			String email = request.getParameter(VariableUtil.EMAIL_PARAMETER_NAME);

			if (isTheEmailIsRegistered(email)) {
				/**此处使用线程的方式得到新密码并在数据库中进行修改，然后将新密码用邮件发送给该email**/
				new FindPasswordThread(email).start();
				
				/**该邮箱存在，则返回新密码已发送至邮箱的结果码**/
				return VariableUtil.NEW_PASSWORD_SEND_SUCCESS + "";
			} else {
				/**该邮箱不存在（未注册）,则返回该邮箱未被注册结果码**/
				return VariableUtil.EMAIL_IS_NOT_REGISTERED + "";
			}
		} catch (Exception e) {
			/**服务器端出现异常则返回客户端连接失败的结果码**/
			return VariableUtil.CONNECT_FAILED + "";
		}

	}
	/**
	 * 修改该邮箱用户的登陆密码
	 * @param email：邮箱地址
	 * @param password：密码
	 */
	public void changePasswordOfTheEmail(String email,String password){
		iFindPasswordDao.updatePasswordOfThewEmail(email,password);
	}
	
	/**
	 * 发送新密码到该邮箱
	 * @param email：邮箱
	 */
	public void sendNewPasswordToEmail(String email){
		/**生成EmailInfo对象，设置发送方的邮箱和邮箱密码**/
		EmailInfo emailInfo = new EmailInfo("qiudai8@163.com","qiudai8~");
		/**设置接收方的邮箱**/
		emailInfo.setReceiveEmail(email);
		/**设置邮箱主题**/
		emailInfo.setEmailSubject("求带--密码重置");
		/**生成一个4位字符串的随机密码**/
		String newPassword = generateNewPassword(4);
		/**设置邮箱内容**/
		emailInfo.setEmailContent("重置密码是:" + newPassword);
		/**在数据库中修改该用户密码未newPassword**/
		changePasswordOfTheEmail(email,newPassword);
		
		iSendingEmailService.sendingEmail(emailInfo);
	}
	

	/**
	 * 返回一个长度为num的随机字符串密码
	 * @param num：长度
	 * @return 随机字符串密码
	 */
	public String generateNewPassword(int num){
		StringBuffer password = new StringBuffer();
		for(int i = 0;i < num; i++){
			/**取0到25范围内的随机数**/
			int numberBetween0To25 = new Random().nextInt(26);
			char c;
			/**如果选择大写c就会得到一个A到Z的一个随机的大写字母**/
			if(chooseUpper()){
				c = (char)('A' + numberBetween0To25);
			}else{/**如果选择非大写c就会得到一个a到z的一个随机的大写字母**/
				c = (char)('a' + numberBetween0To25);
			}
			password.append(c);
		}
		return password.toString();
		
	}
	/**
	 * 是否大写
	 * @return 大写:true 小写：false
	 */
	public boolean chooseUpper(){
		/**在0和1两个取随机数，如果取到1，那么大写**/
		int randomNum = new Random().nextInt(2);
		if(randomNum == 1){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 该email是否被注册
	 * 
	 * @param email
	 *            邮箱
	 * @return 已被注册：true；未被注册：false
	 */
	public boolean isTheEmailIsRegistered(String email) {
		if (email == null) {
			return false;
		}

		int numOfTheEmail = iFindPasswordDao.findUserNumByUserEmail(email);
		if (numOfTheEmail >= 1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 该线程用于设置email新密码并将新密码发送至该email，同时在数据库中
	 * 修改该email用户的密码
	 * @author chuanrong
	 *
	 */
	class FindPasswordThread extends Thread{
		String email;
		public FindPasswordThread(String email){
			this.email = email;
		}
		@Override
		public void run() {
			log.info("发送邮件");
			sendNewPasswordToEmail(email);
		}
		
	}
}
