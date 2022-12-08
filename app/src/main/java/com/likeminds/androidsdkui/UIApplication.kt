package com.likeminds.androidsdkui

import android.app.Application
import android.util.Log
import com.collabmates.sdk.LikeMindsCallback
import com.likeminds.likemindschat.InitiateLikeMindsExtra
import com.likeminds.likemindschat.LikeMinds
import com.likeminds.likemindschat.sdk.model.Fonts

class UIApplication : Application(), LikeMindsCallback {

    companion object {
        const val API_KEY = "9be869f3-b9cc-4333-847f-4978d6dc33ae"
        const val DOMAIN = "https://www.sdkui.com"
    }

    override fun onCreate() {
        super.onCreate()

        Log.d("SampleApp", "initiate likeminds called")
        val fonts = Fonts(
            "fonts/montserrat-regular.ttf",
            "fonts/montserrat-medium.ttf",
            "fonts/montserrat-bold.ttf"
        )

        //create extras to be passed
        val extras = InitiateLikeMindsExtra.Builder()
            .application(this)
            .apiKey(API_KEY)
            .notificationIcon(R.drawable.ic_alarm_notification)
            .domain(DOMAIN)
            .fonts(fonts)
            .likeMindsCallback(this)
            .build()

        LikeMinds.initiateLikeMinds(extras)
    }

    override fun loginRequiredCallback() {
        super.loginRequiredCallback()
        Log.d("LikeMindsSDK", "login callback called")
    }

    override fun eventFiredCallback(eventKey: String, propertiesMap: HashMap<String, String?>) {
        super.eventFiredCallback(eventKey, propertiesMap)
        Log.d(
            "LikeMindsSDK", """
            event name:$eventKey
            prop: $propertiesMap
        """.trimIndent()
        )
    }
}