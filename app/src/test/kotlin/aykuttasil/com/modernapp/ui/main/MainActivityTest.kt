/**
 * Designed and developed by Aykut Asil (@aykuttasil)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
