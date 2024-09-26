package com.appy.reelsblocker.model

import com.appy.reelsblocker.model.consts.AddictiveApps
import org.junit.Test

class AddictiveFeaturesStringMatcherTest {

    val stringMatcher = AddictiveFeaturesStringMatcher()


    @Test
    fun `Youtube video return false`() {
        val app = AddictiveApps.YOUTUBE
        val contentDescription = "video"

        val isAddictiveFeature = stringMatcher.isAddictiveFeature(app, contentDescription)

        assert(!isAddictiveFeature)
    }

    @Test
    fun `Youtube short return true`() {
        val app = AddictiveApps.YOUTUBE
        val contentDescription = "short"

        val isAddictiveFeature = stringMatcher.isAddictiveFeature(app, contentDescription)

        assert(isAddictiveFeature)
    }

    @Test
    fun `Youtube SHORT return true`() {
        val app = AddictiveApps.YOUTUBE
        val contentDescription = "SHORT"

        val isAddictiveFeature = stringMatcher.isAddictiveFeature(app, contentDescription)

        assert(isAddictiveFeature)
    }

    @Test
    fun `Youtube Short return true`() {
        val app = AddictiveApps.YOUTUBE
        val contentDescription = "Short"

        val isAddictiveFeature = stringMatcher.isAddictiveFeature(app, contentDescription)

        assert(isAddictiveFeature)
    }


    @Test
    fun `Facebook video return false`() {
        val app = AddictiveApps.FACEBOOK
        val contentDescription = "video"

        val isAddictiveFeature = stringMatcher.isAddictiveFeature(app, contentDescription)

        assert(!isAddictiveFeature)
    }

    @Test
    fun `Facebook reel return true`() {
        val app = AddictiveApps.FACEBOOK
        val contentDescription = "reel"

        val isAddictiveFeature = stringMatcher.isAddictiveFeature(app, contentDescription)

        assert(isAddictiveFeature)
    }

    @Test
    fun `Facebook REEL return true`() {
        val app = AddictiveApps.FACEBOOK
        val contentDescription = "REEL"

        val isAddictiveFeature = stringMatcher.isAddictiveFeature(app, contentDescription)

        assert(isAddictiveFeature)
    }

    @Test
    fun `Facebook Reel return true`() {
        val app = AddictiveApps.FACEBOOK
        val contentDescription = "Reel"

        val isAddictiveFeature = stringMatcher.isAddictiveFeature(app, contentDescription)

        assert(isAddictiveFeature)
    }

    @Test
    fun `Facebook View reel return true`() {
        val app = AddictiveApps.FACEBOOK
        val contentDescription = "View reel"

        val isAddictiveFeature = stringMatcher.isAddictiveFeature(app, contentDescription)

        assert(isAddictiveFeature)
    }

    @Test
    fun `Facebook reels_tray_container return false`() {
        val app = AddictiveApps.FACEBOOK
        val contentDescription = "reels_tray_container"

        val isAddictiveFeature = stringMatcher.isAddictiveFeature(app, contentDescription)

        assert(!isAddictiveFeature)
    }
}