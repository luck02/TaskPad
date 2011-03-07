package com.orielly.android.taskmanager;

import com.orielly.android.taskmanager.Tasks.Task;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskActivity extends TaskManagerActivity {

	private EditText editTextTaskName;
	private Button buttonAddTask;
	private Button buttonCancel;
	protected boolean changesPending;
	private AlertDialog unsavedChangesDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_tasks);
		
		setUpViews();
	}

	protected void cancel() {
		String taskName = editTextTaskName.getText().toString();
		
		if (changesPending && !taskName.equals("")) {
			unsavedChangesDialog = new AlertDialog.Builder(this)
				.setTitle(R.string.unsaved_changes_title)
				.setMessage(R.string.unsaved_changes_message)
				.setPositiveButton(R.string.add_task, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						addTask();
					}
				})
				.setNeutralButton(R.string.discard, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				})
				.setNegativeButton(R.string.cancel, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						unsavedChangesDialog.cancel();
					}
				})
				.create();
				
			unsavedChangesDialog.show();
				
				
		} else {
			finish();	
		}
		
	}

	protected void addTask() {
		String taskName = editTextTaskName.getText().toString();
		
		if (!taskName.equals("")){
			Task t = new Task(taskName);
			getTaskManagerApplication().addTask(t);
		}
			
		finish();
	}
	
	private void setUpViews() {
		editTextTaskName = (EditText)findViewById(R.id.Edit_task_name);
		buttonAddTask = (Button)findViewById(R.id.button_add);
		buttonCancel = (Button)findViewById(R.id.button_cancel);
		
		buttonAddTask.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addTask();
			}
		});
		
		buttonCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cancel();
				
			}
		});
		
		editTextTaskName.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				changesPending = true;
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,	int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
		
	}

	

}
