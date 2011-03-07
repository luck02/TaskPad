package com.orielly.android.taskmanager.adapter;

import java.util.ArrayList;

import com.orielly.android.taskmanager.Tasks.Task;
import com.orielly.android.taskmanager.views.TaskListItem;

import com.orielly.android.taskmanager.*;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TaskListAdapter extends BaseAdapter {
	private ArrayList<Task> tasks;
	private Context context;
	
	public TaskListAdapter(Context context,ArrayList<Task> tasks) {
		super();
		this.tasks = tasks;
		this.context = context;
	}

	@Override
	public int getCount() {

		return tasks.size();
	}

	@Override
	public Task getItem(int position) {
		return (tasks == null) ? null : tasks.get(position) ;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TaskListItem tli;
		
		if(convertView == null){
			tli = (TaskListItem)View.inflate(context, R.layout.task_list_item,null);
		} else {
			tli = (TaskListItem)convertView;
		}
		tli.setTask(tasks.get(position));
		
		return tli;
		}

	public void forceReload() {
		notifyDataSetChanged();		
	}

	public void toggleTaskCompletionAtPosition(int position) {
		Task t = tasks.get(position);
		
		t.toggleComplete();
		notifyDataSetChanged();
	}

	public Long[] removeCompletedTasks() {
		ArrayList<Task> completedTasks = new ArrayList<Task>();
		ArrayList<Long> completedIds = new ArrayList<Long>();

		for(Task task : tasks){
			if(task.isComplete()){
				completedIds.add(task.getId());
				completedTasks.add(task);
			}
		}
		
		tasks.removeAll(completedTasks);
		notifyDataSetChanged();
		
		return completedIds.toArray(new Long[]{});
	}
	
}
