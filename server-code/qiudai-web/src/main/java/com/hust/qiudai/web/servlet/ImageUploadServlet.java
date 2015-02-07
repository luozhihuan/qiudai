package com.hust.qiudai.web.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class ImageUploadServlet
 */
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("上传");
		response.setContentType("text/html,charset=UTF-8");  
		  
	        SmartUpload smartUpload = new SmartUpload();  
	  
	        try {  
	            smartUpload.initialize(this.getServletConfig(), request, response);  
	            smartUpload.upload();  
	            com.jspsmart.upload.File smartFile = smartUpload.getFiles().getFile(0);  
	            if (!smartFile.isMissing()) {  
	            	String path = request.getSession().getServletContext().getRealPath("");//参数可具体到包名。            
	                String saveFileName = path+"\\uploadimage\\" + smartFile.getFileName(); 
	                
	               
	                
	                
 System.out.println(saveFileName);		                
	                smartFile.saveAs(saveFileName, smartUpload.SAVE_PHYSICAL);  
	                response.getWriter().print("ok:" + saveFileName + ", msg:" + smartUpload.getRequest().getParameter("msg"));  
	            } else {  
	                response.getWriter().print("missing...");  
	            }  
	        } catch (Exception e) {  
System.out.println("报错"+e);	        	
	            response.getWriter().print(e);  
	        }  
	  
	    }  
	

}
