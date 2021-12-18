package com.phonemodels.domain.usecases

import com.phonemodels.data.utils.Resource
import com.phonemodels.domain.entities.PhoneEntity
import com.phonemodels.domain.repositories.PhonesRepository
import javax.inject.Inject

class SwitchFavorite @Inject constructor(
    private val phonesRepository: PhonesRepository
) : UseCase<Int?, Resource<PhoneEntity>>() {

    override suspend fun invoke(data: Int?): Resource<PhoneEntity> {
        return phonesRepository.switchPhoneFavorites(data )
    }
}