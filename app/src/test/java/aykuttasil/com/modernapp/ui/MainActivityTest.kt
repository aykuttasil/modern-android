package aykuttasil.com.modernapp.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import aykuttasil.com.modernapp.TestApp
import aykuttasil.com.modernapp.ui.main.MainActivity
import org.junit.After
import org.junit.Assert.assertTrue
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

    }

    @After
    fun afterTest() {

    }

    @Test
    fun x() {
        assertTrue(2 == 2)
    }

}