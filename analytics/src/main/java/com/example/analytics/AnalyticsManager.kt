
package com.example.analytics

import android.os.Bundle
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import javax.inject.Inject


class AnalyticsManager @Inject constructor(
) : AnalyticsHelper {

    private val firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    override fun logEvent(eventName: String, params: Map<String, String>?) {
        val bundle = Bundle().apply {
            params?.forEach { (key, value) ->
                putString(key, value)
            }
        }
        firebaseAnalytics.logEvent(eventName, bundle)
    }

    override fun setUserId(userId: String?) {
        firebaseAnalytics.setUserId(userId)
    }

    override fun setUserProperty(name: String, value: String?) {
        firebaseAnalytics.setUserProperty(name, value)
    }

    override fun logScreenView(screenName: String, screenClass: String?) {
        logEvent(
            FirebaseAnalytics.Event.SCREEN_VIEW,
            mapOf(
                FirebaseAnalytics.Param.SCREEN_NAME to screenName,
                FirebaseAnalytics.Param.SCREEN_CLASS to (screenClass ?: screenName)
            )
        )
    }

    override fun logLogin(method: String) {
        logEvent(
            FirebaseAnalytics.Event.LOGIN,
            mapOf(FirebaseAnalytics.Param.METHOD to method)

        )
    }

    override fun logSignUp(method: String) {
        logEvent(
            FirebaseAnalytics.Event.SIGN_UP,
            mapOf(FirebaseAnalytics.Param.METHOD to method)
        )
    }

    override fun logSearch(searchTerm: String) {
        logEvent(
            FirebaseAnalytics.Event.SEARCH,
            mapOf(FirebaseAnalytics.Param.SEARCH_TERM to searchTerm)
        )
    }

    override fun logViewItem(contentType: String, itemId: String, itemName: String) {
        logEvent(
            FirebaseAnalytics.Event.VIEW_ITEM,
            mapOf(
                FirebaseAnalytics.Param.CONTENT_TYPE to contentType,
                FirebaseAnalytics.Param.ITEM_ID to itemId,
                FirebaseAnalytics.Param.ITEM_NAME to itemName
            )
        )
    }
}