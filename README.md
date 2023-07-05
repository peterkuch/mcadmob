<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://morecambodia.com/" target="_blank">
    <img src="https://morecambodia.com/images/logo.png" alt="Logo" width="300" height="300">
  </a>

  <h3 align="center">Morecamboidda <br> In1 Educaion Game</h3>

  <p align="center">
    README this doc to jumpstart your projects!
    <br />
    <a href="https://github.com/peterkuch/mcadmob"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/peterkuch/mcadmob">View Demo</a>
    ·
    <a href="https://github.com/peterkuch/mcadmob/issues">Report Bug</a>
    ·
    <a href="https://github.com/peterkuch/mcadmob/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->




<!-- ABOUT THE PROJECT -->
## About The Project
<img src="https://github.com/peterkuch/mcadmob/blob/master/screenshot/Screenshot.png"/>

<!-- https://github.com/peterkuch/mcadmob/blob/master/screenshot/Screenshot.png -->
Add our McAdmobLibary to your own project , fast , easy, not take your own implement we are already handle for you.
So what you have to do for required implement admob ad.
* Create account with Admob and Follow  :  <a href="https://apps.admob.com/" target="_blank">
   Regiser Admob
  </a>
* Follow Google Admob Doc : <a href="https://developers.google.com/admob/android/quick-start/" target="_blank">
   Quick-start
  </a> 
* Import McAdmobLibary to your own project and start implement follow our document.

<!-- GETTING STARTED -->
## Getting Started


### Installation

Create your own Anroid Application

1. Add Grandle App Level , Google service dependencies
   ```
   dependencies {
        .....
        implementation 'com.google.android.gms:play-services-ads:22.1.0'
        implementation 'com.google.android.play:core:1.10.3'
   }
   ```
3. Important to use Admob by add your Admob Account ID to your appliation manifest file. you can find from your admob account. Please replace your own Ads account to implement.
   ```
       <application>
            .....
             <meta-data
                android:name="com.google.android.gms.ads.APPLICATION_ID"
                android:value="ca-app-pub-3929444994565000~6715874000" />
       </application>
   ```
4. Download <a href="https://github.com/peterkuch/mcadmob/blob/master/libs/McAdmobLibrary_1_0.aar">McAdmobLibrary_1_0.aar</a> 
  
5. Import McAdmobLibrary_1_0.aar to your project root/libs/
<img src="https://github.com/peterkuch/mcadmob/blob/master/screenshot/lib.png" width="1000"/>

7. Sync McAdmobLibrary_1_0.aar to your project
<img src="https://github.com/peterkuch/mcadmob/blob/master/screenshot/lib1.png" width="1000"/>
<img src="https://github.com/peterkuch/mcadmob/blob/master/screenshot/lib2.png" width="1000"/>
<img src="https://github.com/peterkuch/mcadmob/blob/master/screenshot/lib3.png" width="1000"/>

8. Start implement your project

Create your own Anroid Application
1. Create if you want to implement banner admob before you use our lib , please create AdBanner contaner  where you want to show in your project first, we required view from your app to show Banner Admob.

<img src="https://github.com/peterkuch/mcadmob/blob/master/screenshot/s2.png" width="1000"/>

2. Implement Banner Admob: `parentview your place to show: R.id.adBanner`
```
//Default Ads Debug
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
 //Release Ads for prod add your own adUnitBanner
 AdmobService(this).setOnListener { status, adType, type, amount ->
            if (adType == AdmobService.AdType.AdView) {
                when (status) {
                    AdmobService.AdStatus.LOADED -> {
                        Log.i(tag, "AdView")
                    }

                    else -> {}
                }
            }
        }.onStartAdBanner(viewBanner = findViewById(R.id.adBanner, adUnitBanner = your own adUnitBanner))
   ```
4. Implement InterstitialAd
```
//Default Ads Debug
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
  //Release Ads for prod add your own adUnitInterstitial
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
}.onStartAdInterstitial(your own adUnitInterstitial)
```
5. Implement RewardedAd
```
//Default Ads Debug
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
//Release Ads for prod add your own adUnitRewarded
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
        }.onStartReward(your own adUnitRewarded)
```

2. Final Demo
[![IMAGE ALT TEXT HERE](https://i3.ytimg.com/vi/dYUMxWogWQw/maxresdefault.jpg)](https://www.youtube.com/watch?v=dYUMxWogWQw)


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Kuch Darith - [@kuchdarith][https://www.youtube.com/@kuchdarith] - kuchdarith@gmail.com

Project Link: [https://github.com/peterkuch/mcadmob][https://github.com/peterkuch/mcadmob]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
<p align="right">(<a href="#readme-top">back to top</a>)</p>



