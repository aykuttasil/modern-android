package aykuttasil.com.modernapp.ui.user

import android.content.Context
import aykuttasil.com.modernapp.di.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class UserActivityModule {

    @ActivityContext
    @Provides
    fun providerContext(context: UserActivity): Context {
        return context
    }
}
