package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.model.HappyPoint
import app.sosocom.smallstep.domain.repository.HappyPointRepository
import javax.inject.Inject

class HappyPointInsertUseCase @Inject constructor(
    private val happyPointRepository: HappyPointRepository
) {
    suspend operator fun invoke(happyPoint: HappyPoint) = happyPointRepository.insertHappyPoint(happyPoint)
}