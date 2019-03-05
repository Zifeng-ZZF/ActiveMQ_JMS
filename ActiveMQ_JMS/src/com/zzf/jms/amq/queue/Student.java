package com.zzf.jms.amq.queue;

import java.io.Serializable;

public class Student implements Serializable {
	
	private int id = 0;
	private String name = null;
	private String department = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Student(int id, String name, String department) {
		this.id = id;
		this.name = name;
		this.department = department;
	}

}
