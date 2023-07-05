package com.morecambodia.mcadmob

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.morecambodia.mcadmoblibrary.AdmobService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.banner).setOnClickListener {
            try {
                AdmobService(this).setOnListener { status, adType , type, amount ->
                    if (adType == AdmobService.AdType.AdView) {
                        when (status) {
                            AdmobService.AdStatus.LOADED -> {

                            }

                            else -> {}
                        }
                    }
                }.onStartAdBanner(viewBanner = findViewById(R.id.adBanner))
            } catch (_: Exception) {

            }
        }
        findViewById<Button>(R.id.intershow).setOnClickListener {
            AdmobService(this).setOnListener { status, adType, type, amount->
                if (adType == AdmobService.AdType.InterstitialAd) {
                    when (status) {
                        AdmobService.AdStatus.LOADED -> {

                        }

                        else -> {

                        }
                    }
                } else {

                }
            }.onStartAdInterstitial()
        }
        findViewById<Button>(R.id.reward).setOnClickListener {
            AdmobService(this).setOnListener { status, adType, type, amount ->
                if (adType == AdmobService.AdType.RewardedAd) {
                    when (status) {
                        AdmobService.AdStatus.REWARDED -> {

                        }

                        else -> {

                        }

                    }
                } else {
                }
            }.onStartReward()
        }

    }
}
