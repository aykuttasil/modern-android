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
package com.aykutasil.modernapp.util.extension

import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.view.ViewTreeObserver
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
inline fun SpannableStringBuilder.withSpan(vararg spans: Any, action: SpannableStringBuilder.() -> Unit):
    SpannableStringBuilder {
  val from = length
  action()

  for (span in spans) {
    setSpan(span, from, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
  }

  return this
}


/**
 * Extension method to int time to 2 digit String
 */
fun Int.twoDigitTime() = if (this < 10) "0" + toString() else toString()

/**
 * Extension method to replace all text inside an [Editable] with the specified [newValue].
 */
fun Editable.replaceAll(newValue: String) {
  replace(0, length, newValue)
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
 * Convert a [Boolean] value to a view visibility [Int].
 */
fun Boolean.toViewVisibility(valueForFalse: Int = View.GONE): Int {
  return if (this) {
    View.VISIBLE
  } else {
    valueForFalse
  }
}
