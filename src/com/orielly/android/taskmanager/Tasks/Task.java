package com.orielly.android.taskmanager.Tasks;

import java.io.Serializable;

public class Task  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @return the id
	 */

	private String name;
	private boolean complete;
	private long id;
	
	public void setId(long id) {
		this.id = id;
		
	}
	
	public long getId() {
		return id;
	}
	public Task(String taskName){
		name = taskName;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String toString(){
		return this.name;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public boolean isComplete() {
		return complete;
	}

	public void toggleComplete() {
		this.complete = !this.complete;		
	}


}
