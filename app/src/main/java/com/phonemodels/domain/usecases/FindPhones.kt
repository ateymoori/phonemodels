package com.phonemodels.domain.usecases

import com.phonemodels.data.utils.Resource
import com.phonemodels.domain.entities.PhoneEntity
import com.phonemodels.domain.repositories.PhonesRepository
import javax.inject.Inject

class FindPhones @Inject constructor(
    private val phonesRepository: PhonesRepository
) : UseCase<String?, Resource<List<PhoneEntity>>>() {

    //to let test classes have access to these values
    companion object {
        private val MINIMUM_SEARCH_WORDS_LENGTH = 3
        val EMPTY_WORDS_ERROR = "Search string is mandatory"
        val LENGTH_WORDS_ERROR = "need $MINIMUM_SEARCH_WORDS_LENGTH char to search at least"
    }

    override suspend fun invoke(data: String?): Resource<List<PhoneEntity>> {
        return when {
            data.isNullOrEmpty() -> {
                Resource.Failure.Generic(EMPTY_WORDS_ERROR)
            }
            data.length < MINIMUM_SEARCH_WORDS_LENGTH -> {
                Resource.Failure.Generic(LENGTH_WORDS_ERROR)
            }
            else -> {
                phonesRepository.findPhonesByNameOrOS(data)
            }
        }

    }

}