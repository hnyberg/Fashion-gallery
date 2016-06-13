package com.fashion.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;

import com.fashion.model.*;

public class Main {

	public static void main(String[] args) {
		
		MongoDAO dao = new MongoDAO();
		HashMap<String, Object> map = new HashMap<>();
		
		ArrayList<String> bodyParts = new ArrayList<>();
		bodyParts.add("top, inner");
		bodyParts.add("feet");
		
		ArrayList<String> brands = new ArrayList<>();
		brands.add("Scotch&Soda");
		brands.add("Elganso");
		
		ArrayList<Float> priceRange = new ArrayList<>();
		priceRange.add(new Float(0));
		priceRange.add(new Float(200));
		
		map.put("body", bodyParts);
		map.put("brand", brands);
		map.put("price", priceRange);
		
		for (Document doc : dao.searchByFilter(map)) {
			System.out.println(doc);
		}
	}
}
