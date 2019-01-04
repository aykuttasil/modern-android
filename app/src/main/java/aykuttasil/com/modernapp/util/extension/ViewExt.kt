package aykuttasil.com.modernapp.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import android.view.View
import com.aykutasil.common.util.SingleLiveEvent

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