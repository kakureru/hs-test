package com.hammersystemstest.core

import android.util.Log
import kotlin.coroutines.cancellation.CancellationException

inline fun <R> runCatchingNonCancellation(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Log.e("EXCEPTION", "Exception", e)
        Result.failure(e)
    }
}