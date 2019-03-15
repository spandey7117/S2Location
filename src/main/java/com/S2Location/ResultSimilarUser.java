package com.S2Location;

public class ResultSimilarUser {
String id;
double startLongitude;
double startLatitude;
double endLongitude;
double endLatitude;
public ResultSimilarUser(String id, double startLongitude, double startLatitude, double endLongitude,
		double endLatitude) {
	super();
	this.id = id;
	this.startLongitude = startLongitude;
	this.startLatitude = startLatitude;
	this.endLongitude = endLongitude;
	this.endLatitude = endLatitude;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
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
