package com.fashion.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Stateless
public class MongoDAO{

    //  Text constants
    public final String STRING_DATABASE = "fashion";
    public final String STRING_COLLECTION = "items";
    public final String STRING_KEY_ID = "_id";
    public final String STRING_KEY_NAME = "name";
    public final String STRING_KEY_DESCRIPTION = "description";
    public final String STRING_KEY_BODY = "body";
    public final String STRING_KEY_BRAND = "brand";
    public final String STRING_KEY_PRICE = "price";
    public final String STRING_KEY_MIN_PRICE = "minPrice";
    public final String STRING_KEY_MAX_PRICE = "maxPrice";
    public final String STRING_KEY_IMAGE_URL = "image";
    public final String STRING_KEY_MAIN_URL = "url";
    public final String STRING_KEY_USER = "user";

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
    
    public ArrayList<HashMap<String, String>> searchMongo(HashMap<String, Object> searchPreferences){
    	//	Every returned Hashmap represents an "Item" in the fashion bean
    	
    	//	Prepare filters-array
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
        float minPrize = Float.parseFloat((String) searchPreferences.get(STRING_KEY_MIN_PRICE));
        float maxPrize = Float.parseFloat((String) searchPreferences.get(STRING_KEY_MAX_PRICE));
        ArrayList<Document> priceList = new ArrayList<Document>();
        priceList.add(new Document(STRING_KEY_PRICE, new Document(
        		"$gte", minPrize)));
        priceList.add(new Document(STRING_KEY_PRICE, new Document(
        		"$lte", maxPrize)));
        filters.add(new Document("$and", priceList));
        
        //	Get search results
        List<Document> mongoResults = coll.find(
        		new Document("$and", filters))
        		.into(new ArrayList<Document>());
        
        //	Re-map mongo results (list of bson documents) 
        //	into ArrayList of items (hashmaps)
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        HashMap<String, String> tempMap;
        for (Document currentDoc : mongoResults) {
        	//	Add each field from current bson document to a hashmap
        	tempMap = new HashMap<String, String>();
        	tempMap.put("_id", currentDoc.get("_id").toString());
        	tempMap.put("name", currentDoc.get("name").toString());
        	tempMap.put("description", currentDoc.get("description").toString());
        	tempMap.put("body", currentDoc.get("body").toString());
        	tempMap.put("brand", currentDoc.get("brand").toString());
        	tempMap.put("price", currentDoc.get("price").toString());
        	tempMap.put("image", currentDoc.get("image").toString());
        	tempMap.put("url", currentDoc.get("url").toString());
        	tempMap.put("user", currentDoc.get("user").toString());
        	results.add(tempMap);
		}
        return results;
    }

    //  Setters
}