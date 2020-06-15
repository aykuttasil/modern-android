package com.aykuttasil.modernapp.worker

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aykuttasil.data.remote.ApiService
import com.aykuttasil.domain.repositories.UserRepository

class XWorker @WorkerInject constructor(
  @Assisted private val context: Context,
  @Assisted private val params: WorkerParameters,
  private val userRepository: UserRepository,
  private val apiService: ApiService
) : CoroutineWorker(context, params) {

  override suspend fun doWork(): Result {
    return try {
      Result.success()
    } catch (ex: Exception) {
      ex.printStackTrace()
      Result.retry()
    }
  }
}
