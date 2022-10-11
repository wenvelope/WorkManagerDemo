package com.wuhongru.workman

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class ChangeImageWorker(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    override fun doWork(): Result {
        val input = inputData.getString("wuhongru")
        if (input != null) {
            val out = Data.Builder()
                .putString("out", input + "成功")
                .build()
            return Result.success(out)
        }
        return Result.failure()
    }
}
