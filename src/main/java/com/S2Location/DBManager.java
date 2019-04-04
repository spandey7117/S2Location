package com.S2Location;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class DBManager {

	public ArrayList<String> findInDBBy(MongoClient mongoClient) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			// MongoClient mongoClient = new MongoClient(new
			// MongoClientURI("mongodb://admin:admin@cluster0-shard-00-00-2nifw.mongodb.net:27017,cluster0-shard-00-01-2nifw.mongodb.net:27017,cluster0-shard-00-02-2nifw.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true"));
			// System.out.println("Connected Successfully");
			MongoDatabase database = mongoClient.getDatabase("myNewDB");
			MongoCollection<Document> collection = database.getCollection("ASE_TestCollection");
			System.out.println("Collection ASE_TestCollection selected successfully");

			FindIterable<Document> iterDoc = collection.find(Filters.eq("status", "Pending"));

			// Getting the iterator
			Iterator it = iterDoc.iterator();

			if (it.hasNext()) {
				System.out.println("User present in pending status");

			} else {

				System.out.println("User Not present in pending status");
			}

			it = iterDoc.iterator();
			int count = 0;
			while (it.hasNext()) {
				String ss = it.next().toString();
				System.out.println(++count);
				System.out.println(ss);
				list.add(ss);

			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			mongoClient.close();
			return list;
		}
	}
	
	public UserDetails findInDBEmailID(String id, MongoClient mongoClient) {
		UserDetails user = new UserDetails();
		try {

			MongoDatabase database = mongoClient.getDatabase("myNewDB");
			MongoCollection<Document> collection = database.getCollection("UsersCollection");
			System.out.println("Collection UsersCollection selected successfully");

			FindIterable<Document> iterDoc = collection.find(Filters.eq("emailID",id));
			UserParser userParser= new UserParser();
			// Getting the iterator
			Iterator it = iterDoc.iterator();

			if (it.hasNext()) {
				System.out.println( id+ " email  exists");
				user= userParser.parser(it.next().toString());
			} else {
				System.out.println( id + " email does not exists in db UsersCollection");
				
			}
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			
			return user;
		}
	}
	
	public UserDetails findInDBEmailID2(String id, MongoClient mongoClient) {
		UserDetails user = new UserDetails();
		try {

			MongoDatabase database = mongoClient.getDatabase("myNewDB");
			MongoCollection<Document> collection = database.getCollection("UsersCollection");
			System.out.println("Collection UsersCollection selected successfully");

			FindIterable<Document> iterDoc = collection.find(Filters.eq("emailID",id));
			UserParser userParser= new UserParser();
			// Getting the iterator
			Iterator it = iterDoc.iterator();

			if (it.hasNext()) {
				System.out.println( id+ " email  exists");
				user= userParser.parser(it.next().toString());
				System.out.println("user rating "+user.getEmailID()+user.getRating());
			} else {
				System.out.println( id + " email does not exists in db ASE_TestCollection");
				
			}
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			
			return user;
		}
	}
	
	public Boolean findInDBEmailIDAndSex(String id,String sex, MongoClient mongoClient) {
		
		try {

			MongoDatabase database = mongoClient.getDatabase("myNewDB");
			MongoCollection<Document> collection = database.getCollection("UsersCollection");
			System.out.println("Collection UsersCollection selected successfully");
			BasicDBObject query = new BasicDBObject();
			
			query.put("emailID", id);
			query.put("sex", sex);
			System.out.println(query.toJson().toString());
			FindIterable<Document> iterDoc = collection.find( query);

			// Getting the iterator
			Iterator it = iterDoc.iterator();

			if (it.hasNext()) {
				System.out.println( id+ " User Valid");
				
				return true;
			} else {
				System.out.println( id + " User Invailde");
				return false;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		
			return false;
		}
	}
	
	


}
