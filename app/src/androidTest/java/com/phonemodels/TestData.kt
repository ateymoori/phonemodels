package com.phonemodels

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.phonemodels.presentation.ui.screens.entry.EntryPointActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestData {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<EntryPointActivity>()

    @Test
    fun testPhonesListHasRecord() {
        val phonesListItem =
            composeTestRule.onNode(hasTestTag("phonesList"), useUnmergedTree = true)
        phonesListItem.onChildren().get(0).assertIsDisplayed()
    }

}