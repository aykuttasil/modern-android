package aykuttasil.com.modernapp.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityAndroidTest {

    /*
    val mockUserAction = mock(ViewModelFactory::class.java)

    @get:Rule
    val activityTestRule1 =
        object : ActivityTestRule<MainActivity>(MainActivity::class.java, true, true) {
            override fun beforeActivityLaunched() {
                super.beforeActivityLaunched()
                val myApp =
                    InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App
                myApp.activityDispatchingAndroidInjector =
                    createFakeActivityInjectorX<MainActivity> {
                        viewModelFactory = mockUserAction
                        //userAction = mockUserAction
                    }
            }
        }
    */

    @Test
    fun abc() {
        assertTrue(2 == 2)
    }
}