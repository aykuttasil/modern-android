package com.aykuttasil.modernapp.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aykuttasil.data.remote.ApiService
import com.aykuttasil.domain.repositories.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class XWorker @AssistedInject constructor(
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
