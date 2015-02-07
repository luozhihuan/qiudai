package com.hust.qiudai.web.service.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;

import com.hust.qiudai.web.db.User;
import com.hust.qiudai.web.db.dao.ILoginDao;
import com.hust.qiudai.web.service.ILoginService;
import com.hust.qiudai.web.util.CommonUtil;
import com.hust.qiudai.web.util.VariableUtil;
@Named
public class LoginService implements ILoginService{

	@Inject
	private ILoginDao iLoginDao;
	
	public String login( String email, String password) {
		
//		Message message = PhaseInterceptorChain.getCurrentMessage();
//		HttpServletRequest request = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);
		HttpServletRequest request = CommonUtil.getHttpServletRequest();
		
		
		/**获取账号密码**/
//		String email = request.getParameter(VariableUtil.EMAIL_PARAMETER_NAME);
//		String password = request.getParameter(VariableUtil.PASSWORD_PARAMETER_NAME);
		
		
		
		
System.out.println("登陆账号是"+email);		
System.out.println("登陆密码是"+password);		
/**获取session查看session是否是新的**/
//HttpSession session = (HttpSession)request.getSession();
//System.out.println(session.isNew());
//System.out.println("sessionid:"+request.getSession().getId());			
		/**查询该邮箱的相关信息**/
		User user = iLoginDao.checkTheUser(email);
		
		return getResultCodeFromUserAndPassword(user,password);
	}
	
	
	public String  getResultCodeFromUserAndPassword(User user,String password){
		/**初始化状态码,初始值为-1**/
		String resCode = "-1";
		if(user == null){
			/**该邮箱未找到，将状态码设置为相应值**/
			resCode = VariableUtil.EMAIL_NOT_FOUND + "";
		}else if(user != null && !user.getPassword().trim().equals(password.trim())){
			/**密码错误，将状态码设置为相应值**/
			resCode = VariableUtil.PASSWORD_ERROR + "";
		}else{
			/**登陆成功，将用户信息封装为json格式，并将状态码设置为json值**/
			resCode = CommonUtil.getJsonStringByGson(user);
		}
		return resCode;
		
	}

}
