package aykuttasil.com.modernapp.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import aykuttasil.com.modernapp.App
import aykuttasil.com.modernapp.di.ViewModelFactory
import aykuttasil.com.modernapp.ui.main.MainActivity
import aykuttasil.com.modernapp.ui.main.MainViewModel
import aykuttasil.com.modernapp.util.createFakeActivityInjectorX
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class MainActivityAndroidTest {

    val mockViewModelFactory = mock(ViewModelFactory::class.java)

    @get:Rule
    val activityTestRule1 =
        object : ActivityTestRule<MainActivity>(MainActivity::class.java, true, true) {
            override fun beforeActivityLaunched() {
                super.beforeActivityLaunched()
                val myApp =
                    InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App
                myApp.activityDispatchingAndroidInjector =
                    createFakeActivityInjectorX<MainActivity> {
                        viewModelFactory = mockViewModelFactory
                    }
            }
        }

    @Test
    fun abc1() {
        verify(mockViewModelFactory).create(MainViewModel::class.java)
        assertTrue(2 == 2)
    }

    @Test
    fun abc() {
        assertTrue(2 == 2)
    }
}