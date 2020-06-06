package com.aykuttasil.modernapp.di.modules

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

/**
 * https://github.com/nlgtuankiet/dagger-workmanager
 * https://proandroiddev.com/dagger-2-setup-with-workmanager-a-complete-step-by-step-guild-bb9f474bde37
 * https://gist.github.com/luanmm/85dd8217ed3f7384e6bab075a8ab7a61
 */
@Module(includes = [AssistedInject_WorkerAssistedInjectModule::class])
@AssistedModule
interface WorkerAssistedInjectModule
