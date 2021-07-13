package com.example.adsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.huawei.hms.ads.AdListener
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.InterstitialAd

/**
 * 插屏广告
 */
class InterstitialActivity : AppCompatActivity() {
    private val TAG = "hw_ad_interstitial"

    private var interstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial)

        // 创建插屏广告对象
        interstitialAd = InterstitialAd(this)
        // 设置广告位ID
        interstitialAd!!.adId = getString(R.string.hw_ad_interstitial_id)
    }

    fun loadInterstitialAd(view: View) {
        // 获取插屏广告
        val adParam = AdParam.Builder().build()
        interstitialAd!!.loadAd(adParam)
    }

    fun showInterstitialAd(view: View) {
        // 设置广告监听
        interstitialAd!!.adListener = adListener
        // 显示广告
        if (interstitialAd!!.isLoaded){
            interstitialAd!!.show(this)
        }else {
            Toast.makeText(this,"ad did not load", Toast.LENGTH_SHORT).show()
        }
    }

    private val adListener: AdListener = object : AdListener() {
        // 广告获取成功时调用
        override fun onAdLoaded() {
            Log.d(TAG, "onAdLoaded: ")
        }

        // 广告获取失败时调用
        override fun onAdFailed(p0: Int) {
            Log.d(TAG, "onAdFailed: ")
        }

        // 广告打开时调用
        override fun onAdOpened() {
            Log.d(TAG, "onAdOpened: ")
        }

        // 广告点击时调用
        override fun onAdClicked() {
            Log.d(TAG, "onAdClicked: ")
        }

        // 广告离开时调用
        override fun onAdLeave() {
            Log.d(TAG, "onAdLeave: ")
        }

        // 广告曝光时调用
        override fun onAdImpression() {
            Log.d(TAG, "onAdImpression: ")
        }

        // 广告关闭时调用
        override fun onAdClosed() {
            Log.d(TAG, "onAdClosed: ")
        }
    }
}