package com.phonemodels.domain.usecases

import com.phonemodels.data.utils.Resource
import com.phonemodels.domain.entities.PhoneEntity
import com.phonemodels.domain.repositories.PhonesRepository
import javax.inject.Inject

class GetPhones @Inject constructor(
    private val phonesRepository : PhonesRepository
) : UseCase<Nothing?, Resource<List<PhoneEntity>>>() {

    override suspend fun invoke(data: Nothing?): Resource<List<PhoneEntity>> {
        return phonesRepository.getPhones()
    }

}