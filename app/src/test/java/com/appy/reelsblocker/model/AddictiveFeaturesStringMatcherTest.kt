package com.appy.reelsblocker.model

import org.junit.Test

class AddictiveFeaturesStringMatcherTest {

    val stringMatcher = AddictiveFeaturesStringMatcher()


    @Test
    fun `View reels returns true`() {

        val contentDescription = "View reels"

        val isAddictive = stringMatcher.isAddictiveFeature(contentDescription)

        assert(isAddictive)

    }

    @Test
    fun `Reels returns true`() {

        val contentDescription = "Reels"

        val isAddictive = stringMatcher.isAddictiveFeature(contentDescription)

        assert(isAddictive)

    }

    @Test
    fun `Shorts returns true`() {

        val contentDescription = "Shorts"

        val isAddictive = stringMatcher.isAddictiveFeature(contentDescription)

        assert(isAddictive)

    }

    @Test
    fun `Photos returns false`() {

        val contentDescription = "Photos"

        val isAddictive = stringMatcher.isAddictiveFeature(contentDescription)

        assert(!isAddictive)

    }

    @Test
    fun `null contentDescription returns false`() {

        val contentDescription: String? = null

        val isAddictive = stringMatcher.isAddictiveFeature(contentDescription)

        assert(!isAddictive)

    }
}