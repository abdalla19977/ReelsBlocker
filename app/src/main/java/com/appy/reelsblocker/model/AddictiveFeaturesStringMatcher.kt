package com.appy.reelsblocker.model

import com.appy.reelsblocker.model.consts.AddictiveApps
import com.appy.reelsblocker.model.consts.AddictiveFeatures

class AddictiveFeaturesStringMatcher {

    private val addictiveFeatures = hashMapOf<AddictiveApps, List<AddictiveFeatures>>()

    init {
        addictiveFeatures[AddictiveApps.INSTAGRAM] = arrayListOf(AddictiveFeatures.REEL)
        addictiveFeatures[AddictiveApps.FACEBOOK] = arrayListOf(AddictiveFeatures.REEL)
        addictiveFeatures[AddictiveApps.YOUTUBE] = arrayListOf(AddictiveFeatures.SHORT)
    }

    fun isAddictiveFeature(
        addictiveApps: AddictiveApps,
        contentDescription: String?
    ): Boolean {


        if (contentDescription == null) {
            return false
        }

        val addictiveFeature = addictiveFeatures[addictiveApps]

        val sanitizedContentDescription = contentDescription.trim()

        if (sanitizedContentDescription.contains("_")) {
            // a naive solution to not break instagram
            // as the contentDescription of the home page is "reels_tray_container"
            return false
        }

        val isAddictiveFeature = addictiveFeature?.any {
            it.name.equals(sanitizedContentDescription, ignoreCase = true)
                    || it.name.contains(sanitizedContentDescription, ignoreCase = true)
                    || (sanitizedContentDescription.contains(it.name, ignoreCase = true))

        }

        return isAddictiveFeature ?: false
    }
}