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
package com.aykuttasil.modernapp.util.extension

import android.app.Activity
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.aykuttasil.common.util.extension.getLayoutInflater
import com.aykuttasil.common.util.extension.replaceAll
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Extension method to remove the required boilerplate for running code after a view has been
 * inflated and measured.
 *
 * @author Antonio Leiva
 * @see <a href="https://antonioleiva.com/kotlin-ongloballayoutlistener/>Kotlin recipes: OnGlobalLayoutListener</a>
 */
inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
  viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
    override fun onGlobalLayout() {
      if (measuredWidth > 0 && measuredHeight > 0) {
        viewTreeObserver.removeOnGlobalLayoutListener(this)
        f()
      }
    }
  })
}

/**
 * Extension method to simplify the code needed to apply spans on a specific sub string.
 */
inline fun SpannableStringBuilder.withSpan(
    vararg spans: Any,
    action: SpannableStringBuilder.() -> Unit
):
    SpannableStringBuilder {
  val from = length
  action()

  for (span in spans) {
    setSpan(span, from, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
  }

  return this
}

/**
 * Extension method to replace all text inside an [Editable] with the specified [newValue] while
 * ignoring any [android.text.InputFilter] set on the [Editable].
 */
fun Editable.replaceAllIgnoreFilters(newValue: String) {
  val currentFilters = filters
  filters = emptyArray()
  replaceAll(newValue)
  filters = currentFilters
}

/**
 * Extension method to cast a char with a decimal value to an [Int].
 */
fun Char.decimalValue(): Int {
  if (!isDigit())
    throw IllegalArgumentException("Out of range")
  return this.toInt() - '0'.toInt()
}

/**
 * Extension method to simplify view binding.
 */
fun <T : ViewDataBinding> View.bind() = DataBindingUtil.bind<T>(this) as T

/**
 * Extension method to simplify view inflating and binding inside a [ViewGroup].
 *
 * e.g.
 * This:
 *<code>
 *     binding = bind(R.layout.widget_card)
 *</code>
 *
 * Will replace this:
 *<code>
 *     binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.widget_card, this, true)
 *</code>
 */
fun <T : ViewDataBinding> ViewGroup.bind(layoutId: Int): T {
  return DataBindingUtil.inflate(getLayoutInflater(), layoutId, this, true)
}

fun <T : ViewDataBinding> Activity.bind(layoutId: Int): T {
  return DataBindingUtil.setContentView(this, layoutId)
}

fun String.dateInFormat(format: String): Date? {
  val dateFormat = SimpleDateFormat(format, Locale.US)
  var parsedDate: Date? = null
  try {
    parsedDate = dateFormat.parse(this)
  } catch (ignored: ParseException) {
    ignored.printStackTrace()
  }
  return parsedDate
}

fun getClickableSpan(color: Int, action: (view: View) -> Unit): ClickableSpan {

  return object : ClickableSpan() {
    override fun onClick(view: View) {
      action(view)
    }

    override fun updateDrawState(ds: TextPaint) {
      super.updateDrawState(ds)
      ds.color = color
    }
  }
}

/**
 * Extension method used to return the value of the specified float raised to the power
 * of the specified [exponent].
 */
fun Float.pow(exponent: Float) = Math.pow(this.toDouble(), exponent.toDouble()).toFloat()

/**
 * Provide the ability to snap to a specified [position] in the specified [recyclerView]
 * using [SnapHelper].
 */
fun SnapHelper.snapToPosition(recyclerView: RecyclerView, position: Int) {
  recyclerView.apply {
    val view = findViewHolderForAdapterPosition(position)?.itemView
    val snapPositions = view?.let {
      layoutManager?.let { it1 -> calculateDistanceToFinalSnap(it1, it) }
    }

    snapPositions?.let { smoothScrollBy(it[0], it[1]) }
  }
}

fun <T> T.isNull(): Boolean {
  return this == null
}
