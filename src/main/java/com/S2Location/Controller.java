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
					System.out.println("userSimilarStart hh is not empty");
					System.out.println("Length of userSimilarStart "+userSimilarStart.size());
					
					for(int gg= 0;gg<userSimilarStart.size();gg++)
					{
						System.out.println(userSimilarStart.get(gg).getId());
					}
					
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
		
		@RequestMapping("/checkForGroupWithCondition")
		public Response userDetailsEntryWithCondition( @RequestParam(value = "id") String id) 
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
					//System.out.println("userSimilarStart is empty");
					res.setResponseCode("400");
					res.setResponseMessage("No Similar User");
				}
				else {
					System.out.println("userSimilarStart hh is not empty");
					System.out.println("Length of userSimilarStart "+userSimilarStart.size());
				S2CellUnion s2CellUnionEnd=createRegion.S2CellIdsCoveringGivenRadius(5000, String.valueOf(uu.endLatitude), String.valueOf(uu.endLongitude));
				ArrayList<UserLocDetail> userSimilarEnd= checkIfPresent.GetNearbyUsersEnd(userSimilarStart, s2CellUnionEnd);
				System.out.println("Length of userSimilarEnd "+userSimilarEnd.size());
				
				 
					ArrayList<UserLocDetail> userWithSimilarGoal= new ArrayList<UserLocDetail>();
					Helper helper = new Helper();
					
					userWithSimilarGoal	=helper.checkConditionWithMode(userSimilarEnd,uu, mongoClient);
					if(userWithSimilarGoal.size()>=1)
					{
						if(uu.getPreferedSex().equalsIgnoreCase("any"))
						{
							System.out.println("prefered mode is any ");
						}
						else
						{
							userWithSimilarGoal	=helper.checkConditionWithSex(userSimilarEnd,uu, mongoClient);
								
						}
					}
					else
					{
						System.out.println("userWithSimilarGoal is empty: no user with similar mode");
					}
					System.out.println("userWithSimilarGoal.size()"+userWithSimilarGoal.size());
					if(userWithSimilarGoal.size()>4)
					{
						ArrayList<UserLocDetail> userWithSimilarGoal1= new ArrayList<UserLocDetail>();
						ArrayList<UserDetails> users= new ArrayList<UserDetails>() ;
						for(int j=0;j<userWithSimilarGoal.size();j++)
						{System.out.println("inside for");
							UserDetails userDetail=	dbManager.findInDBEmailID2(userWithSimilarGoal.get(j).getId(), mongoClient);
							users.add(userDetail);
							UserLocDetail u=	userWithSimilarGoal.get(j);
							u.setRating(Double.parseDouble(userDetail.getRating()));
							userWithSimilarGoal1.add(u);
						}
					System.out.println(userWithSimilarGoal1.size());
					
						userWithSimilarGoal=	helper.checkConditionWithRating(userWithSimilarGoal1,users);
					
					}
					
					resArray= new ResultSimilarUser[userWithSimilarGoal.size()];
					for(int k=0;k<userWithSimilarGoal.size();k++)
					{
				//	System.out.println("Lets See"+" "+userWithSimilarGoal.get(k).id+" "+ userWithSimilarGoal.get(k).startLongitude+" "+ userWithSimilarGoal.get(k).startLatitude+" "+ userWithSimilarGoal.get(k).endLongitude+" "+ userWithSimilarGoal.get(k).endLatitude);
					ResultSimilarUser res123= new ResultSimilarUser(userWithSimilarGoal.get(k).id, userWithSimilarGoal.get(k).startLongitude, userWithSimilarGoal.get(k).startLatitude, userWithSimilarGoal.get(k).endLongitude, userWithSimilarGoal.get(k).endLatitude);
					resArray[k]=res123;
				}
				for(int p=0;p<resArray.length;p++)
				{
					System.out.println("ID of User Similar Users: "+resArray[p].id);
				}
				
				if(userSimilarEnd.isEmpty()) {
					System.out.println("userSimilarEnd is empty");
					res.setResponseCode("400");
					res.setResponseMessage("No Similar User");
				}
				
				else	if(userWithSimilarGoal.isEmpty()) {
					System.out.println("userWithSimilarGoal is empty");
					res.setResponseCode("404");
					res.setResponseMessage("No Similar User with same conditions");
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
		
		
		
		
		
		
		@RequestMapping("/checkForUsers")
		public Response checkForUsers( @RequestParam(value = "id") String id) 
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
					
					res.setResponseCode("200");
					res.setResponseMessage("Similar User");
					resArray= new ResultSimilarUser[userSimilarStart.size()];
					for(int k=0;k<userSimilarStart.size();k++)
					{ System.out.println("Lets See"+" "+userSimilarStart.get(k).id+" "+ userSimilarStart.get(k).startLongitude+" "+ userSimilarStart.get(k).startLatitude+" "+ userSimilarStart.get(k).endLongitude+" "+ userSimilarStart.get(k).endLatitude);
						ResultSimilarUser res123= new ResultSimilarUser(userSimilarStart.get(k).id, userSimilarStart.get(k).startLongitude, userSimilarStart.get(k).startLatitude, userSimilarStart.get(k).endLongitude, userSimilarStart.get(k).endLatitude);
						resArray[k]=res123;
					}
					
					
					res.setResultSimilarUsers(resArray);
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
		
		
		
		
		
		
		
		
	}
