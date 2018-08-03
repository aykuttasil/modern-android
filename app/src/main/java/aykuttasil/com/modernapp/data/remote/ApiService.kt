package aykuttasil.com.modernapp.data.remote

import androidx.lifecycle.LiveData
import aykuttasil.com.modernapp.data.remote.model.Repo
import aykuttasil.com.modernapp.data.remote.model.User
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by aykutasil on 20.12.2017.
 */
interface ApiService {

    @GET("user")
    fun getUser(): LiveData<ApiResponse<User>>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): LiveData<ApiResponse<User>>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): LiveData<ApiResponse<List<Repo>>>

    @GET("repos/{owner}/{name}")
    fun getRepo(@Path("owner") owner: String, @Path("name") name: String): LiveData<ApiResponse<Repo>>

    /*
    @GET("repos/{owner}/{name}/contributors")
    fun getContributors(@Path("owner") owner: String, @Path("name") name: String): LiveData<ApiResponse<List<Contributor>>>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): LiveData<ApiResponse<RepoSearchResponse>>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String, @Query("page") page: Int): Call<RepoSearchResponse>
    */
}