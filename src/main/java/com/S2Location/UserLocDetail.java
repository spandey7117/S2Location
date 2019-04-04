package com.S2Location;

import com.google.common.geometry.S2Point;

public class UserLocDetail {
S2Point startLocation;
S2Point endLocation;
String id;
String status;
double startLongitude;
double startLatitude;
double endLongitude;
double endLatitude;
String time;
String preferedMode;
String preferedSex;
double rating;
public double getRating() {
	return rating;
}
public void setRating(double rating) {
	this.rating = rating;
}
public UserLocDetail(S2Point startLocation, S2Point endLocation, String id, String status, double startLongitude,
		double startLatitude, double endLongitude, double endLatitude,String time, String preferedMode ,String preferedSex, double rating) {
	super();
	this.startLocation = startLocation;
	this.endLocation = endLocation;
	this.id = id;
	this.status = status;
	this.startLongitude = startLongitude;
	this.startLatitude = startLatitude;
	this.endLongitude = endLongitude;
	this.endLatitude = endLatitude;
	this.time = time;
	this.preferedMode = preferedMode;
	this.preferedSex = preferedSex;
	this.rating=rating;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getPreferedMode() {
	return preferedMode;
}
public void setPreferedMode(String preferedMode) {
	this.preferedMode = preferedMode;
}
public String getPreferedSex() {
	return preferedSex;
}
public void setPreferedSex(String preferedSex) {
	this.preferedSex = preferedSex;
}
public S2Point getStartLocation() {
	return startLocation;
}
public void setStartLocation(S2Point startLocation) {
	this.startLocation = startLocation;
}
public S2Point getEndLocation() {
	return endLocation;
}
public void setEndLocation(S2Point endLocation) {
	this.endLocation = endLocation;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public double getStartLongitude() {
	return startLongitude;
}
public void setStartLongitude(double startLongitude) {
	this.startLongitude = startLongitude;
}
public double getStartLatitude() {
	return startLatitude;
}
public void setStartLatitude(double startLatitude) {
	this.startLatitude = startLatitude;
}
public double getEndLongitude() {
	return endLongitude;
}
public void setEndLongitude(double endLongitude) {
	this.endLongitude = endLongitude;
}
public double getEndLatitude() {
	return endLatitude;
}
public void setEndLatitude(double endLatitude) {
	this.endLatitude = endLatitude;
}

}