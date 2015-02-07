package com.luoyu.qiudai.register.service;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;

import com.luoyu.qiudai.util.DatabaseHelper;

public class UserLocalDBOperService {

	DatabaseHelper m_dbHelper = null;
	/**创建本地User数据库语句**/
	public String userDB ="create table if not exists user(email text primary key ,password text,lastlogintime datetime)";
	String columns[] = {"email","password","lastlogintime"};
	String userName = "user";
	public DatabaseHelper GetDBHelper()
	{
		return m_dbHelper;
	}
	
	public UserLocalDBOperService(DatabaseHelper dbHelper)
	{
		m_dbHelper = dbHelper;
		openOrCreateUserDB();
	}
	
	public void openOrCreateUserDB()
	{
		GetDBHelper().ExecNonSQL(userDB);
	}
	
	/**
	 * 从数据库中获取用户信息
	 * @return
	 */
	public JSONObject GetUserDataOfJson()
	{
		JSONObject result = new JSONObject();
		
		String selection=null;
		String selectionArgs[]=null;
		String groupBy =null;
		String having = null;
		String orderBy ="lastlogintime asc";
		try {
			result = GetDBHelper().GetJsonData(userName, columns, selection, selectionArgs, groupBy, having, orderBy);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean Insert(String email,String password,String datetime )
	{
		ContentValues values = new ContentValues();
		values.put("email", email);
		values.put("password", password);
		values.put("lastlogintime", datetime);
		return GetDBHelper().Insert(userName, values);
	}
}
