package com.phonemodels.presentation.ui.screens.dashboard

import com.phonemodels.domain.entities.PhoneEntity
import com.phonemodels.presentation.utils.ViewEvent
import com.phonemodels.presentation.utils.ViewSideEffect
import com.phonemodels.presentation.utils.ViewState

class DashboardContract {
    sealed class Event : ViewEvent {
        data class PhoneSelected(val phoneID: Int?) : Event()
        data class SearchValueChanged(val searchValue: String?) : Event()
    }

    data class State(
        val phones: List<PhoneEntity> = listOf(),
        val isLoading: Boolean = false,
        val error: String? = null,
        val searchValue: String? = null
    ) :
        ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()
        object GotError : Effect()

        sealed class Navigation : Effect() {
            data class ToPhoneDetails(val phoneID: Int) : Navigation()
        }
    }

}