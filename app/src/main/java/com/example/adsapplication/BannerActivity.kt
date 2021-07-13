package com.example.adsapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hms.ads.AdListener
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.BannerAdSize
import com.huawei.hms.ads.banner.BannerView

/**
 * Banner广告
 */
class BannerActivity : AppCompatActivity() {
    private val TAG = "hw_ad_banner"

    lateinit var banner1: BannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)

        // 从布局获取BannerView
        banner1 = findViewById(R.id.hw_banner_view)

        // 编程方式添加BannerView
        val banner2: BannerView = BannerView(this)
        // 设置广告位id
        banner2.adId = getString(R.string.hw_ad_banner_id)
        // 设置广告尺寸（在中国大陆区域暂只支持BANNER_SIZE_360_57和BANNER_SIZE_360_144。）
        banner2.bannerAdSize = BannerAdSize.BANNER_SIZE_360_57
        // 设置广告间隔时间
        banner2.setBannerRefresh(60)
        // 创建广告请求，加载广告
        val adParam: AdParam = AdParam.Builder().build()
        banner2.loadAd(adParam)
        // 监听广告事件
        banner2.adListener = adListener
        // 添加到父布局
        val layout = findViewById<LinearLayout>(R.id.hw_banner_layout)
        layout.addView(banner2)
    }

    fun showBanner(view: View) {
        banner1.adListener = adListener
        val adParam: AdParam = AdParam.Builder().build()
        banner1.loadAd(adParam)
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

        // 广告关闭时调用
        override fun onAdClosed() {
            Log.d(TAG, "onAdClosed: ")
        }

        // 广告点击时调用
        override fun onAdClicked() {
            Log.d(TAG, "onAdClicked: ")
        }

        // 广告离开应用时调用
        override fun onAdLeave() {
            Log.d(TAG, "onAdLeave: ")
        }

        override fun onAdImpression() {
            Log.d(TAG, "onAdImpression: ")
        }
    }
}