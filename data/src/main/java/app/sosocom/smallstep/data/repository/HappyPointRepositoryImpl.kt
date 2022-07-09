package app.sosocom.smallstep.data.repository

import app.sosocom.smallstep.data.Mapper
import app.sosocom.smallstep.data.data_source.HappyPointDao
import app.sosocom.smallstep.domain.model.HappyPoint
import app.sosocom.smallstep.domain.repository.HappyPointRepository

class HappyPointRepositoryImpl(
    private val dao: HappyPointDao
) : HappyPointRepository {
    override suspend fun insertHappyPoint(happyPoint: HappyPoint) = dao.insertHappyPoint(Mapper.convertHappyPointToEntity(happyPoint))

    override suspend fun getAllHappyPoint(dateTimeRange: LongRange) = dao.getAllHappyPoint(dateTimeRange.first, dateTimeRange.last).map { Mapper.convertHappyPointEntityToModel(it) }

    override suspend fun deleteHappyPoint(happyPoint: HappyPoint) = dao.deleteHappyPoint(Mapper.convertHappyPointToEntity(happyPoint))
}