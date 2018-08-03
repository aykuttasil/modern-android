package aykuttasil.com.modernapp.di

import aykuttasil.com.modernapp.di.scopes.PerService
import aykuttasil.com.modernapp.service.AppService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBuilder {

    @PerService
    @ContributesAndroidInjector
    internal abstract fun bindAppService(): AppService

}