package com.aykuttasil.modernapp.di

import com.aykuttasil.modernapp.di.key.WorkerKey
import com.aykuttasil.modernapp.worker.XWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerBuilder {

  @Binds
  @IntoMap
  @WorkerKey(XWorker::class)
  fun bindXWorker(factory: XWorker.Factory): ChildWorkerFactory
}
