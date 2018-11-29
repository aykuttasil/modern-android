package aykuttasil.com.modernapp.di

import aykuttasil.com.modernapp.ui.main.MainActivity
import aykuttasil.com.modernapp.ui.main.MainActivityModule
import aykuttasil.com.modernapp.ui.user.UserActivity
import aykuttasil.com.modernapp.ui.user.UserActivityModule
import com.aykuttasil.sweetloc.di.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentBuilder::class])
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    internal abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [(UserActivityModule::class)])
    internal abstract fun bindUserActivity(): UserActivity

}