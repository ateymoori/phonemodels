package com.phonemodels.domain.usecases

import com.phonemodels.data.utils.Resource
import com.phonemodels.data.utils.onSuccess
import com.phonemodels.domain.entities.SamplePhoneEntity
import com.phonemodels.domain.repositories.PhonesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SwitchFavoriteTest {
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
    fun `Test if SwitchFavorite UseCase (DI and Interface works fine)`() {
        runBlockingTest {
            SwitchFavorite(phonesRepository).invoke(1)
            Mockito.verify(phonesRepository, Mockito.times(1)).switchPhoneFavorites(1)
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `Test if the mocked data is equaled to the result of repository and use-case in failed scenario`() {
        runBlockingTest {

            val sampleEntity = Resource.Success(SamplePhoneEntity.copy(isFavorite = false))

            Mockito.`when`(phonesRepository.switchPhoneFavorites(1)).thenReturn(sampleEntity)

            val useCase = SwitchFavorite(phonesRepository)

            useCase.invoke()
                .onSuccess {
                    Assert.assertEquals(it?.isFavorite, false)
                }
        }
    }
}