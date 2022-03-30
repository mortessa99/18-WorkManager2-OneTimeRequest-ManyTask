package com.example.a18_workmanager2_onetimerequest_manytask

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ShrinkDBScheduler(context: Context, workerParameters: WorkerParameters) :
    Worker(context,workerParameters) {
    override fun doWork(): Result {
        return try {
            for (i in 1..10000){ Log.i("mylog","Backing up data # $i")}
            Result.success()
        } catch (e:Exception){
            Result.failure()
        }
    }
}