package com.example.adsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.reward.Reward
import com.huawei.hms.ads.reward.RewardAd
import com.huawei.hms.ads.reward.RewardAdLoadListener
import com.huawei.hms.ads.reward.RewardAdStatusListener

/**
 * 激励广告
 * https://developer.huawei.com/consumer/cn/doc/development/HMSCore-Guides-V5/publisher-service-reward-0000001050066917-V5
 * 此外激励广告有校验服务端验证回调功能，可以向媒体服务器查询进行验证，本示例没用到，如需使用请参考官方开发文档
 */
class RewardActivity : AppCompatActivity() {
    private val TAG: String = "hw_ad_reward"

    private var rewardAd: RewardAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward)

    }

    fun loadRewardAd(view: View) {
        // 创建激励广告对象
        rewardAd = RewardAd(this,getString(R.string.hw_ad_reward_id))
        // 加载激励广告监听
        val listener: RewardAdLoadListener = object : RewardAdLoadListener() {
            // 激励广告加载成功
            override fun onRewardedLoaded() {
                Log.d(TAG, "onRewardedLoaded: ")
            }

            // 激励广告加载失败
            override fun onRewardAdFailedToLoad(p0: Int) {
                Log.d(TAG, "onRewardAdFailedToLoad: ")
            }
        }
        // 加载激励广告
        rewardAd!!.loadAd(AdParam.Builder().build(),listener)
    }

    fun showRewardAd(view: View) {
        // 显示广告前需要确认是否加载完成
        if (rewardAd!!.isLoaded) {
            rewardAd!!.show(this, object : RewardAdStatusListener() {
                // 激励广告展示失败
                override fun onRewardAdFailedToShow(p0: Int) {
                    Log.d(TAG, "onRewardAdFailedToShow: ")
                }

                // 激励广告被打开
                override fun onRewardAdOpened() {
                    Log.d(TAG, "onRewardAdOpened: ")
                }

                // 激励广告奖励达成，发放奖励
                override fun onRewarded(p0: Reward?) {
                    Log.d(TAG, "onRewarded: ")
                }

                // 激励广告被关闭
                override fun onRewardAdClosed() {
                    Log.d(TAG, "onRewardAdClosed: ")
                }
            })
        }
    }
}