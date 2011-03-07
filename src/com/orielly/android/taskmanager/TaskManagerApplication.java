package com.orielly.android.taskmanager;

import java.util.ArrayList;
import com.orielly.android.taskmanager.Tasks.Task;
import com.orielly.android.taskmanager.Tasks.TasksSqlLiteOpenHelper;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import static com.orielly.android.taskmanager.Tasks.TasksSqlLiteOpenHelper.*;

public class TaskManagerApplication extends Application {

	private ArrayList<Task> currentTasks;
	private SQLiteDatabase database;

	@Override
	public void onCreate() {		
		super.onCreate();
		
		setUp();
	}

	private void setUp() {
		TasksSqlLiteOpenHelper helper = new TasksSqlLiteOpenHelper(this);
		database = helper.getWritableDatabase();
		
		if (currentTasks == null){
			loadTasks();
		}
	}
	
	private void loadTasks() {
		currentTasks = new ArrayList<Task>();
		Cursor tasksCursor = database.query(
				TASKS_TABLE
				, new String[] {
						TASK_ID, 
						TASK_NAME, 
						TASK_COMPLETE
						},
				null, 
				null, 
				null, 
				null, 
				String.format("%s,%s", TASK_COMPLETE, TASK_NAME)
				);
		
		tasksCursor.moveToFirst();
		Task t;
		
		if(! tasksCursor.isAfterLast()){
			do {
				long id = tasksCursor.getLong(0);
				String name = tasksCursor.getString(1);
				String boolValue = tasksCursor.getString(2);
				boolean complete = Boolean.parseBoolean(boolValue);
				
				t = new Task(name);
				t.setId(id);
				t.setComplete(complete);
				currentTasks.add(t);
			}while (tasksCursor.moveToNext());
		}
	}

	public ArrayList<Task> getCurrentTasks(){
		return this.currentTasks;
	}
	
	public void setCurrentTasks(ArrayList<Task> currentTasks){
		this.currentTasks = currentTasks;
	}

	public void addTask(Task task){
		assert(task != null);	
		
		ContentValues values = new ContentValues();
		values.put(TASK_NAME, task.getName());
		values.put(TASK_COMPLETE, Boolean.toString(task.isComplete()));
		
		long id = database.insert(TASKS_TABLE,null,values);
		task.setId(id);
		
		currentTasks.add(task);		
	}
	
	public void saveTask(Task task){
		assert(task != null);
		
		ContentValues values = new ContentValues();
		values.put(TASK_NAME, task.getName());
		values.put(TASK_COMPLETE, Boolean.toString(task.isComplete()));
		
		long id = task.getId();
		
		String where = String.format("%s = ?", TASK_ID);
		String[] whereArgs = new String[]{String.format("%d", id)};
		
		database.update(TASKS_TABLE, values, where, whereArgs);
	}
	
	public void deleteTasks(Long[] ids){
		StringBuffer idList = new StringBuffer();
		
		for(int i = 0; i<ids.length; i++){
			idList.append(ids[i]);
			if (i < ids.length -1){
				idList.append(",");
			}
		}
		
		String where = String.format("%s in (%s)", TASK_ID, idList);
		database.delete(TASKS_TABLE, where, null);
	}
	
}
