package com.appy.reelsblocker.model

import com.appy.reelsblocker.model.consts.AddictiveFeatures
import java.util.Locale

class AddictiveFeaturesStringMatcher {

    fun isAddictiveFeature(
        contentDescription: String?
    ): Boolean {
        val sanitizedContentDescription = contentDescription?.trim()
            ?.uppercase(Locale.ROOT)

        val addictiveFeature = AddictiveFeatures.entries.firstOrNull {
            it.name == sanitizedContentDescription
        }

        return addictiveFeature != null
    }
}