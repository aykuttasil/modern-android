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

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber

inline fun <reified T> printEvent(tag: String, success: T?, error: Throwable?) =
    when {
      success == null && error == null -> Timber.d("Complete")
      success != null -> Timber.d("Success $success")
      error != null -> Timber.d("Error $error")
      else -> -1 /* Cannot happen*/
    }

inline fun printEvent(tag: String, error: Throwable?) =
    when {
      error != null -> Timber.d("Error $error")
      else -> Timber.d("Complete")
    }

/**
 * Example usage of [log]:
Single.timer(1, TimeUnit.SECONDS)
.log()
.subscribe({ }, { })
 */

inline fun tag() = Thread.currentThread().stackTrace
    .first { it.fileName.endsWith(".kt") }
    .let { stack -> "${stack.fileName.removeSuffix(".kt")}::${stack.methodName}:${stack.lineNumber}" }

inline fun <reified T> Single<T>.log(): Single<T> {
  val tag = tag()
  return doOnEvent { success, error -> printEvent(tag, success, error) }
      .doOnSubscribe { Timber.d("Subscribe") }
      .doOnDispose { Timber.d("Dispose") }
}

inline fun <reified T> Maybe<T>.log(): Maybe<T> {
  val tag = tag()
  return doOnEvent { success, error -> printEvent(tag, success, error) }
      .doOnSubscribe { Timber.d("Subscribe") }
      .doOnDispose { Timber.d("Dispose") }
}

inline fun Completable.log(): Completable {
  val tag = tag()
  return doOnEvent { printEvent(tag, it) }
      .doOnSubscribe { Timber.d("Subscribe") }
      .doOnDispose { Timber.d("Dispose") }
}

inline fun <reified T> Observable<T>.log(): Observable<T> {
  val line = tag()
  return doOnEach { Timber.d("Each $it") }
      .doOnSubscribe { Timber.d("Subscribe") }
      .doOnDispose { Timber.d("Dispose") }
}

inline fun <reified T> Flowable<T>.log(): Flowable<T> {
  val line = tag()
  return doOnEach { Timber.d("Each $it") }
      .doOnSubscribe { Timber.d("Subscribe") }
      .doOnCancel { Timber.d("Cancel") }
}
