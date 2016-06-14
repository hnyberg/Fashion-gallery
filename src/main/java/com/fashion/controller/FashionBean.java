package com.fashion.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import com.fashion.model.*;

@RequestScoped
@ManagedBean
public class FashionBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String message = "Welcome, fashionist!";
	private String minPrice = "0";
	private String maxPrice = "5000";
	
	private ArrayList<String> body;
	private ArrayList<String> brand;
	
	private ArrayList<String> bodyOptions;
	private ArrayList<String> brandOptions;
	private ArrayList<Item> items;
	
	@EJB
	MongoDAO dao;
	
	public FashionBean() {
		
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
		
		items = new ArrayList<>();
		
		System.out.println("FashionBean (re)loaded");
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public void setBody(ArrayList<String> body){
		this.body = body;
	}
	
	public void setBodyOptions(ArrayList<String> bodyOptons){
		this.bodyOptions = bodyOptons;
	}
	
	public void setBrand(ArrayList<String> brand){
		this.brand = brand;
	}
	
	public void setBrandOptions(ArrayList<String> brandOptons){
		this.brandOptions = brandOptons;
	}
	
	public void setMinPrice(String minPrice){
		this.minPrice = minPrice;
	}
	
	public void setMaxPrice(String maxPrice){
		this.maxPrice = maxPrice;
	}
	
	public String getMessage() {
		return message;
	}
	
	public ArrayList<String> getBody(){
		return body;
	}
	
	public ArrayList<String> getBodyOptions(){
		return bodyOptions;
	}
	
	public ArrayList<String> getBrand(){
		return brand;
	}
	
	public ArrayList<String> getBrandOptions(){
		return brandOptions;
	}
	
	public String getMinPrice(){
		return minPrice;
	}
	
	public String getMaxPrice(){
		return maxPrice;
	}
	
	public ArrayList<Item> getItems() {
		System.out.println(items);
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public static class Item{
		String id;
		String name;
		String description;
		String body;
		String brand;
		String price;
		String image;
		String url;
		String user;
		
		public Item(String id, 
				String name, 
				String description, 
				String body, 
				String brand, 
				String price, 
				String image,
				String url, 
				String user) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.body = body;
			this.brand = brand;
			this.price = price;
			this.image = image;
			this.url = url;
			this.user = user;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		@Override
		public String toString() {
			return "Item [id=" + id + ", name=" + name 
					+ ", description=" + description 
					+ ", body=" + body + ", brand="
					+ brand + ", price=" + price + ", image=" 
					+ image + ", url=" + url + ", user=" 
					+ user + "]";
		}
	}

	public String searchByFilter(){
		
		HashMap<String, Object> inputMap = new HashMap<>();
		inputMap.put(dao.STRING_KEY_BODY, body);
		inputMap.put(dao.STRING_KEY_BRAND, brand);
		inputMap.put(dao.STRING_KEY_MIN_PRICE, minPrice);
		inputMap.put(dao.STRING_KEY_MAX_PRICE, maxPrice);
		
		ArrayList<HashMap<String, String>> listOfResultMaps = dao.searchMongo(inputMap);
		
		items.clear();
		for (HashMap<String, String> currMap : listOfResultMaps) {
			items.add(new Item(
					currMap.get(dao.STRING_KEY_ID),
					currMap.get(dao.STRING_KEY_NAME),
					currMap.get(dao.STRING_KEY_DESCRIPTION),
					currMap.get(dao.STRING_KEY_BODY),
					currMap.get(dao.STRING_KEY_BRAND),
					currMap.get(dao.STRING_KEY_PRICE),
					currMap.get(dao.STRING_KEY_IMAGE_URL),
					currMap.get(dao.STRING_KEY_MAIN_URL),
					currMap.get(dao.STRING_KEY_USER)
					));
		}
		
		return "index";
	}
}
