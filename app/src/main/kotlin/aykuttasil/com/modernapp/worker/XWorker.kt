package aykuttasil.com.modernapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import aykuttasil.com.modernapp.data.remote.ApiService
import aykuttasil.com.modernapp.data.repository.UserRepository
import aykuttasil.com.modernapp.di.ChildWorkerFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class XWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val userRepository: UserRepository,
    private val apiService: ApiService
) : CoroutineWorker(context, params) {

  @AssistedInject.Factory
  interface Factory : ChildWorkerFactory

  override suspend fun doWork(): Result {
    return try {
      Result.success()
    } catch (ex: Exception) {
      ex.printStackTrace()
      Result.retry()
    }
  }
}
