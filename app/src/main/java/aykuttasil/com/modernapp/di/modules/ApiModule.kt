package aykuttasil.com.modernapp.di.modules

import aykuttasil.com.modernapp.data.remote.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by aykutasil on 9.12.2017.
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}