package com.S2Location;

import java.util.ArrayList;

public class Response {

	
	String id;
	 ResultSimilarUser[] resultSimilarUsers;
	String responseCode;
	String responseMessage;

	
	public Response(String id, ResultSimilarUser[] resultSimilarUsers, String responseCode, String responseMessage) {
		super();
		this.id = id;
		this.resultSimilarUsers = resultSimilarUsers;
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public ResultSimilarUser[] getResultSimilarUsers() {
		return resultSimilarUsers;
	}


	public void setResultSimilarUsers(ResultSimilarUser[] resultSimilarUsers) {
		this.resultSimilarUsers = resultSimilarUsers;
	}


	public String getResponseCode() {
		return responseCode;
	}


	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}


	public String getResponseMessage() {
		return responseMessage;
	}


	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}


	public Response()
	{}
	
}
