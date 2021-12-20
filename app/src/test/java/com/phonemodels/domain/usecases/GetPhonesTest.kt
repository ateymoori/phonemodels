package com.phonemodels.domain.usecases

import com.phonemodels.data.utils.Resource
import com.phonemodels.data.utils.onNetworkError
import com.phonemodels.data.utils.onSuccess
import com.phonemodels.domain.entities.SamplePhoneEntity
import com.phonemodels.domain.repositories.PhonesRepository
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetPhonesTest {
    @Mock
    private lateinit var phonesRepository: PhonesRepository


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    /*
        PhonesRepository injected into the GetPhones UseCase class.
        By calling the GetPhones`s invoke method, phonesRepository.getPhones method should be called.
     */
    @ExperimentalCoroutinesApi
    @Test
    fun `Test if GetPhones UseCase can call phonesRepository getters correct(DI and Interface works fine)`() {
        runBlockingTest {
            GetPhones(phonesRepository).invoke()
            verify(phonesRepository, times(1)).getPhones()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Test if the mocked data is equaled to the result of repository and use-case in successful scenario`() {
        runBlockingTest {
            val results = Resource.Success(
                listOf(SamplePhoneEntity)
            )

            Mockito.`when`(phonesRepository.getPhones()).thenReturn(results)

            val useCase = GetPhones(phonesRepository)

            useCase.invoke()
                .onSuccess {
                    assertEquals(it?.get(0)?.name, SamplePhoneEntity.name)
                }
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `Test if the mocked data is equaled to the result of repository and use-case in failed scenario`() {
        runBlockingTest {
            val errorMsg = "No Internet connection"

            val results = Resource.Failure.NetworkException(errorMsg)

            Mockito.`when`(phonesRepository.getPhones()).thenReturn(results)

            val useCase = GetPhones(phonesRepository)

            useCase.invoke()
                .onNetworkError {
                    assertEquals(it, errorMsg)
                }
        }
    }
}