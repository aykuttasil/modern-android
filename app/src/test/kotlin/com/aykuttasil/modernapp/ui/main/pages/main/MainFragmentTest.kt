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
package com.aykuttasil.modernapp.ui.main.pages.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.aykuttasil.modernapp.R
import com.aykuttasil.modernapp.TestApp
import com.aykuttasil.modernapp.util.ActivityForTest
import com.aykuttasil.common.util.extension.replaceFragment
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
    activityRule.activity.replaceFragment(R.id.testContainer, MainFragment())
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
