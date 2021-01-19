package com.apitest.apitask.helpers;

import com.apitest.apitask.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import com.apitest.apitask.constants.EndPoints;
import com.apitest.apitask.model.*;

public class UsersHelper {
	
	private static final String BASE_URL = ConfigManager.getInstance().getString("baseURL");
	
	public UsersHelper() {
		RestAssured.baseURI = BASE_URL;
	}
	
	/**
	 * Get Status Code of the Users EndPoint
	 * @return int
	 */
	public int getStatusCode() {
		int code = RestAssured.given().log().body().contentType(ContentType.JSON).get(EndPoints.GET_ALL_USERS).getStatusCode();
		return code;
	}
	
	/**
	 * Get List of All Users from Users Endpoint
	 * @return
	 */
	public UserList[] getAllUser(){
	
		UserList[] users = RestAssured.given().log().body().contentType(ContentType.JSON).get(EndPoints.GET_ALL_USERS).as(UserList[].class);
		return users;		
	}
	
	/**
	 * Get User ID from username of specific user
	 * @param userName
	 * @return String userID
	 */
	public String getUserID(String userName) {
		UserList[] users = RestAssured.given().log().body().contentType(ContentType.JSON).get(EndPoints.GET_ALL_USERS).as(UserList[].class);
		for (int i = 0; i < users.length; i++) {
			UserList userList = users[i];
			if (userList.getUsername().contains(userName))
				return userList.getId();
			}
		return "No User Found!!";
	}
	
}
