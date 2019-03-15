package com.S2Location;

import com.google.common.geometry.S1Angle;
import com.google.common.geometry.S2Cap;
import com.google.common.geometry.S2Cell;
import com.google.common.geometry.S2CellUnion;
import com.google.common.geometry.S2LatLng;
import com.google.common.geometry.S2LatLngRect;
import com.google.common.geometry.S2Point;
import com.google.common.geometry.S2Region;
import com.google.common.geometry.S2RegionCoverer;

public class CreateRegion  {

	
	private S2RegionCoverer s2RegionCoverer;

	public S2CellUnion S2CellIdsCoveringGivenRadius(int radius, String Latitude, String Longitude)
	{
		S2CellUnion s2CellUnion= null;
		
		S2LatLng s2LatLng= S2LatLng.fromDegrees( Double.parseDouble(Latitude),  Double.parseDouble(Longitude));
		S2Point s2Point =s2LatLng.toPoint();
		S1Angle s1Angle= S1Angle.degrees(radius/S2LatLng.EARTH_RADIUS_METERS);
		S2Cap s2Cap=S2Cap.fromAxisAngle(s2Point, s1Angle);
		//S2LatLngRect s2LatLngRect =S2LatLngRect.(s2Cap);
		S2Region s2Region= s2Cap.getCapBound();
		 s2RegionCoverer= new S2RegionCoverer();
		s2RegionCoverer.setMaxLevel(22);
		s2RegionCoverer.setMinLevel(15);
		s2RegionCoverer.setMaxCells(500);
		s2CellUnion=s2RegionCoverer.getCovering(s2Region);
		
		return s2CellUnion;
	}

	
}
