package com.luoyu.qiudai.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;

import com.luoyu.qiudai.exception.ParameterMoreThanOneException;
/**
 * 用于创建一个的ProgressDialog，同时费时的操作
 * 在新的线程中执行，执行完毕后，ProgressDialog结束
 * @author chuanrong
 *
 */
public class ThreadProgressDialogUtil {
	/**
	 * 根据传进来的参数数组返回参数Class类型数组
	 * 
	 * @param params
	 *            参数数组
	 * @return 参数Class类型数组
	 */
	public static Class[] getParamsDefine(Object[] params) {
		if (params == null) {
			return null;
		}
		Class[] paramsDefine = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			System.out.println(params[i].getClass().getName());
			try {
				paramsDefine[i] = Class.forName(params[i].getClass().getName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return paramsDefine;

	}
	
	/**
	 * 进度框展现
	 * @param dialog 进度框对象
	 * @param object1 线程中方法被执行的对象
	 * @param methodName1 线程中被调用的方法名称
	 * @param params1 线程中传递给被调用方法的参数集合
	 * @param object2 UI线程中方法被执行的对象
	 * @param methodName2 UI线程中被调用的方法名称
	 * @param fateWaitingTime 假等待时间，单位是毫秒
	 */
	public static void progressDialog(final ProgressDialog dialog,
			final Object object1, final String methodName1,
			final Object[] params1, final Object object2,
			final String methodName2,final long fateWaitingTime) {

		final int THREAD_PROCESS_OVER = 0;
		
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case THREAD_PROCESS_OVER:
					// 关闭ProgressDialog
					dialog.dismiss();
					if(object2 != null){
						methodBeInvokedSecond(object2,methodName2,msg.obj);
					}
				}
				super.handleMessage(msg);
			}
		};

		new Thread() {

			@Override
			public void run() {

				try {
					Thread.sleep(fateWaitingTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				Message msg = new Message();
				
				msg.obj = methodBeInvokedFirst(object1,methodName1,params1);
				msg.what = THREAD_PROCESS_OVER;
				handler.sendMessage(msg);
				
			}
		}.start();

	}
	
	
	/**
	 * 进度框展现
	 * @param dialog 进度框对象
	 * @param object1 线程中方法被执行的对象
	 * @param methodName1 线程中被调用的方法字符串名称
	 * @param params1 线程中传递给被调用方法的参数集合
	 * @param object2 UI线程中方法被执行的对象
	 * @param methodName2 UI线程中被调用的方法字符串名称
	 * @param parameterClazz UI线程中被调用的方法的参数的Class类型
	 */
	public static void progressDialog(final ProgressDialog dialog,
			final Object object1, final String methodName1,
			final Object[] params1, final Object object2,
			final String methodName2,final Class parameterClazz) {

		final int THREAD_PROCESS_OVER = 0;
		
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case THREAD_PROCESS_OVER:
					// 关闭ProgressDialog
					dialog.dismiss();
					if(object2 != null){
						methodBeInvokedSecond(object2,methodName2,msg.obj,parameterClazz);
					}
				}
				super.handleMessage(msg);
			}
		};

		new Thread() {

			@Override
			public void run() {
				
				try {
					Thread.sleep(2 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				Message msg = new Message();
				msg.obj = methodBeInvokedFirst(object1,methodName1,params1);
				msg.what = THREAD_PROCESS_OVER;
				handler.sendMessage(msg);
				
			}
		}.start();

	}
	
	/**
	 * 在UI线程中使用反射调用方法
	 * @param object2 被调用方法的对象
	 * @param methodName2 调用方法的方法名
	 * @param parameter 被调用方法的参数
	 * @param parameterClazz 被调用方法的参数的Class类型
	 */
	public static void methodBeInvokedSecond(Object object2,String methodName2,Object parameter,Class parameterClazz){
		try {
			Method method = getClassByObject(object2).getMethod(methodName2, parameterClazz);
			methodInvoke(object2, parameter, method);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParameterMoreThanOneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 在UI线程中使用反射调用方法
	 * @param object 被调用方法的对象
	 * @param methodName 调用方法的方法名
	 * @param parameter 被调用方法的参数
	 */
	public static void methodBeInvokedSecond(Object object,String methodName,Object parameter){
		Method[] methods = object.getClass().getMethods();
		try{
		/**遍历对象中的全部方法**/
		for(Method m : methods){
			/**找到我们需要调用的方法**/
			if(m.getName().equals(methodName)){
				methodInvoke(object, parameter, m);
				break;
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}





	/**
	 * @param object 反射机制被调用方法的对象
	 * @param parameter 反射机制被调用方法参数
	 * @param method 反射机制被调用方法
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ParameterMoreThanOneException
	 */
	public static void methodInvoke(Object object, Object parameter, Method method)
			throws IllegalAccessException, InvocationTargetException,
			ParameterMoreThanOneException {
		/**获取该方法的参数类型**/
		Class[] parameterTypes = method.getParameterTypes();
		/**参数类型数组为0表示该方法没有参数**/
		if(parameterTypes.length == 0){//被调用方法无参数
			method.invoke(object);
		}else if(parameterTypes.length == 1){
			/**获取该参数类型的字符串表达形式**/
			String type = parameterTypes[0].getName();
			
			/**判断参数类型是否为八个基础类型，如果是，将他们从包装类型转换为基础类型，再进行调用**/
			if(type.equals("int")){
				method.invoke(object, ((Integer)parameter).intValue());
			}else if(type.equals("double")){
				method.invoke(object, ((Double)parameter).doubleValue());
			}else if(type.equals("long")){
				method.invoke(object, ((Long)parameter).longValue());
			}else if(type.equals("short")){
				method.invoke(object, ((Short)parameter).shortValue());
			}else if(type.equals("char")){
				method.invoke(object, ((Character)parameter).charValue());
			}else if(type.equals("byte")){
				method.invoke(object, ((Byte)parameter).byteValue());
			}else if(type.equals("boolean")){
				method.invoke(object, ((Boolean)parameter).booleanValue());
			}else if(type.equals("float")){
				method.invoke(object, ((Float)parameter).floatValue());
			}else{/**如果参数类型不是基础类型，则直接进行调用**/
				method.invoke(object, parameter);
			}
			
//					m.invoke(object2, parameter);
		}else{/**参数类型数组大于1，表示该方法参数大于等于2，此时引起报错**/
			throw new ParameterMoreThanOneException();
		}
	}
	
	
	
	
	
	
	/**
	 * 在线程中使用反射机制调用方法
	 * @param object 反射机制调用方法的对象
	 * @param methodName 被调用方法的方法名字符串
	 * @param params 传递给被调用方法的参数
	 * @return 被调用方法的返回值，返回null表示返回值为空或者该方法没有返回值
	 */
	public static Object methodBeInvokedFirst(Object object,String methodName,Object[] params) {
		Object returnObject = null;
		try {
			/**获取被反射调用的具体方法**/
			Method m1 = getClassByObject(object).getMethod(methodName, getParamsDefine(params));
			/**获取被反射调用方法的返回值类型**/
			Class returnTypeClass = m1.getReturnType();
			/**返回类型为void，则表示没有返回值**/
			if (returnTypeClass.getName().trim().equals("void")) {
				returnTypeClass = null;
			}

			if (params == null) {/**反射方法无参数**/
				if (returnTypeClass == null) {// 没有返回值
					m1.invoke(object);
				} else {//有返回值
					returnObject = m1.invoke(object);
				}

			} else {// 反射方法有参数
				if (returnTypeClass == null) {// 没有返回值
					m1.invoke(object, params);
				} else {// 有返回值
					returnObject = m1.invoke(object, params);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnObject;
	}

	/**
	 * 根据对象返回该对象的Class类型
	 * 
	 * @param object
	 *            对象
	 * @return 对象的Class类型
	 */
	public static Class getClassByObject(Object object) {
		if (object == null) {
			return null;
		}
		Class clazz = null;
		try {
			clazz = Class.forName(object.getClass().getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clazz;
	}
}
