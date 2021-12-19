package com.phonemodels.domain.usecases

import com.phonemodels.data.utils.Resource
import com.phonemodels.data.utils.onError
import com.phonemodels.data.utils.onNetworkError
import com.phonemodels.data.utils.onSuccess
import com.phonemodels.domain.entities.SamplePhoneEntity
import com.phonemodels.domain.repositories.PhonesRepository
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetPhoneDetailsTest {
    @Mock
    private lateinit var phonesRepository: PhonesRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Test GetPhoneDetails use-case, DI and Repository`() {
        runBlockingTest {
            GetPhoneDetails(phonesRepository).invoke(1)
            Mockito.verify(phonesRepository, Mockito.times(1)).getPhoneDetails(1)
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `Test Get Phone Details if returns correct answer`() {
        runBlockingTest {

            val result = Resource.Success(SamplePhoneEntity)

            Mockito.`when`(phonesRepository.getPhoneDetails(1)).thenReturn(result)

            val useCase = GetPhoneDetails(phonesRepository)

            useCase.invoke()
                .onSuccess {
                    Assert.assertEquals(it?.name, SamplePhoneEntity.name)
                }
        }
    }


}