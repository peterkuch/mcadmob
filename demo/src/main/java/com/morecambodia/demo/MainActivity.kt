package com.morecambodia.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.morecambodia.mcadmoblibrary.AdmobService

class MainActivity : AppCompatActivity() {
    private var tag = "Admob Log"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.banner).setOnClickListener {
            loadBanner()
        }
        findViewById<Button>(R.id.intershow).setOnClickListener {
            loadInterstitialAd()
        }
        findViewById<Button>(R.id.reward).setOnClickListener {
            loadRewardedAd()
        }
    }
    /**
     * Load Admob banner
     */
    private fun loadBanner(){
        AdmobService(this).setOnListener { status, adType, type, amount ->
            if (adType == AdmobService.AdType.AdView) {
                when (status) {
                    AdmobService.AdStatus.LOADED -> {
                        Log.i(tag, "AdView")
                    }

                    else -> {}
                }
            }
        }.onStartAdBanner(viewBanner = findViewById(R.id.adBanner))
    }
    /**
     * Load Admob InterstitialAd
     */
    private fun loadInterstitialAd(){
        AdmobService(this).setOnListener { status, adType, type, amount->
            if (adType == AdmobService.AdType.InterstitialAd) {
                when (status) {
                    AdmobService.AdStatus.LOADED -> {
                        Log.i(tag, "InterstitialAd")
                    }

                    else -> {

                    }
                }
            } else {

            }
        }.onStartAdInterstitial()
    }
    /**
     * Load Admob RewardedAd
     */
    private fun loadRewardedAd(){
        AdmobService(this).setOnListener { status, adType, type, amount ->
            if (adType == AdmobService.AdType.RewardedAd) {
                when (status) {
                    AdmobService.AdStatus.REWARDED -> {
                        Log.i(tag, "REWARDED")
                    }

                    else -> {

                    }

                }
            } else {
            }
        }.onStartReward()
    }
}