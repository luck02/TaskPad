package com.orielly.android.taskmanager.Tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TasksSqlLiteOpenHelper extends SQLiteOpenHelper {

	// 
	public static final String DB_NAME = "tasks_db.sqlite";
	public static final int VERSION = 2;
	
	public static final String TASKS_TABLE = "tasks";
	public static final String TASK_ID = "id";
	public static final String TASK_NAME = "name";
	public static final String TASK_COMPLETE = "complete";
	
	public TasksSqlLiteOpenHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
		
		
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTable(db);

	}

	protected void createTable(SQLiteDatabase db) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("create table " + TASKS_TABLE + " ( ");
		sb.append(TASK_ID + " integer primary key autoincrement not null , ");
		sb.append(TASK_NAME + " text ,");
		sb.append(TASK_COMPLETE + " text");
		sb.append(");");
		
		db.execSQL(sb.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

	}

}
