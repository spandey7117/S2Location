package com.S2Location;

import java.util.ArrayList;

import com.google.common.geometry.S2CellId;
import com.google.common.geometry.S2CellUnion;

public class CheckIfPresent {

	public ArrayList<UserDetail> GetNearbyUsersStart(ArrayList<UserDetail> userDetails,S2CellUnion s2CellUnion)
	{
		
		ArrayList<UserDetail> similar= new ArrayList<UserDetail>();
		for(int i=0 ; i<userDetails.size();i++)
		{
		S2CellId	cell = S2CellId.fromPoint(userDetails.get(i).startLocation);
		System.out.println(cell.id());
		if(s2CellUnion.contains(cell))
				{
			System.out.println(userDetails.get(i).startLatitude+" , "+userDetails.get(i).startLongitude +" is present");
			similar.add(userDetails.get(i));
				}
		
		else
		{
			
			System.out.println(userDetails.get(i).startLatitude+" , "+userDetails.get(i).startLongitude +" is not present");
		}
	}
	return similar;	
	}
	
	public ArrayList<UserDetail> GetNearbyUsersEnd(ArrayList<UserDetail> userDetails,S2CellUnion s2CellUnion)
	{ArrayList<UserDetail> similar= new ArrayList<UserDetail>();
		for(int i=0 ; i<userDetails.size();i++)
		{
		S2CellId	cell = S2CellId.fromPoint(userDetails.get(i).endLocation);
		System.out.println(cell.id());
		if(s2CellUnion.contains(cell))
				{
			System.out.println(userDetails.get(i).endLatitude+" , "+userDetails.get(i).endLongitude +" is present");
				
			similar.add(userDetails.get(i));
				}
		
		else
		{
			
			System.out.println(userDetails.get(i).endLatitude+" , "+userDetails.get(i).endLongitude +" is not present");
		}
	}
	return similar;	
	}
	
	
	
	
}
