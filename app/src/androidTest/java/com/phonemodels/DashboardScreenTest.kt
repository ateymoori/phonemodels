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


class DashboardScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<EntryPointActivity>()

    @Before
    fun initList() {
        composeTestRule.setContent {
            PhoneModelsAppTheme {
                DashboardView(state = DashboardContract.State(
                    phones = listOf(),
                    isLoading = false,
                    error = null,
                    searchValue = null
                ),
                    effectFlow = null,
                    onEventSent = {},
                    navController = null,
                    onNavigationRequested = {})
            }
        }
    }

    /**
     * after click on search button in the dashboard,
     * page title should be hide and search field should be appeared.
     */
    @Test
    fun testSearchButton() {

        val searchButton =
            composeTestRule.onNode(hasTestTag("searchButton"), useUnmergedTree = true)

        val pageTitle =
            composeTestRule.onNode(hasTestTag("pageTitle"), useUnmergedTree = true)

        val searchField =
            composeTestRule.onNode(hasTestTag("searchField"), useUnmergedTree = true)

        pageTitle.assertIsDisplayed()

        searchButton.performClick()

        pageTitle.assertDoesNotExist()
        searchField.assertIsDisplayed()
    }


}