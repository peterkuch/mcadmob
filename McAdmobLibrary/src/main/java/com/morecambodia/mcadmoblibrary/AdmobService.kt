package com.morecambodia.mcadmoblibrary

import android.app.Activity
import android.content.Context
import android.widget.LinearLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

/**
 * The Admob class for integrate with other application.
 * free open sources
 *
 * @author  Kuch Darith
 * @version 1.0
 * @since   2013-06-22
 */
class AdmobService constructor(context: Context) {
    private lateinit var mAdView: AdView
    private var mContext: Context
    private var mInterstitialAd: InterstitialAd? = null
    private var mRewardedAd: RewardedAd? = null
    private var mAdListener: ((AdStatus, Any, String, Int) -> Unit)? = null

    enum class AdStatus {
        LOADED,
        FAILED,
        REWARDED
    }
    enum class AdType{
        InterstitialAd,
        RewardedAd,
        AdView
    }
    init {
        mContext = context
    }

    companion object : SingletonHolder<AdmobService, Context>(::AdmobService)

    fun setOnListener(listener: ((AdStatus, Any, String, Int) -> Unit)?): AdmobService {
        mAdListener = listener
        return this
    }

    /**
     *  onStartReward
     *  @param adUnitRewarded type String, default testing if not set
     *
     */
    fun onStartReward(adUnitRewarded: String = "ca-app-pub-3940256099942544/5224354917") {
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(
            mContext,
            adUnitRewarded,
            adRequest,
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mRewardedAd = null
                    mAdListener?.invoke(AdStatus.FAILED, adError, "", -1)
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    mRewardedAd = rewardedAd
                    mRewardedAd?.show(
                        mContext as Activity
                    ) {
                        val rewardAmount = it.amount
                        val rewardType = it.type
                        mAdListener?.invoke(AdStatus.REWARDED, AdType.RewardedAd, rewardType, rewardAmount)
                    }
                }
            })
    }

    /**
     * onStartAdInterstitial
     * @param adUnitInterstitial type String, default testing if not set
     */
    fun onStartAdInterstitial(adUnitInterstitial: String = "ca-app-pub-3940256099942544/1033173712") {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(mContext, adUnitInterstitial,
            adRequest, object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                    mAdListener?.invoke(AdStatus.FAILED, adError, "", -1)
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    mInterstitialAd?.apply {
                        mAdListener?.invoke(AdStatus.LOADED, AdType.InterstitialAd, "", -1)
                    }
                    if (mContext is Activity) {
                        mInterstitialAd?.show(mContext as Activity)
                    }
                }
            })
    }

    /**
     * onStartAdBanner
     * @param
     */
    fun onStartAdBanner(adUnitBanner: String = "ca-app-pub-3940256099942544/6300978111",
                        viewBanner: LinearLayout?) {
        mAdView = AdView(mContext)
        val adRequest = AdRequest.Builder().build()
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = adUnitBanner
        mAdView.loadAd(adRequest)
        mAdView.adListener = object : AdListener() {

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
                mAdListener?.invoke(AdStatus.FAILED, AdType.AdView, "", -1)
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                mAdListener?.invoke(AdStatus.LOADED, AdType.AdView,"", -1)
                viewBanner?.removeAllViews()
                viewBanner?.addView(mAdView)
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }
       // return mAdView
    }
}

open class SingletonHolder<out T : Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}