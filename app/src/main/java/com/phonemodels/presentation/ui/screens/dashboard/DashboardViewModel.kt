package com.phonemodels.presentation.ui.screens.dashboard

import androidx.lifecycle.viewModelScope
import com.phonemodels.data.utils.onError
import com.phonemodels.data.utils.onSuccess
import com.phonemodels.domain.usecases.GetPhones
import com.phonemodels.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val getPhones: GetPhones) :
    BaseViewModel<DashboardContract.Event, DashboardContract.State, DashboardContract.Effect>() {

    init {
        viewModelScope.launch { getPhonesList() }
    }

    override fun setInitialState() =
        DashboardContract.State(phones = listOf(), isLoading = true)

    override fun handleEvents(event: DashboardContract.Event) {
        when (event) {
            is DashboardContract.Event.PhoneSelection -> {
                setEffect {
                    DashboardContract.Effect.Navigation.ToPhoneDetails(
                        event.phoneID ?: 0
                    )
                }
            }
        }
    }

    private suspend fun getPhonesList() {
        getPhones.invoke().onSuccess {
            setState {
                copy(phones = it ?: listOf(), isLoading = false, error = null)
            }
            setEffect { DashboardContract.Effect.DataWasLoaded }
        }.onError {
            setState {
                copy(phones = phones, isLoading = false, error = it)
            }
        }
    }

}
