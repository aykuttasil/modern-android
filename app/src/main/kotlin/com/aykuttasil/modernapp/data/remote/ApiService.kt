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
package com.aykuttasil.modernapp.data.remote

import androidx.lifecycle.LiveData
import com.aykuttasil.modernapp.data.remote.model.User
import com.aykuttasil.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

  @GET("user")
  fun getUser(): LiveData<ApiResponse<User>>

  @GET("users/{login}")
  fun getUser(@Path("login") login: String): LiveData<ApiResponse<User>>
}
