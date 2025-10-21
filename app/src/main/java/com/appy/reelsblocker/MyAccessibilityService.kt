package com.appy.reelsblocker

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
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

        if (isAddictiveFeature) {
            Log.i(
                "onAccessibilityEvent",
                "blocked $contentDescription for $app"
            )
            performGlobalAction(GLOBAL_ACTION_BACK)
            return
        }

        // Check the entire window content for reels/shorts indicators
        val rootNode = rootInActiveWindow
        if (rootNode != null && isInReelsOrShortsView(rootNode, app)) {
            Log.i(
                "onAccessibilityEvent",
                "Detected reels/shorts view for $app, pressing back"
            )
            performGlobalAction(GLOBAL_ACTION_BACK)
            rootNode.recycle()
        }


    }

    private fun isInReelsOrShortsView(node: AccessibilityNodeInfo, app: AddictiveApps): Boolean {
        // Check current node
        val nodeText = node.text?.toString()
        val nodeContentDesc = node.contentDescription?.toString()
        val nodeViewId = node.viewIdResourceName

        if (addictiveFeaturesStringMatcher.isAddictiveFeature(app, nodeContentDesc)) {
            return true
        }

        if (addictiveFeaturesStringMatcher.isAddictiveFeature(app, nodeText)) {
            return true
        }

        // Check for specific view IDs that indicate reels/shorts
        when (app) {
            AddictiveApps.YOUTUBE -> {
                if (nodeViewId?.contains("shorts", ignoreCase = true) == true ||
                    nodeViewId?.contains("reel", ignoreCase = true) == true) {
                    return true
                }
            }
            AddictiveApps.INSTAGRAM -> {
                if (nodeViewId?.contains("clips", ignoreCase = true) == true ||
                    nodeViewId?.contains("reel", ignoreCase = true) == true) {
                    return true
                }
            }
            AddictiveApps.FACEBOOK -> {
                if (nodeViewId?.contains("reel", ignoreCase = true) == true) {
                    return true
                }
            }
        }

        // Recursively check children
        for (i in 0 until node.childCount) {
            val child = node.getChild(i)
            if (child != null) {
                if (isInReelsOrShortsView(child, app)) {
                    child.recycle()
                    return true
                }
                child.recycle()
            }
        }

        return false
    }

    override fun onServiceConnected() {

    }

}