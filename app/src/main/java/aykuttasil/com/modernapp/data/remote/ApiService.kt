package aykuttasil.com.modernapp.data.remote

import androidx.lifecycle.LiveData
import aykuttasil.com.modernapp.data.remote.model.Repo
import aykuttasil.com.modernapp.data.remote.model.User
import com.aykutasil.network.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("user")
    fun getUser(): LiveData<ApiResponse<User>>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): LiveData<ApiResponse<User>>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): LiveData<ApiResponse<List<Repo>>>

    @GET("repos/{owner}/{name}")
    fun getRepo(@Path("owner") owner: String, @Path("name") name: String): LiveData<ApiResponse<Repo>>

}