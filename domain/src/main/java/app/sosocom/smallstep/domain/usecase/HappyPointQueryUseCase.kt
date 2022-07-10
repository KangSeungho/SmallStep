package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.repository.HappyPointRepository
import javax.inject.Inject

class HappyPointQueryUseCase @Inject constructor(
    private val happyPointRepository: HappyPointRepository
) {
    suspend operator fun invoke(dateTimeRange: LongRange) = happyPointRepository.getAllHappyPoint(dateTimeRange)
}