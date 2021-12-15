package com.phonemodels.data.repositories

import com.phonemodels.data.api.RestAPI
import com.phonemodels.data.models.mapToPhoneEntities
import com.phonemodels.data.utils.BaseDataSource
import com.phonemodels.data.utils.Resource
import com.phonemodels.data.utils.onError
import com.phonemodels.data.utils.onSuccess
import com.phonemodels.domain.entities.PhoneEntity
import com.phonemodels.domain.repositories.PhonesRepository
import javax.inject.Inject

class PhonesRepositoryImpl  @Inject constructor(
    private val restApi: RestAPI,
) : PhonesRepository, BaseDataSource()  {

    override suspend fun getPhones(): Resource<List<PhoneEntity>> {
        getResult { restApi.getPhones() }.onSuccess {
            return Resource.Success(it?.mapToPhoneEntities())
        }.onError {
            return Resource.Failure.Generic(it)
        }
        return Resource.Failure.NetworkException(null)
    }

    override suspend fun findPhonesByOsOrName(word: String?): Resource<List<PhoneEntity>> {
        getResult { restApi.findPhonesByOsOrName(word) }.onSuccess {
            return Resource.Success(it?.mapToPhoneEntities())
        }.onError {
            return Resource.Failure.Generic(it)
        }
        return Resource.Failure.NetworkException(null)
    }
}