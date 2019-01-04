package aykuttasil.com.modernapp.data.repository

import androidx.lifecycle.LiveData
import aykuttasil.com.modernapp.data.remote.ApiService
import aykuttasil.com.modernapp.data.remote.model.Repo
import com.aykutasil.common.Resource
import com.aykutasil.common.util.AppExecutors
import com.aykutasil.network.ApiResponse
import com.aykutasil.network.NetworkBoundResource
import javax.inject.Inject

/**
 * Created by aykutasil on 1.03.2018.
 */
class RepoRepository @Inject constructor(val apiService: ApiService, val appExecutors: AppExecutors) {

    fun getUserRepos(user: String): LiveData<Resource<Repo>> {
        return object : NetworkBoundResource<Repo, Repo>(appExecutors) {
            override fun saveCallResult(item: Repo) {
                TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
            }

            override fun shouldFetch(data: Repo?): Boolean {
                TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
            }

            override fun loadFromDb(): LiveData<Repo> {
                TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
            }

            override fun createCall(): LiveData<ApiResponse<Repo>> {
                TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
            }
        }.asLiveData()
    }
}