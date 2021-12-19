package com.phonemodels.data.utils

import com.google.gson.Gson
import com.phonemodels.data.models.PhoneResponse
import com.phonemodels.data.utils.GsonUtils.toObjectByGson
import org.junit.Before
import org.mockito.MockitoAnnotations
import org.junit.Assert.*
import org.junit.Test
import java.io.InputStreamReader

class GsonUtilsTest {

    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = GsonUtils.gson
    }

    @Test
    fun `Convert String to Json`() {
        val profileString =
            InputStreamReader(this.javaClass.classLoader?.getResourceAsStream("phone_details_api.json"))
        val jsonObject = profileString.toObjectByGson<PhoneResponse>()

        assertEquals(
            jsonObject.name,
            "Iphone 13 Pro MAX"
        )
    }

}