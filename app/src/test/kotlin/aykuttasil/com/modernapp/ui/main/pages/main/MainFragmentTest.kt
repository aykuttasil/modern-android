package aykuttasil.com.modernapp.ui.main.pages.main

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
import aykuttasil.com.modernapp.util.ActivityForTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28], application = TestApp::class)
class MainFragmentTest {

    /*
    @Rule
    var mainActivityActivityTestRule: ActivityTestRule<MainActivity> = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val intent = Intent(InstrumentationRegistry.getContext(), MainActivity::class.java)
            intent.putExtra("Key", "Value")
            return intent
        }
    }
    */

    @get:Rule
    val activityRule = ActivityTestRule(ActivityForTest::class.java)

    @Before
    fun beforeTest() {
        activityRule.activity.supportFragmentManager.beginTransaction().replace(R.id.testContainer, MainFragment()).commit()
    }

    @After
    fun afterTest() {

    }

    @Test
    fun check_fragment_exist() {
        assertTrue(activityRule.activity.supportFragmentManager.fragments.size == 1)
    }

    @Test
    fun check_txt_message() {
        onView(withId(R.id.message)).check(matches(withText("Home")))
    }

    @Test
    fun should_open_activity_when_click_button() {
        onView(withId(R.id.btnGoUserActivity)).check(matches(isEnabled()))
        // onView(withId(R.id.btnGoUserActivity)).perform(click())
    }
}