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
package aykuttasil.com.modernapp.util.extension

import android.app.Activity
import android.app.Application
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import aykuttasil.com.modernapp.BuildConfig

const val ADD_EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 1
const val DELETE_RESULT_OK = Activity.RESULT_FIRST_USER + 2
const val EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 3

/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, containerId: Int) {
  supportFragmentManager.transact {
    replace(containerId, fragment)
  }
}

/**
 * The `fragment` is added to the container view with tag. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, tag: String) {
  supportFragmentManager.transact {
    add(fragment, tag)
  }
}

fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
  setSupportActionBar(findViewById(toolbarId))
  supportActionBar?.run {
    action()
  }
}

//        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
  beginTransaction().apply {
    action()
  }.commit()
}

inline fun AppCompatActivity.debug(block: () -> Unit) {
  if (BuildConfig.DEBUG) {
    block()
  }
}

inline fun Application.debug(block: () -> Unit) {
  if (BuildConfig.DEBUG) {
    block()
  }
}

fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment) {
  supportFragmentManager.transaction { replace(containerId, fragment) }
}

fun AppCompatActivity.addFragment(containerId: Int, fragment: Fragment) {
  supportFragmentManager.transaction { add(containerId, fragment) }
}
