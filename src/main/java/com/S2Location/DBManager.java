package com.S2Location;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.bson.Document;

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
			MongoCollection<Document> collection = database.getCollection("sampleCollection2");
			System.out.println("Collection sampleCollection selected successfully");

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

}
