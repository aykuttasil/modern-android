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

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.aykutasil.modernapp.util.SingleLiveEvent
import com.google.android.material.snackbar.Snackbar

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun View.showSnackbar(snackbarText: String?, timeLength: Int) {
  Snackbar.make(this, snackbarText ?: "Message is null", timeLength).show()
}

/**
 * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
 */
fun View.setupSnackbar(
  lifecycleOwner: LifecycleOwner,
  snackbarMessageLiveEvent: SingleLiveEvent<Int>,
  timeLength: Int
) {
  snackbarMessageLiveEvent.observe(lifecycleOwner, Observer {
    it?.let { showSnackbar(context.getString(it), timeLength) }
  })
}

/**
 * Reloads the data when the pull-to-refresh is triggered.
 *
 * Creates the `android:onRefresh` for a [SwipeRefreshLayout].
 */
// @BindingAdapter("android:onRefresh")
// fun ScrollChildSwipeRefreshLayout.setSwipeRefreshLayoutOnRefreshListener(
//        viewModel: TasksViewModel) {
//    setOnRefreshListener { viewModel.loadTasks(true) }
// }
