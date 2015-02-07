package com.luoyu.qiudai.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	private static final int VERSION = 1; 
	private static String Name = VariableUtil.DataBaseNAME;
	
	private Context context;
	public DatabaseHelper(Context context, String name, CursorFactory factory,int version) 
	{
		
		super(context, name, factory, version);
	}
	public DatabaseHelper(Context context, String name, int version)
	 {  
	    this(context,name,null,version);  
	    Name = name;
	 }  
	  
	public DatabaseHelper(Context context, String name)
	 {  
	    this(context,name,VERSION);  
	    Name = name;
	 }  
	
	public DatabaseHelper(Context context)
	 {  
		
	     this(context,Name);  
	     this.context = context;
	 }  
	
	/**
	 * 当程序启动，我们第一次调用DatabaseHepler的getReadableDatabase()或者是getWriteableDatabase()时，
	 * 也就是第一次获取SQLiteDatabase这个对象时，程序会调用DatabaseHepler对象的onCreate方法
	 */
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// TODO 创建数据库后，对数据库的操作
		System.out.println("数据库onCreate方法");
	}

	/**
	 * 当DatabaseHepler传入的版本参数和之前不一样的时候，并且在调用了DatabaseHepler的getReadableDatabase()
	 * 或者是getWriteableDatabase()时候（不管哪一次调用），就会调用DatabaseHepler对象的onUpgrade方法，如下所示：
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO 更改数据库版本的操作 
		
	}
	
	@Override
	public void onOpen(SQLiteDatabase db)
	{    
		super.onOpen(db);                 
		// TODO 每次成功打开数据库后首先被执行       
		
	}    
	SQLiteDatabase m_db;
	DatabaseHelper m_dbHelper;
	
	/**
	 * 根据当前的Activity和数据库的名字，创建或打开数据库
	 * @param activity
	 * @param dbName
	 */
	public void openOrCreateDataBase(Activity activity,String dbName)
	{
		m_dbHelper = new DatabaseHelper(activity,dbName);
		GetWritableDatabase();
		Close();
	}
	public void openOrCreateDataBase(Activity activity)
	{
		openOrCreateDataBase(activity, Name);
	}
	/**
	 * 根据sqlString 执行对应的sql语句（包括Delete、Insert、Update、Create）
	 * @param sqlString
	 */
	public void ExecNonSQL(String sqlString )
	{
		GetWritableDatabase();
		m_db.execSQL(sqlString);
		Close();
	}
	
	/**
	 * 关闭数据库连接
	 */
	public void Close()
	{
		m_db.close();
	}
	
	public boolean Insert(String table,ContentValues values)
	{
		long rowid = m_db.insert(table, null, values);
		Close();
		if(rowid<0)
			return false;
		return true;
	}
	
	private void GetWritableDatabase()
	{
		m_db =m_dbHelper.getWritableDatabase();
	}
	
	private void GetReadableDatabase()
	{
		m_db =m_dbHelper.getReadableDatabase();
	}
	
	/**
	 * 删除数据
	 * @param table
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 */
	public boolean Delete(String table,String whereClause,String[] whereArgs)
	{
		 
		int rowid = m_db.delete(table, whereClause, whereArgs);
		Close();
		if(rowid<0)
			return false;
		return true;
	}
	
	/**
	 * 更新数据
	 * @param table
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 */
	public boolean Update(String table,ContentValues values,String whereClause,String[] whereArgs)
	{
		int rowid = m_db.update(table, values, whereClause, whereArgs);
		Close();
		if(rowid<0)
			return false;
		return true;
	}

	/**
	 * 获取数据
	 * @param table
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @return
	 * @throws JSONException
	 */
	public JSONObject GetJsonData(String table,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy) throws JSONException
	{
		JSONObject queryJson = new JSONObject();
		/**获取可读数据库操作对象**/
		GetReadableDatabase();
		
		
		
		/***此处报错**/
		Cursor cursor = m_db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		
		((Activity)context).startManagingCursor(cursor);
		
		int row = 0;
		while(cursor.moveToNext())
		{				
			JSONObject element = new JSONObject();
			for (int i = 0;i<columns.length;++i) {
				element.put(columns[i], cursor.getString(i));
			}
			queryJson.put(Integer.toString(row), element);
		}
//		((Activity)context).stopManagingCursor(cursor);
		this.close();
		
		
		
		return queryJson;
	}
}
