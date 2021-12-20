package com.phonemodels.domain.repositories

import com.phonemodels.data.utils.Resource
import com.phonemodels.domain.entities.PhoneEntity

interface PhonesRepository {
    suspend fun getPhones(): Resource<List<PhoneEntity>>
    suspend fun getPhoneDetails(id: Int?): Resource<PhoneEntity>
    suspend fun findPhonesByNameOrOS(word: String?): Resource<List<PhoneEntity>>
    suspend fun switchPhoneFavorites(id: Int?): Resource<PhoneEntity>
}