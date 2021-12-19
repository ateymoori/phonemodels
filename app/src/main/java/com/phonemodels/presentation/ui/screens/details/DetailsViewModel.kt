package com.phonemodels.presentation.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.phonemodels.data.utils.onError
import com.phonemodels.data.utils.onSuccess
import com.phonemodels.domain.usecases.GetPhoneDetails
import com.phonemodels.domain.usecases.SwitchFavorite
import com.phonemodels.presentation.ui.screens.entry.EntryPointActivity
import com.phonemodels.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val getPhoneDetails: GetPhoneDetails,
    private val switchFavorite: SwitchFavorite
) :
    BaseViewModel<DetailsContract.Event, DetailsContract.State, DetailsContract.Effect>() {

    init {
        viewModelScope.launch {
            getDetails(
                stateHandle.get<Int>(EntryPointActivity.NavigationKeys.Arg.PHONE_ID)
            )
        }
    }

    override fun setInitialState() =
        DetailsContract.State(phone = null, isLoading = true)

    override fun handleEvents(event: DetailsContract.Event) {
        when (event) {
            is DetailsContract.Event.SwitchFavorite -> {
                viewModelScope.launch {
                    switchPhoneFavorite(event.phoneID)
                }
            }
        }
    }

    suspend fun getDetails(phoneID: Int?) {
        getPhoneDetails.invoke(phoneID).onSuccess {
            setState {
                copy(phone = it, isLoading = false, error = null)
            }
            setEffect { DetailsContract.Effect.DataWasLoaded }
        }.onError {
            setState {
                copy(phone = phone, isLoading = false, error = it)
            }
        }
    }

    private suspend fun switchPhoneFavorite(phoneID: Int?) {
        switchFavorite.invoke(phoneID).onSuccess {
            setState {
                copy(phone = it, isLoading = false, error = null)
            }
            setEffect { DetailsContract.Effect.DataWasLoaded }
        }.onError {
            setState {
                copy(phone = phone, isLoading = false, error = it)
            }
        }
    }

}
