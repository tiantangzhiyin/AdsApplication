package com.example.adsapplication

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.AudioFocusType
import com.huawei.hms.ads.splash.SplashAdDisplayListener
import com.huawei.hms.ads.splash.SplashView
import com.huawei.hms.ads.splash.SplashView.SplashAdLoadListener

class SplashActivity : AppCompatActivity() {
    private val TAG = "hw_ad_splash"
    private val MSG_AD_TIME_OUT = 1000
    /*
    暂停标志位
    在开屏广告页面展示时：
    --按返回键退出应用时需设置为true，以确保应用主界面不被拉起；
    --切换至其他界面时需设置为false，以确保从其他页面回到开屏广告页面时仍然可以正常跳转至应用主界面；
     */
    private var hasPaused = false

    // 广告展示超时回调处理
    private val timeoutHandler = Handler(Handler.Callback {
        if (hasWindowFocus()) {
            jump()
        }
        false
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 获取并展示开屏广告
        loadAd()
    }

    /**
     * 广告展示完毕时，从广告界面跳转至APP主界面
     */
    private fun jump() {
        if (!hasPaused) {
            hasPaused = true
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    /**
     * 按返回键退出应用时需设置为true，以确保应用主界面不被拉起
     */
    override fun onStop() {
        // 移除消息队列中等待的超时消息
        timeoutHandler.removeMessages(MSG_AD_TIME_OUT)
        hasPaused = true
        super.onStop()
    }

    /**
     * 从其他页面回到开屏页面时调用，进入应用主界面
     */
    override fun onRestart() {
        super.onRestart()
        hasPaused = false
        jump()
    }

    private fun loadAd() {
        // 获取SplashView
        val splashView = findViewById<SplashView>(R.id.splash_ad)
        // 设置默认Slogan(即未获得广告前的占位图片)
        splashView.setSloganResId(R.drawable.ic_launcher_background)
        // 设置视频类开屏广告的音频焦点类型
        splashView.audioFocusType = AudioFocusType.NOT_GAIN_AUDIO_FOCUS_WHEN_MUTE
        // 加载广告
        splashView.load(getString(R.string.hw_ad_splash_id),
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,
            AdParam.Builder().build(),
            loadListener
        )
        // 发送延时消息，保证广告显示超时后，APP首页可以正常显示
        timeoutHandler.removeMessages(MSG_AD_TIME_OUT)
        timeoutHandler.sendEmptyMessageDelayed(MSG_AD_TIME_OUT, 10*1000)
        // 设置广告显示监听
        splashView.setAdDisplayListener(displayListener)
    }

    private val loadListener: SplashAdLoadListener = object : SplashAdLoadListener() {
        // 广告获取失败时调用, 跳转至App主界面
        override fun onAdFailedToLoad(errorCode: Int) {
            Log.d(TAG, "onAdFailedToLoad: ")
            jump()
        }

        // 广告获取成功时调用
        override fun onAdLoaded() {
            Log.d(TAG, "onAdLoaded: ")
        }

        // 广告展示完毕时调用, 跳转至App主界面
        override fun onAdDismissed() {
            Log.d(TAG, "onAdDismissed: ")
            jump()
        }
    }

    private var displayListener: SplashAdDisplayListener = object : SplashAdDisplayListener() {
        // 广告显示时调用
        override fun onAdShowed() {
            Log.d(TAG, "onAdShowed: ")
        }

        // 广告被点击时调用
        override fun onAdClick() {
            Log.d(TAG, "onAdClick: ")
        }
    }
}