package com.appy.reelsblocker

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityEvent.TYPE_VIEW_CLICKED


class MyAccessibilityService : AccessibilityService() {

    override fun onInterrupt() {
        Log.i("onInterrupt", "onInterrupt")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        Log.i("onAccessibilityEvent", "action: ${event}")
        Log.i("onAccessibilityEvent", "contentChangeTypes: ${event?.contentChangeTypes}")
//youtube
        if (event?.text?.firstOrNull()?.trim().toString() in "Shorts") {
            Log.i("onAccessibilityEvent", "onAccessibilityEvent Shorts")
            performGlobalAction(GLOBAL_ACTION_BACK)
        }
        // instagram
        if (event?.eventType == TYPE_VIEW_CLICKED && event.contentDescription.toString().trim() == "Reels") {
            Log.i("onAccessibilityEvent", "onAccessibilityEvent Shorts")
            performGlobalAction(GLOBAL_ACTION_BACK)
        }
// facebook
            if (event?.eventType == TYPE_VIEW_CLICKED && event.contentDescription.toString().trim() == "View reels") {
            Log.i("onAccessibilityEvent", "onAccessibilityEvent Shorts")
            performGlobalAction(GLOBAL_ACTION_BACK)
        }




    }

    override fun onServiceConnected() {

    }

}