package aykuttasil.com.modernapp.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import aykuttasil.com.modernapp.R
import aykuttasil.com.modernapp.TestApp
import aykuttasil.com.modernapp.ui.main.pages.main.MainFragment
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28], application = TestApp::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun beforeTest() {
        activityRule.activity.supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment())
    }

    @After
    fun afterTest() {

    }

    @Test
    fun should_show_hello_world() {
        onView(withId(R.id.hello)).check(matches(withText("Hello World!")))
    }

    @Test
    fun check_txt_message() {
        onView(withId(R.id.message)).check(matches(withText("Home")))
    }

    @Test
    fun should_open_activity_when_click_button() {
        onView(withId(R.id.btnGoUserActivity)).check(matches(isEnabled()))
        onView(withId(R.id.btnGoUserActivity)).perform(click())
        // onView(withId(R.id.textView)).check(matches(isDisplayed()))
        onView(withId(R.id.textView)).check(matches(withText("About My App")))
    }

}