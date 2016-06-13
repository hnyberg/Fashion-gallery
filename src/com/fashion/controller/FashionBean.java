package com.fashion.controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "fashion", eager = true)
public class FashionBean {
	
	ArrayList<String> bodyOptions;
	ArrayList<String> brandOptions;
	
	private String message = "Welcome, fashionist!";
	private String[] body;
	private String[] brand;
	
	public FashionBean() {
		System.out.println("FashionBean started!");
		
		 bodyOptions = new ArrayList<>();
		 bodyOptions.add("accessories");
		 bodyOptions.add("head");
		 bodyOptions.add("feet");
		 bodyOptions.add("bottom");
		 bodyOptions.add("top, inner");
		 bodyOptions.add("top, outer");
		 
		 brandOptions = new ArrayList<>();
		 brandOptions.add("Esprit");
		 brandOptions.add("Aragaza");
		 brandOptions.add("Elganso");
		 brandOptions.add("Scotch&Soda");
	}
	
	//	Setters
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public void setBody(String[] body){
		this.body = body;
	}
	
	public void setBodyOptions(ArrayList<String> bodyOptons){
		this.bodyOptions = bodyOptons;
	}
	
	public void setBrand(String[] brand){
		this.brand = brand;
	}
	
	public void setBrandOptions(ArrayList<String> brandOptons){
		this.brandOptions = brandOptons;
	}
	
	//	Getters
	
	public String getMessage() {
		return message;
	}
	
	public String[] getBody(){
		return body;
	}
	
	public ArrayList<String> getBodyOptions(){
		return bodyOptions;
	}
	
	public String[] getBrand(){
		return brand;
	}
	
	public ArrayList<String> getBrandOptions(){
		return brandOptions;
	}
}
