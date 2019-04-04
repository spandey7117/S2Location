package com.S2Location;
import java.util.*;

import com.google.common.geometry.S2LatLng;
import com.google.common.geometry.S2Point;
public class GettingUserDetails {

	ArrayList<UserLocDetail> converter(ArrayList<String> ss)
	{int count=0;
	ArrayList<UserLocDetail> usr= new ArrayList<UserLocDetail>();
		for(int i=0;i<ss.size();i++) {
			
			String s= ss.get(i);
			String[] afterComa=s.split(",");
			S2Point startLocation= null;
			S2Point endLocation= null;
			String id="";
			String status="";
			String preferedMode="",preferedSex="",myPlanName="";
			double startLongitude=0;
			double startLatitude = 0;
			double endLongitude=0;
			double endLatitude=0;
			String time="";
			for(int j=0;j<afterComa.length;j++) {
				String SAC=afterComa[j];
				String[] afterEqual=SAC.split("=");
			//	System.out.println(count+" "+afterEqual[0]);
				
				if( afterEqual[0].trim().equals("endLatitude"))
				{
					endLongitude=Double.parseDouble(afterEqual[1]);
				}
				
				
				if( afterEqual[0].trim().equals("endLongitude"))
				{
					endLongitude=Double.parseDouble(afterEqual[1]);
				}
				if(afterEqual[0].trim().equals("myPlanName"))
				{
					myPlanName=afterEqual[1];
				}
				if(afterEqual[0].trim().equals("id"))
				{
					//System.out.println("id"+id);
					id=afterEqual[1];
				}
				if(afterEqual[0].trim().equals("startLatitude"))
				{
					startLatitude=Double.parseDouble(afterEqual[1]);
				}
				
				if(afterEqual[0].trim().equals("status"))
				{
					status=afterEqual[1];
				}
				if(afterEqual[0].trim().equals("startLongitude"))
				{//System.out.println("startLongitude "+afterEqual[1].substring(0,afterEqual[1].length()-2));
					startLongitude=Double.parseDouble(afterEqual[1]);
				}
				if(afterEqual[0].trim().equals("time"))
				{
					time=afterEqual[1];
				}
				if(afterEqual[0].trim().equals("preferedMode"))
				{
					preferedMode=afterEqual[1];
				}
				if(afterEqual[0].trim().equals("preferedSex"))
				{
					preferedSex=afterEqual[1];
				}
			
				 startLocation = S2LatLng.fromDegrees(startLatitude, startLongitude).toPoint();

				 endLocation = S2LatLng.fromDegrees(endLatitude, endLongitude).toPoint();
				
						
				
				count++;
		}
			UserLocDetail ud= new UserLocDetail(startLocation, endLocation, id, status, startLongitude, startLatitude, endLongitude, endLatitude, time, preferedMode, preferedSex,0);
			usr.add(ud);
			count=0;
		
		
	}
		return usr;
		}
	
}
	

