package com.S2Location;

import java.util.ArrayList;

import com.mongodb.MongoClient;

public class Helper {

	String preferedSex="";
	public ArrayList<UserLocDetail> checkConditionWithSex(ArrayList<UserLocDetail> userLoc, UserLocDetail uu, MongoClient mongoClient)
	{DBManager dbManager= new DBManager();
		if(uu.getPreferedSex().equalsIgnoreCase("Male"))
		{
			preferedSex="M";
		}
		else if(uu.getPreferedSex().equalsIgnoreCase("Female"))
		{
			preferedSex="F";
		}
		
			
	//String preferedSex="M";
	ArrayList<UserLocDetail> userByPreferedSex = new ArrayList<UserLocDetail>();
		 
				
				 
				 for(int i=0;i<userLoc.size();i++)
				 {
				if( dbManager.findInDBEmailIDAndSex(userLoc.get(i).getId(), preferedSex, mongoClient))
				{
					
					userByPreferedSex.add(userLoc.get(i));
				}
				 }
				 
				
				 
				
		//return userByPreferedSex;
		return userByPreferedSex;  
	}
	
	public ArrayList<UserLocDetail> checkConditionWithMode(ArrayList<UserLocDetail> userLoc, UserLocDetail uu, MongoClient mongoClient)
	{
		
			
	//String preferedSex="M";
	
		 ArrayList<UserLocDetail> userByPreferedMode = new ArrayList<UserLocDetail>();
				
				 
				
				 
				 for(int i=0;i<userLoc.size();i++)
				 {
					 if(userLoc.get(i).getPreferedMode().equalsIgnoreCase(uu.getPreferedMode()))
					 {
						 userByPreferedMode.add(userLoc.get(i));
					 }
				 } 
				 
				
		//return userByPreferedSex;
		return userByPreferedMode;  
	}
	
	public ArrayList<UserLocDetail> checkConditionWithRating(ArrayList<UserLocDetail> userLoc ,ArrayList<UserDetails> userDetails)
	{ 
		
		int i, j;
  
    
		String[] st = new String[4];
    for (i = 1; i < 5; i++)  	
    { 
    	
    	double max=0;
      
       
        for (j = 0; j < userLoc.size(); j++) 
        {System.out.println("i="+i+"j="+j);
        	System.out.println("max"+max);
        	if(userLoc.get(j).getRating()>max)
        	{
        		if(i==1)
        		{max=userLoc.get(j).getRating();
        		st[0]=userLoc.get(j).getId();}
        		else if (i==2)
        		{
        			System.out.println("st[0]"+st[0]+"userLoc.get(j).getId()"+userLoc.get(j).getId());
        			if(!st[0].equalsIgnoreCase(userLoc.get(j).getId()))
        					{
        				max=userLoc.get(j).getRating();
                		st[1]=userLoc.get(j).getId();
        					}
        		}
        		else if (i==3)
        		{
        			if(!st[0].equalsIgnoreCase(userLoc.get(j).getId())&&!st[1].equalsIgnoreCase(userLoc.get(j).getId()))
        					{
        				max=userLoc.get(j).getRating();
                		st[2]=userLoc.get(j).getId();
        					}
        		}
        		else
        		{
        			if(!st[0].equalsIgnoreCase(userLoc.get(j).getId())&&!st[1].equalsIgnoreCase(userLoc.get(j).getId())&&!st[2].equalsIgnoreCase(userLoc.get(j).getId()))
					{
				max=userLoc.get(j).getRating();
        		st[3]=userLoc.get(j).getId();
					}
        		}
        		
        	}
        }
		
		
	}
ArrayList<UserLocDetail> uuuu= new ArrayList<UserLocDetail>();
    for(int k=0;k<userLoc.size();k++)
    {
    	for(int l=0;l<st.length;l++)
        { System.out.println(l+" : "+st[l]);
    	if(userLoc.get(k).getId().equalsIgnoreCase(st[l]))
    	{
    		uuuu.add(userLoc.get(k));
    	}
    }
    
    }
    return uuuu;
}
	
	
	
	
}
