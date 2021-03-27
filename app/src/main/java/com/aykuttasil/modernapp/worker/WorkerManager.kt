package com.aykuttasil.modernapp.worker

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object WorkerManager {

    fun workX(
        context: Context,
        tag: String,
        data: Data,
        networkType: NetworkType
    ) {
        val constraints = Constraints.Builder().setRequiredNetworkType(networkType).build()

        val work = OneTimeWorkRequestBuilder<XWorker>()
            .setConstraints(constraints)
            .setInputData(data)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 5, TimeUnit.MINUTES)
            .addTag(tag)
            .build()

        WorkManager.getInstance(context).enqueue(work)
    }
}
