package com.example.a18_workmanager2_onetimerequest_manytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonClick.setOnClickListener { setOneTimeWorkRequest() }
    }


    private fun setOneTimeWorkRequest(){
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val backupRequest = OneTimeWorkRequest.Builder(DbBackupScheduler::class.java).setConstraints(constraint).build()

        val shrinkRequest = OneTimeWorkRequest.Builder(ShrinkDBScheduler::class.java).setConstraints(constraint).build()


        val workManager = WorkManager.getInstance(applicationContext)
        workManager.beginWith(shrinkRequest).then(backupRequest).enqueue()
        workManager.getWorkInfoByIdLiveData(backupRequest.id).observe(this){
            textViewReport.text = textViewReport.text.toString() + it.state.name + "\n"
        }
        //
    }
}

