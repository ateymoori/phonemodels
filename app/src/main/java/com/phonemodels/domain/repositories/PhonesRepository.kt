package com.phonemodels.domain.repositories

import com.phonemodels.data.utils.Resource
import com.phonemodels.domain.entities.PhoneEntity

interface PhonesRepository {
    suspend fun getPhones(): Resource<List<PhoneEntity>>
    suspend fun findPhonesByOsOrName(word:String?): Resource<List<PhoneEntity>>
}