package com.aykuttasil.data.di

import android.content.Context
import com.aykuttasil.data.BuildConfig
import com.aykuttasil.data.util.InternetConnection.isConnectionOn
import com.aykuttasil.data.util.InternetConnection.isInternetAvailable
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun getBaseUrl() = "https://api.github.com"

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            // .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckInterceptor: ChuckInterceptor,
        stethoInterceptor: StethoInterceptor
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()

        httpClientBuilder.addInterceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                .addHeader("Content-Type", "application/json")
                .method(original.method, original.body)
                .build()

            return@addInterceptor chain.proceed(request)
        }

        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        httpClientBuilder.addInterceptor(NoConnectionInterceptor(context))

        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(httpLoggingInterceptor)
            httpClientBuilder.addInterceptor(chuckInterceptor)
            httpClientBuilder.addNetworkInterceptor(stethoInterceptor)
        }
        return httpClientBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    internal fun provideChuckInterceptor(@ApplicationContext context: Context): ChuckInterceptor {
        return ChuckInterceptor(context)
    }

    @Provides
    @Singleton
    internal fun provideStetho(): StethoInterceptor {
        return StethoInterceptor()
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }
}


class NoConnectivityException : IOException() {
    override val message: String
        get() =
            "No network available, please check your WiFi or Data connection"
}

class NoInternetException : IOException() {
    override val message: String
        get() =
            "No internet available, please check your connected WIFi or Data"
}


class NoConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isConnectionOn(context)) {
            throw NoConnectivityException()
        } else if (!isInternetAvailable()) {
            throw NoInternetException()
        } else {
            chain.proceed(chain.request())
        }
    }
}