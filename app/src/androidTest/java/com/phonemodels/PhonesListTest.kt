package com.phonemodels


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.phonemodels.domain.entities.SamplePhoneEntity
import com.phonemodels.presentation.ui.screens.dashboard.DashboardContract
import com.phonemodels.presentation.ui.screens.dashboard.DashboardView
import com.phonemodels.presentation.ui.screens.dashboard.PhonesList
import com.phonemodels.presentation.ui.screens.details.DetailsContract
import com.phonemodels.presentation.ui.screens.details.DetailsView
import com.phonemodels.presentation.ui.screens.entry.EntryPointActivity
import com.phonemodels.presentation.ui.theme.PhoneModelsAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PhonesListTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<EntryPointActivity>()

    @Before
    fun initList() {
        composeTestRule.setContent {
            PhoneModelsAppTheme {
                PhonesList(listOf(SamplePhoneEntity))
            }
        }
    }

    @Test
    fun testPhonesListHasRecord() {
        val phonesListItem =
            composeTestRule.onNode(hasTestTag("phonesList"), useUnmergedTree = true)
        phonesListItem.onChildren().assertCountEquals(1)
    }

}