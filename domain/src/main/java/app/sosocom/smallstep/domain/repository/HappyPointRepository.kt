package app.sosocom.smallstep.domain.repository

import app.sosocom.smallstep.domain.model.HappyPoint

interface HappyPointRepository {
    suspend fun insertHappyPoint(happyPoint: HappyPoint): Long
    suspend fun getAllHappyPoint(dateTimeRange: LongRange): List<HappyPoint>
    suspend fun deleteHappyPoint(happyPoint: HappyPoint)
}