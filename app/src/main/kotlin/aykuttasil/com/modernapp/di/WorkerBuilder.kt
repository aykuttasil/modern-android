package aykuttasil.com.modernapp.di

import aykuttasil.com.modernapp.di.key.WorkerKey
import aykuttasil.com.modernapp.worker.XWorker
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
