package com.phonemodels.data.repositories

import com.phonemodels.data.api.RestAPI
import com.phonemodels.data.models.PhoneResponse
import com.phonemodels.data.models.PhonesResponse
import com.phonemodels.data.models.SamplePhoneResponse
import com.phonemodels.data.utils.onError
import com.phonemodels.data.utils.onSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class PhonesRepositoryImplTest {
    @Mock
    private lateinit var restApi: RestAPI

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Test GetPhones Repository(getPhones) and PhoneResponses to PhoneEntityList mapper`() {
        runBlockingTest {

            val mockResponse =
                Response.success(PhonesResponse(devices = listOf(SamplePhoneResponse)))

            Mockito.`when`(restApi.getPhones()).thenReturn(mockResponse)

            val phonesRepositoryImpl = PhonesRepositoryImpl(restApi)

            phonesRepositoryImpl.getPhones().onSuccess {
                Assert.assertEquals(it?.get(0)?.name, SamplePhoneResponse.name)
            }.onError {
                assert(false)
            }

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Test GetPhones Repository (getPhoneDetails)`() {
        runBlockingTest {

            val mockResponse =
                Response.success(SamplePhoneResponse)

            Mockito.`when`(restApi.getPhoneDetails(1)).thenReturn(mockResponse)

            val phonesRepositoryImpl = PhonesRepositoryImpl(restApi)

            phonesRepositoryImpl.getPhoneDetails(1).onSuccess {
                Assert.assertEquals(it?.name, SamplePhoneResponse.name)
            }.onError {
                assert(false)
            }

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Test GetPhones Repository (findPhonesByNameOrOS)`() {
        runBlockingTest {
            val mockResponse =
                Response.success(PhonesResponse(devices = listOf(SamplePhoneResponse)))

            Mockito.`when`(restApi.findPhonesByNameOrOS("Android")).thenReturn(mockResponse)

            val phonesRepositoryImpl = PhonesRepositoryImpl(restApi)

            phonesRepositoryImpl.findPhonesByNameOrOS("Android").onSuccess {
                Assert.assertEquals(it?.get(0)?.name, SamplePhoneResponse.name)
            }.onError {
                assert(false)
            }

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Test GetPhones Repository (switchPhoneFavorites)`() {
        runBlockingTest {
            val mockResponse =
                Response.success(SamplePhoneResponse)

            Mockito.`when`(restApi.switchPhoneFavorites(1)).thenReturn(mockResponse)

            val phonesRepositoryImpl = PhonesRepositoryImpl(restApi)

            phonesRepositoryImpl.switchPhoneFavorites(1).onSuccess {
                Assert.assertEquals(it?.name, SamplePhoneResponse.name)
            }.onError {
                assert(false)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Test REST API Error Handling`() {
        val errorMsg = "You don't have access to this API."
        runBlockingTest {
            val errorResponse = """
                {
                       "type" : "error",
                       "message" : "$errorMsg"
                }
            """.trimIndent()

            val errorResponseBody =
                errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
            val mockResponse = Response.error<PhoneResponse>(403, errorResponseBody)

            Mockito.`when`(restApi.switchPhoneFavorites(1)).thenReturn(mockResponse)

            val phonesRepositoryImpl = PhonesRepositoryImpl(restApi)

            phonesRepositoryImpl.switchPhoneFavorites(1).onSuccess {
                assert(false)
            }.onError {
                Assert.assertEquals(it, errorMsg)
            }
        }
    }

}