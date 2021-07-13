package com.example.adsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toBanner(view: View) {
        startActivity(Intent(this,BannerActivity::class.java))
    }

    fun toNative(view: View) {

    }

    fun toReward(view: View) {
        startActivity(Intent(this,RewardActivity::class.java))
    }

    fun toInterstitial(view: View) {
        startActivity(Intent(this,InterstitialActivity::class.java))
    }

    fun toSplash(view: View) {
        startActivity(Intent(this,SplashActivity::class.java))
    }
}