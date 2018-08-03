package aykuttasil.com.modernapp.data.repository

import androidx.lifecycle.LiveData
import aykuttasil.com.modernapp.data.Resource
import aykuttasil.com.modernapp.data.remote.ApiResponse
import aykuttasil.com.modernapp.data.remote.ApiService
import aykuttasil.com.modernapp.data.remote.NetworkBoundResource
import aykuttasil.com.modernapp.data.remote.model.Repo
import javax.inject.Inject

/**
 * Created by aykutasil on 1.03.2018.
 */
class RepoRepository @Inject constructor(val apiService: ApiService) {

    fun getUserRepos(user: String): LiveData<Resource<Repo>> {
        return object : NetworkBoundResource<Repo, Repo>() {
            override fun saveCallResult(item: Repo) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun shouldFetch(data: Repo?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loadFromDb(): LiveData<Repo> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun createCall(): LiveData<ApiResponse<Repo>> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        }.asLiveData()
    }

}