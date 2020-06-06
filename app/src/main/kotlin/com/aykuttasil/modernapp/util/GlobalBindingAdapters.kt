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
package com.aykuttasil.modernapp.util

import android.view.View
import androidx.databinding.BindingAdapter

class GlobalBindingAdapters {

  companion object {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun visibleGone(view: View, show: Boolean) = if (show) {
      view.visibility = View.VISIBLE
    } else {
      view.visibility = View.GONE
    }
  }
}

/*
enum class Shape {
    SQUARE, CIRCLE
}

@BindingAdapter("imageUrl", "placeHolder", "shape", requireAll = false)
fun load(view: ImageView, url: String, placeHolder: Drawable?, shape: Shape?) {
    val requestCreator = Picasso.with(view.context)
            .load(url)
            .placeholder(placeHolder)

    requestCreator.apply {
        when (shape) {
            Shape.CIRCLE -> transform(CircleTransform())
            Shape.SQUARE -> TODO()
        }
    }
    requestCreator.into(view)
}
*/
