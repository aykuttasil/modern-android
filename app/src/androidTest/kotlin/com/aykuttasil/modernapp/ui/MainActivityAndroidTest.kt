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
package com.aykuttasil.modernapp.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.aykuttasil.modernapp.ui.main.MainActivity
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityAndroidTest {

    @get:Rule
    val activityTestRule1 = ActivityTestRule<MainActivity>(MainActivity::class.java)
    /*
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
        */

    @Test
    fun abc1() {
        //verify(mockViewModelFactory).create(MainViewModel::class.java)
        assertTrue(2 == 2)
    }

    @Test
    fun abc() {
        assertTrue(2 == 2)
    }
}
