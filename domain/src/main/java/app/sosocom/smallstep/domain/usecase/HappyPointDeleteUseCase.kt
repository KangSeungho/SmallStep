package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.model.HappyPoint
import app.sosocom.smallstep.domain.repository.HappyPointRepository
import javax.inject.Inject

class HappyPointDeleteUseCase @Inject constructor(
    private val happyPointRepository: HappyPointRepository
) {
    suspend operator fun invoke(happyPoint: HappyPoint) = happyPointRepository.deleteHappyPoint(happyPoint)
}