package com.phonemodels.presentation.ui.screens.dashboard

import androidx.lifecycle.viewModelScope
import com.phonemodels.data.utils.onError
import com.phonemodels.data.utils.onSuccess
import com.phonemodels.domain.usecases.FindPhones
import com.phonemodels.domain.usecases.GetPhones
import com.phonemodels.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getPhones: GetPhones,
    private val findPhones: FindPhones
) :
    BaseViewModel<DashboardContract.Event, DashboardContract.State, DashboardContract.Effect>() {

    init {
        viewModelScope.launch { getAllPhones() }
    }

    override fun setInitialState() =
        DashboardContract.State(phones = listOf(), isLoading = true)

    override fun handleEvents(event: DashboardContract.Event) {
        when (event) {
            is DashboardContract.Event.PhoneSelected -> {
                setEffect {
                    DashboardContract.Effect.Navigation.ToPhoneDetails(
                        event.phoneID ?: 0
                    )
                }
            }
            is DashboardContract.Event.SearchValueChanged -> {
                setState {
                    viewModelScope.launch {
                        if (event.searchValue.isNullOrEmpty())
                            getAllPhones()
                        else
                            findPhones(event.searchValue)
                    }
                    copy(isLoading = true, searchValue = event.searchValue)
                }
            }
        }
    }

    suspend fun getAllPhones() {
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

    suspend fun findPhones(word: String?) {
        findPhones.invoke(word).onSuccess {
            setState {
                copy(phones = it ?: listOf(), isLoading = false, error = null)
            }
            setEffect { DashboardContract.Effect.DataWasLoaded }
        }.onError {
            setState {
                copy(phones = phones, isLoading = false, error = it)
            }
            setEffect { DashboardContract.Effect.GotError }
        }
    }


}
