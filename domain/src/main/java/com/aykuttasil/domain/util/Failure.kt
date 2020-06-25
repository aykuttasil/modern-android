package com.aykuttasil.domain.util

/*
/**
 * Injectable class which returns information about the network connection state.
 */
/*
@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) {
  val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}

 */

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
  data class NetworkConnection(val errorMessage: String? = null) : Failure()
  object ServerError : Failure()

  /** * Extend this class for feature specific failures.*/
  abstract class FeatureFailure : Failure()
}

 */