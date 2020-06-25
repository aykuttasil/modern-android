package com.aykuttasil.modernapp.util

import android.os.Handler
import android.os.Looper
import com.aykuttasil.domain.util.AppExecutors

import java.util.concurrent.Executor
import java.util.concurrent.Executors

import javax.inject.Inject
import javax.inject.Singleton

class AppExecutorsImpl @Inject constructor() : AppExecutors {

  private class MainThreadExecutor : Executor {
    private val mainThreadHandler = Handler(Looper.getMainLooper())
    override fun execute(command: Runnable) {
      mainThreadHandler.post(command)
    }
  }

  override val diskIO: Executor
    get() = Executors.newSingleThreadExecutor()

  override val networkIO: Executor
    get() = Executors.newFixedThreadPool(3)

  override val mainThread: Executor
    get() = MainThreadExecutor()

}