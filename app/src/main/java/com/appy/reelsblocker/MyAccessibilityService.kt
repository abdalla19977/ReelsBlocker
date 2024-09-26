package com.appy.reelsblocker

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.appy.reelsblocker.model.AddictiveFeaturesStringMatcher
import com.appy.reelsblocker.model.consts.AddictiveApps


class MyAccessibilityService : AccessibilityService() {

    private val addictiveFeaturesStringMatcher = AddictiveFeaturesStringMatcher()

    override fun onInterrupt() {
        Log.i("onInterrupt", "onInterrupt")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {


        Log.i("onAccessibilityEvent", "action: ${event}")
        Log.i("onAccessibilityEvent", "contentChangeTypes: ${event?.contentChangeTypes}")

        if (event == null) {
            return
        }

        val app = AddictiveApps.entries.firstOrNull { it.packageName == event.packageName }

        if (app == null) {
            return
        }


        val contentDescription = event.contentDescription?.toString()

        val isAddictiveFeature =
            addictiveFeaturesStringMatcher.isAddictiveFeature(app, contentDescription)

        if (!isAddictiveFeature) {
            return
        }

        Log.i(
            "onAccessibilityEvent",
            "blocked $contentDescription for $app"
        )

        performGlobalAction(GLOBAL_ACTION_BACK)


    }

    override fun onServiceConnected() {

    }

}