package com.apitest.apitask.helpers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.apitest.apitask.constants.EndPoints;
import com.apitest.apitask.model.Posts;
import com.apitest.apitask.model.UserList;
import com.apitest.apitask.utils.Commons;
import com.apitest.apitask.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * Helper class for Posts of Users
 * @author Jitender Sharma
 *
 */
public class UserPostsHelper {
	
	private static final String BASE_URL = ConfigManager.getInstance().getString("baseURL");
	public UserPostsHelper() {
		RestAssured.baseURI = BASE_URL;
	}

	/**
	 * Get Posts of Users By UserID of user
	 * @param UserID
	 * @return List
	 */
	public List<String> getPostsOfUser(String UserID) {

		Posts[] post =  RestAssured.given().log().body().contentType(ContentType.JSON).get(Commons.msgFormat(EndPoints.GET_ALL_POSTS_BY_USERID,UserID)).as(Posts[].class);
		
		List<String> PostIDs = new ArrayList<String>();
		for (int i = 0; i < post.length; i++) {
			PostIDs.add(post[i].getId());
		}
		return PostIDs;
	}


}
