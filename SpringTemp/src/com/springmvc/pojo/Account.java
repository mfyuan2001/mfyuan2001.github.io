package com.springmvc.pojo;

public class Account {
	private Integer id;
	private String name;
	private String registeTime;
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	public Account(Integer id, String name, String registeTime) {
		super();
		this.id = id;
		this.name = name;
		this.registeTime = registeTime;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", registeTime=" + registeTime + "]";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegisteTime() {
		return registeTime;
	}
	public void setRegisteTime(String registeTime) {
		this.registeTime = registeTime;
	}
	

}
