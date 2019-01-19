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
package aykuttasil.com.modernapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import aykuttasil.com.modernapp.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

class FragmentTestActivity : AppCompatActivity(), HasSupportFragmentInjector {
    private lateinit var injector: AndroidInjector<Fragment>

    override fun supportFragmentInjector() = injector

    fun startFragment(fragment: Fragment, injector: AndroidInjector<Fragment>) {
        this.injector = injector

        supportFragmentManager?.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                    super.onFragmentCreated(fm, f, savedInstanceState)
                    if (f is Injectable) {
                        AndroidSupportInjection.inject(f)
                    }
                }
            }, true)

        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, fragment, "TAG")
            .commit()
    }

    inline fun <reified T : Fragment> startFragment(fragment: T, crossinline injector: (T) -> Unit) {
        startFragment(
            fragment,
            AndroidInjector {
                if (it is T) {
                    println("aaa injected")
                    injector(it)
                }
            }
        )
    }
}
