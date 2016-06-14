package com.fashion.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.fashion.model.*;

/**
 * Class just for testing the MongoDAO. Not used in the web application
 * 
 * @author Hannes Nyberg
 *
 */
public class Main {

	public static void main(String[] args) {
		
		MongoDAO dao = new MongoDAO();
		HashMap<String, Object> inputMap = new HashMap<>();
		
		ArrayList<String> bodyParts = new ArrayList<>();
		bodyParts.add("top, inner");
		bodyParts.add("feet");
		
		ArrayList<String> brands = new ArrayList<>();
		brands.add("Scotch&Soda");
		brands.add("Elganso");
		
		String minPrice = "0";
		String maxPrice = "200";
		
		inputMap.put("body", bodyParts);
		inputMap.put("brand", brands);
		inputMap.put("minPrice", minPrice);
		inputMap.put("maxPrice", maxPrice);
		
		for (HashMap currMap : dao.searchMongo(inputMap)) {
			System.out.println();
			System.out.println("ID: " + currMap.get("_id"));
			System.out.println("Name: " + currMap.get("name"));
			System.out.println("Description: " + currMap.get("description"));
			System.out.println("Body: " + currMap.get("body"));
			System.out.println("Brand: " + currMap.get("brand"));
			System.out.println("Item URL: " + currMap.get("url"));
			System.out.println("Image URL: " + currMap.get("image"));
			System.out.println("Price: " + currMap.get("price"));
		}
	}
}
