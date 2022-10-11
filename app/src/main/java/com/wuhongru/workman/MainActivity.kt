package com.wuhongru.workman

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.wuhongru.workman.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val inputData = Data.Builder().apply {
            putString("wuhongru", "123")
        }.build()

        mBinding.name.text = "123"

        val request = OneTimeWorkRequestBuilder<ChangeImageWorker>()
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setInputData(inputData)
            .build()

        val workManager = WorkManager.getInstance(this)
        workManager.enqueue(request)

        workManager.getWorkInfoByIdLiveData(request.id).observe(this) {
            if (it.state == WorkInfo.State.SUCCEEDED) {
                val out = it.outputData.getString("out")
                mBinding.name.text = out
            }
        }
    }
}
