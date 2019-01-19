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
package aykuttasil.com.modernapp.util.delegates

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import android.view.ViewGroup
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Inflate<in R : Fragment, out T : ViewDataBinding>(@LayoutRes private val layoutRes: Int) : ReadOnlyProperty<R, T> {

    private var binding: T? = null

    override fun getValue(thisRef: R, property: KProperty<*>): T {
        if (binding == null) {
            val inflater = thisRef.layoutInflater
            val container = thisRef.view as ViewGroup?
            binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        }
        return binding!!
    }
}

fun <R : Fragment, T : ViewDataBinding> inflate(@LayoutRes layoutRes: Int): Inflate<R, T> {
    return Inflate(layoutRes)
}
