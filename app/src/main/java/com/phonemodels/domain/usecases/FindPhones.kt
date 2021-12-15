package com.phonemodels.domain.usecases

import com.phonemodels.data.utils.Resource
import com.phonemodels.domain.entities.PhoneEntity
import com.phonemodels.domain.repositories.PhonesRepository
import javax.inject.Inject

class FindPhones @Inject constructor(
    private val phonesRepository: PhonesRepository
) : UseCase<String?, Resource<List<PhoneEntity>>>() {
    private val MINIMUM_SEARCH_WORDS_LENGTH = 3

    override suspend fun invoke(data: String?): Resource<List<PhoneEntity>> {
        return when {
            data.isNullOrEmpty() -> {
                Resource.Failure.Generic("Search string is mandatory")
            }
            data.length < MINIMUM_SEARCH_WORDS_LENGTH -> {
                Resource.Failure.Generic("need $MINIMUM_SEARCH_WORDS_LENGTH char to search at least")
            }
            else -> {
                phonesRepository.findPhonesByOsOrName(data)
            }
        }

    }

}