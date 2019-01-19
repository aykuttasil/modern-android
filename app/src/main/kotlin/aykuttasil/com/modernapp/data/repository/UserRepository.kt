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
package aykuttasil.com.modernapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import aykuttasil.com.modernapp.data.local.dao.UserDao
import aykuttasil.com.modernapp.data.local.entity.UserEntity
import aykuttasil.com.modernapp.data.remote.ApiService
import aykuttasil.com.modernapp.data.remote.model.User
import com.aykutasil.modernapp.Resource
import com.aykutasil.modernapp.util.AppExecutors
import com.aykutasil.network.ApiResponse
import com.aykutasil.network.NetworkBoundResource
import javax.inject.Inject

/**
 * Created by aykutasil on 3.02.2018.
 */
class UserRepository @Inject constructor(val apiService: ApiService, val userDao: UserDao, val appExecutors: AppExecutors) {

    fun getUser(username: String): LiveData<Resource<UserEntity>> {
        return object : NetworkBoundResource<UserEntity, User>(appExecutors) {
            override fun saveCallResult(item: User) {
                val userEntity = UserEntity(_UserName = item.name, UserEmail = item.login, _UserJob = "Developer")
                userDao.insertItem(userEntity)
            }

            override fun shouldFetch(data: UserEntity?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<UserEntity> {
                return userDao.getItem(username)
            }

            override fun createCall(): LiveData<ApiResponse<User>> {
                return Transformations.map(apiService.getUser(username)) {
                    val toplam = (1..1000000).sum()
                    println(toplam)
                    it
                }
            }
        }.asLiveData()
    }
}
