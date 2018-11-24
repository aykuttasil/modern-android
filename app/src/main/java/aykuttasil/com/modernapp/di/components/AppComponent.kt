package aykuttasil.com.modernapp.di.components

import android.app.Application
import aykuttasil.com.modernapp.App
import aykuttasil.com.modernapp.di.ActivityBuilder
import aykuttasil.com.modernapp.di.ServiceBuilder
import aykuttasil.com.modernapp.di.modules.ApiModule
import aykuttasil.com.modernapp.di.modules.AppModule
import aykuttasil.com.modernapp.di.modules.DatabaseModule
import com.aykutasil.network.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidSupportInjectionModule::class),
    (ActivityBuilder::class),
    (ServiceBuilder::class),
    (AppModule::class),
    (NetworkModule::class),
    (ApiModule::class),
    (DatabaseModule::class)])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
