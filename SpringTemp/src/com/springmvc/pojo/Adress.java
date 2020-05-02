package com.springmvc.pojo;

public class Adress {
	private String city;
	private String catt;
	public Adress() {
		// TODO Auto-generated constructor stub
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCatt() {
		return catt;
	}
	public void setCatt(String catt) {
		this.catt = catt;
	}
	public Adress(String city, String catt) {
		super();
		this.city = city;
		this.catt = catt;
	}
	@Override
	public String toString() {
		return "Adress [city=" + city + ", catt=" + catt + "]";
	}
	
}
