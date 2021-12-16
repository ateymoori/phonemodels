package com.phonemodels.domain.usecases

import com.phonemodels.data.utils.onError
import com.phonemodels.data.utils.onSuccess
import com.phonemodels.domain.repositories.PhonesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FindPhonesTest {
    @Mock
    private lateinit var phonesRepository: PhonesRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Test if DI, FindPhonesUseCase and repository working fine`() {
        runBlockingTest {
            FindPhones(phonesRepository).invoke("iphone")
            Mockito.verify(phonesRepository, Mockito.times(1)).findPhonesByNameOrOS("iphone")
        }
    }

    /*
        invoke arg should be filled with search word
        in this case, I've passed null value as search word
        should return error (onError)
     */
    @ExperimentalCoroutinesApi
    @Test
    fun `Test if search word is empty, UseCase should return proper error`() {
        runBlockingTest {
            FindPhones(phonesRepository).invoke(null)
                .onSuccess {
                    assert(false)
                }.onError {
                    Assert.assertEquals(it, FindPhones.EMPTY_WORDS_ERROR)
                }
        }
    }

    /*
    invoke arg should be filled with search word
    in this case, I've passed null value as search word
    should return error (onError)
 */
    @ExperimentalCoroutinesApi
    @Test
    fun `Test if search word's length is less than enough, UseCase should return proper error`() {
        runBlockingTest {
            FindPhones(phonesRepository).invoke("a")
                .onSuccess {
                    assert(false)
                }.onError {
                    Assert.assertEquals(it, FindPhones.LENGTH_WORDS_ERROR)
                }
        }
    }

}