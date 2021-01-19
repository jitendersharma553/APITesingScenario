package com.apitest.apitask.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.apitest.apitask.constants.EndPoints;
import com.apitest.apitask.model.Comments;
import com.apitest.apitask.utils.Commons;
import com.apitest.apitask.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


/**
 * This is Helper Class for Comments on Posts
 * @author Jitender Sharma
 *
 */
public class CommentsHelper {
	private static final String BASE_URL = ConfigManager.getInstance().getString("baseURL");


	public CommentsHelper() {
		RestAssured.baseURI = BASE_URL;
	}
	/**
	 * Method to Get Comments from Single Post
	 * @param PostID
	 * @return Comments[]
	 */
	public Comments[] getCommentsByPostID(String PostID) {
		Comments[] comments =  RestAssured.given().log().body().contentType(ContentType.JSON).get(Commons.msgFormat(EndPoints.GET_COMMENTS_BY_POSTID,PostID)).as(Comments[].class);
		
		return comments;
	}

	/**
	 * Get Emails form Comments on Posts
	 * @param postComments
	 * @return ArrayList emails
	 */
	public  ArrayList<String> getEmailsFromComments(Comments[] postComments) {
		ArrayList<String> emails = new ArrayList<String>();
		for (Comments comments : postComments) {
			emails.add(comments.getEmail());
		}

		return emails;
	}
	
	/**
	 * Get Emails From List of PostID's
	 * @param PostIDs
	 * @return ArrayList
	 */
	public ArrayList<String> getEmailsFromPostsByPostID(List<String>PostIDs) {
		ArrayList<String> emails = new ArrayList<String>();
		for (Iterator iterator = PostIDs.iterator(); iterator.hasNext();) {
			String singlePost = (String) iterator.next();
			Comments[] comments = this.getCommentsByPostID(singlePost);
			emails.addAll(this.getEmailsFromComments(comments));			
		}
		return emails;
	}
	
}
