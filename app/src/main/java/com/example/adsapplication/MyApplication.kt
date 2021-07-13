package com.example.adsapplication

import android.app.Application
import com.huawei.hms.ads.HwAds

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // 初始化huawei ads sdk
        HwAds.init(this)
    }
    
}