package com.test.news

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.test.news.features.login.presentation.LoginActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class UserSeeNews {

    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    //Scenario 2 - Failed to load images
    @Test
    fun failedToLoadNews(){
        //Given - the user successfully logged in to the app
        loginToApp()
        // when there is no internet connection
        // Then - “failed to load news” error message is displayed and Retry button
        onView(withId(R.id.textViewError))
            .check(matches(withText("Failed to load news")))
    }
    
    // Login to App
    fun loginToApp(){
        onView(withId(R.id.editTextUserName))
            .perform(clearText(), typeText(LoginToApp.VALID_USER_NAME))
        onView(withId(R.id.editTextPassword))
            .perform(clearText(), typeText(LoginToApp.VALID_USER_PASSWORD))
        onView(withId(R.id.buttonLogin))
            .perform(click())
        onView(withId(R.id.action_bar))
            .check(matches(isDisplayed()))
    }
}