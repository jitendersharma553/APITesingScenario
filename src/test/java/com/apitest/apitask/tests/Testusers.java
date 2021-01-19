package com.apitest.apitask.tests;

import java.util.Iterator;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.apitest.apitask.helpers.CommentsHelper;
import com.apitest.apitask.helpers.UserPostsHelper;
import com.apitest.apitask.helpers.UsersHelper;
import com.apitest.apitask.utils.Commons;

import io.qameta.allure.Description;

public class Testusers {

	private UsersHelper userHelper;
	private UserPostsHelper userPostsHelper;
	private CommentsHelper commentsHelper;

	@BeforeClass
	public void init() {
		userHelper = new UsersHelper();
		userPostsHelper = new UserPostsHelper();
		commentsHelper = new CommentsHelper();
	}
	
	
	@Test
	@Description("Test to check the Users Endpoint status")
	public void checkUsersAPIStatus() {
		Assert.assertEquals(userHelper.getStatusCode(), 200, "Users API is Working");
	}

	
	@Test
	@Description("Test for Checking Presence of Emails.")
	public void testEmailsArePresent() {
		String userID = userHelper.getUserID("Samantha");
		List<String> PostIDs = userPostsHelper.getPostsOfUser(userID);
		List<String> emails = commentsHelper.getEmailsFromPostsByPostID(PostIDs);
		
		Assert.assertNotNull(emails, "Emails are not Null");
		Assert.assertFalse(emails.isEmpty(),"Emails are present in Posts");
	}
	
	
	@Test
	@Description("Test for Checking the Format of Emails.")
	public void testEmailsFormat() {
		String userID = userHelper.getUserID("Samantha");
		List<String> PostIDs = userPostsHelper.getPostsOfUser(userID);
		List<String> emails = commentsHelper.getEmailsFromPostsByPostID(PostIDs);
		for (Iterator iterator = emails.iterator(); iterator.hasNext();) {
			String email = (String) iterator.next();
			Assert.assertTrue(Commons.isValidEmailAddress(email), "Email Format is incorrect");
		} 	
	}

}
