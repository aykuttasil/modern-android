package com.aykuttasil.modernapp.di.modules

import com.aykuttasil.domain.util.AppExecutors
import com.aykuttasil.domain.util.DispatcherProvider
import com.aykuttasil.modernapp.util.AppExecutorsImpl
import com.aykuttasil.modernapp.util.DispatcherProviderImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ImplModule {

    @Singleton
    @Binds
    abstract fun provideDispatcherProvider(
        dispatcherProvider: DispatcherProviderImp
    ): DispatcherProvider

    @Singleton
    @Binds
    abstract fun provideAppExecutors(
        executors: AppExecutorsImpl
    ): AppExecutors

}