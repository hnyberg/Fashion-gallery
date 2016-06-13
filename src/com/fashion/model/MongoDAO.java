package com.fashion.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MongoDAO{

    //  Text constants
    private final String STRING_DATABASE = "fashion";
    private final String STRING_COLLECTION = "items";
//    private final String STRING_KEY_ID = "_id";
//    private final String STRING_KEY_NAME = "name";
//    private final String STRING_KEY_DESCRIPTION = "description";
    private final String STRING_KEY_BODY = "body";
    private final String STRING_KEY_BRAND = "brand";
    private final String STRING_KEY_PRICE = "price";
//    private final String STRING_KEY_IMAGE_URL = "image";
//    private final String STRING_KEY_MAIN_URL = "url";
//    private final String STRING_KEY_USER = "user";

    //  Declarations
    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection<Document> coll;

    public MongoDAO(){

        client = new MongoClient();
        db = client.getDatabase(STRING_DATABASE);
        coll = db.getCollection(STRING_COLLECTION);
        
        System.out.println("Connected to " + STRING_DATABASE);
    }

    //  Getters

    public List<Document> searchByFilter(HashMap<String, Object> searchPreferences){
    	
    	//	Prepare filters
        ArrayList<Document> filters = new ArrayList<Document>();
        
        //	Set body section filter
        ArrayList<String> bodySearchPreferences = 
        		(ArrayList<String>) searchPreferences
        		.get(STRING_KEY_BODY);
        ArrayList<Document> bodyList = new ArrayList<Document>();
        for (String string : bodySearchPreferences) {
			bodyList.add(new Document(STRING_KEY_BODY, string));}
        filters.add(new Document("$or", bodyList));
        
        //	Set brand filter
        ArrayList<String> brandSearchPreferences = 
        		(ArrayList<String>) searchPreferences
        		.get(STRING_KEY_BRAND);
        ArrayList<Document> brandList = new ArrayList<Document>();
        for (String string : brandSearchPreferences) {
			brandList.add(new Document(STRING_KEY_BRAND, string));}
        filters.add(new Document("$or", brandList));
        
        //	Set price filter
        ArrayList<Float> priceSearchPreferences = (ArrayList<Float>) searchPreferences.get(STRING_KEY_PRICE);
        ArrayList<Document> priceList = new ArrayList<Document>();
        priceList.add(new Document(STRING_KEY_PRICE, new Document("$gte", priceSearchPreferences.get(0))));
        priceList.add(new Document(STRING_KEY_PRICE, new Document("$lte", priceSearchPreferences.get(1))));
        filters.add(new Document("$and", priceList));
        
        //	Get search results
        List<Document> results = coll.find(
        		new Document("$and", filters))
        		.into(new ArrayList<Document>());
        
        return results;
    }

    //  Setters
}