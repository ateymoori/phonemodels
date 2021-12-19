package com.phonemodels.presentation.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import com.phonemodels.data.utils.Resource
import com.phonemodels.domain.entities.SamplePhoneEntity
import com.phonemodels.domain.repositories.PhonesRepository
import com.phonemodels.domain.usecases.FindPhones
import com.phonemodels.domain.usecases.GetPhoneDetails
import com.phonemodels.domain.usecases.GetPhones
import com.phonemodels.domain.usecases.SwitchFavorite
import com.phonemodels.presentation.ui.screens.dashboard.DashboardViewModel
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsViewModelTest {
    private lateinit var getPhoneDetails: GetPhoneDetails
    private lateinit var findPhonesUseCase: FindPhones
    private lateinit var switchFavorite: SwitchFavorite
    private lateinit var stateHandle: SavedStateHandle

    @Mock
    private lateinit var phonesRepository: PhonesRepository
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        getPhoneDetails = GetPhoneDetails(phonesRepository)
        findPhonesUseCase = FindPhones(phonesRepository)
        switchFavorite = SwitchFavorite(phonesRepository)
        stateHandle = SavedStateHandle()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `TEST GetPhoneDetails, State and effect of Details ViewModel`() {
        runBlockingTest {
            launch(dispatcher) {
                val results = Resource.Success(SamplePhoneEntity)

                Mockito.`when`(phonesRepository.getPhoneDetails(1)).thenReturn(results)
                val viewModel = DetailsViewModel(
                    stateHandle = stateHandle,
                    getPhoneDetails = getPhoneDetails,
                    switchFavorite = switchFavorite
                )
                viewModel.getDetails(1)
                Assert.assertEquals(viewModel.viewState.value.phone, SamplePhoneEntity)
            }
        }
    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `TEST FindPhones, State and effect of Dashboard ViewModel`() {
//        runBlockingTest {
//            launch(dispatcher) {
//
//                val results = Resource.Success(
//                    listOf(SamplePhoneEntity)
//                )
//
//                Mockito.`when`(phonesRepository.findPhonesByNameOrOS("apple")).thenReturn(results)
//                val viewModel = DashboardViewModel(getPhonesUseCase, findPhonesUseCase)
//
//                viewModel.findPhones("apple")
//                Assert.assertEquals(viewModel.viewState.value.phones, listOf(SamplePhoneEntity))
//            }
//
//        }
//    }
//
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `TEST ERROR HANDLING in DashboardViewModel`() {
//        val errorMsg = "Error in Connection"
//
//        runBlockingTest {
//            launch(dispatcher) {
//                val result = Resource.Failure.Generic(
//                    errorMsg
//                )
//
//                Mockito.`when`(phonesRepository.findPhonesByNameOrOS("new")).thenReturn(result)
//                val viewModel = DashboardViewModel(getPhonesUseCase, findPhonesUseCase)
//
//                viewModel.findPhones("new")
//                Assert.assertEquals(viewModel.viewState.value.error, errorMsg)
//            }
//
//        }
//    }
//

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}