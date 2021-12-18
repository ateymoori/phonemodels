package com.phonemodels.presentation.ui.screens.details

import com.phonemodels.domain.entities.PhoneEntity
import com.phonemodels.presentation.utils.ViewEvent
import com.phonemodels.presentation.utils.ViewSideEffect
import com.phonemodels.presentation.utils.ViewState

class DetailsContract {
    sealed class Event : ViewEvent {
        data class SwitchFavorite(val phoneID: Int?) : Event()
    }

    data class State(
        val phone: PhoneEntity? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    ) :
        ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()
        object GotError : Effect()
    }
}