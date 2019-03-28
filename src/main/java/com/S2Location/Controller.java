package com.S2Location;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.geometry.S2CellUnion;
import com.mongodb.MongoClient;





	@RestController
	public class Controller {
		
		@RequestMapping("/checkForGroup")
		public Response userDetailsEntry( @RequestParam(value = "id") String id) 
				 {
		int check=0;
		ResultSimilarUser[] resArray=null;
			DBManager dbManager = new DBManager();
			ConnectionManager cm = new ConnectionManager();
			GettingUserDetails gt= new GettingUserDetails();
			Response res = new Response();
			CreateRegion createRegion = new CreateRegion();
			CheckIfPresent checkIfPresent = new CheckIfPresent();
			res.id=id;
			MongoClient mongoClient= cm.createConnection();
			try {
				
			ArrayList<String>	user=  dbManager.findInDBBy( mongoClient);
		ArrayList<UserLocDetail> userDetails=	gt.converter(user);
		for(int i=0;i<userDetails.size();i++)
		{
			UserLocDetail uu=userDetails.get(i);
		
			if(uu.id.equals(id))
			{	check=1;
				userDetails.remove(uu);
				System.out.println("present in DB , given ID");
				S2CellUnion s2CellUnionStart=createRegion.S2CellIdsCoveringGivenRadius(5000, String.valueOf(uu.startLatitude), String.valueOf(uu.startLongitude));
				ArrayList<UserLocDetail> userSimilarStart= checkIfPresent.GetNearbyUsersStart(userDetails, s2CellUnionStart);
				if(userSimilarStart.isEmpty()) {
					System.out.println("userSimilarStart is empty");
					res.setResponseCode("400");
					res.setResponseMessage("No Similar User");
				}
				else {
					System.out.println("userSimilarStart is not empty");
				S2CellUnion s2CellUnionEnd=createRegion.S2CellIdsCoveringGivenRadius(5000, String.valueOf(uu.endLatitude), String.valueOf(uu.endLongitude));
				ArrayList<UserLocDetail> userSimilarEnd= checkIfPresent.GetNearbyUsersEnd(userSimilarStart, s2CellUnionEnd);
				System.out.println("Length of userSimilarEnd "+userSimilarEnd.size());
				resArray= new ResultSimilarUser[userSimilarEnd.size()];
				for(int k=0;k<userSimilarEnd.size();k++)
				{ System.out.println("Lets See"+" "+userSimilarEnd.get(k).id+" "+ userSimilarEnd.get(k).startLongitude+" "+ userSimilarEnd.get(k).startLatitude+" "+ userSimilarEnd.get(k).endLongitude+" "+ userSimilarEnd.get(k).endLatitude);
					ResultSimilarUser res123= new ResultSimilarUser(userSimilarEnd.get(k).id, userSimilarEnd.get(k).startLongitude, userSimilarEnd.get(k).startLatitude, userSimilarEnd.get(k).endLongitude, userSimilarEnd.get(k).endLatitude);
					resArray[k]=res123;
				}
				for(int p=0;p<resArray.length;p++)
				{
					System.out.println("ID of User Similar1111: "+resArray[p].id);
				}
				
				if(userSimilarEnd.isEmpty()) {
					System.out.println("userSimilarEnd is empty");
					res.setResponseCode("400");
					res.setResponseMessage("No Similar User");
				}
				
				else
				{System.out.println("userSimilarEnd is not empty");
				
				
				
				res.setResultSimilarUsers(resArray);
					res.setResponseCode("200");
					res.setResponseMessage("Successfull");
				}
				
				}
			}
			else
			{	if(check==0) {
				res.setResponseCode("600");
				res.setResponseMessage("LocationNotInDB");
				System.out.println("Not present in DB , given ID");}
			}
		}
		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			mongoClient.close();
			
			return res;
			
	}
		@RequestMapping("/check")
		public String check() {
		String hello="hello";
			try {

				
				
				return hello;
			}
			catch (Exception e) {
				System.out.println("Error In controller");
				e.printStackTrace();
				
				return hello;
			}
			
	}
	}
