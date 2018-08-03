package aykuttasil.com.modernapp.di.components

import android.app.Application
import aykuttasil.com.modernapp.App
import aykuttasil.com.modernapp.di.ActivityBuilder
import aykuttasil.com.modernapp.di.ServiceBuilder
import aykuttasil.com.modernapp.di.modules.AppModule
import aykuttasil.com.modernapp.di.modules.DatabaseModule
import aykuttasil.com.modernapp.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by aykutasil on 8.12.2017.
 */
@Singleton
@Component(modules = [
    (AndroidSupportInjectionModule::class),
    (ActivityBuilder::class),
    (ServiceBuilder::class),
    (AppModule::class),
    (NetworkModule::class),
    (DatabaseModule::class)])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        // @BindsInstance ile Application nesnesi bekleyen modüllerdeki fonksiyonlara zaten oluşturulmuş Application nesnesini bind ediyoruz
        // Örnek: {@link AppModule.provideSharedPreference}
        // https://proandroiddev.com/dagger-2-component-builder-1f2b91237856
        // Bunu yapmak için modül de constructer olarak beklenen parametreyi (Application) fonksiyona taşımamız gerekli
        @BindsInstance
        fun application(application: Application): Builder

        // Eğer modül constructerında bizden parametre beklemiyor ise Componenti oluştururken(DaggerAppComponent.Builder)
        // bu modülü (AppModule) ayrı olarak tanımlamamıza gerek yok. Ve @BindsInstance kullanarak modül içerisinde aynı tipte (Application)
        // parametre bekleyen fonksiyonlara, zaten oluşturulmuş olan nesneyi daggerın otomatik olarak atama yapmasını sağlarız.
        // fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent

    }

    /*
    // ----> or
    @Component.Builder
    internal abstract inner class Builder : AndroidInjector.Builder<App>()
    */

}
