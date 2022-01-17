package com.test.news

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.test.news.features.login.presentation.LoginActivity
import org.junit.Rule
import org.junit.Test


class LoginToApp {
    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    //Scenario 1 - user opens the android app first time (when not logged in yet)
    @Test
    fun shouldDisplayUsernamePassword(){
        // Given - when user opens app for the first time (when not logged in yet)
        // Then - login screen with user name and password entries and login button is displayed
        onView(withId(R.id.editTextUserName))
            .check(matches(isDisplayed()))
        onView(withId(R.id.editTextPassword))
            .check(matches(isDisplayed()))
        onView(withId(R.id.buttonLogin))
            .check(matches(isDisplayed()))
    }

    //Scenario 2 - user login failed
    @Test
    fun loginFailed(){
        // Given - the user provided wrong user name and/or password
        onView(withId(R.id.editTextUserName))
            .perform(clearText(), typeText("wrongname"))
        onView(withId(R.id.editTextPassword))
            .perform(clearText(), typeText(VALID_USER_PASSWORD))
        // When - login button is clicked
        onView(withId(R.id.buttonLogin))
            .perform(click())
        // Then - error markers are displayed by user name and/or password entries
        onView(withId(R.id.editTextUserName))
            .check(matches(hasErrorText("Wrong user name")));
    }

    //Scenario 3 - User login succeed
    @Test
    fun loginSucceed(){
        // Given - the user provided right user name and password
        onView(withId(R.id.editTextUserName))
            .perform(clearText(), typeText(VALID_USER_NAME))
        onView(withId(R.id.editTextPassword))
            .perform(clearText(), typeText(VALID_USER_PASSWORD))
        // When - login button is clicked
        onView(withId(R.id.buttonLogin))
            .perform(click())
        // Then - user is taken to the news screen
        onView(withId(R.id.action_bar))
            .check(matches(isDisplayed()))
    }

    companion object {
        const val VALID_USER_NAME = "user1"
        const val VALID_USER_PASSWORD = "password"
    }

}