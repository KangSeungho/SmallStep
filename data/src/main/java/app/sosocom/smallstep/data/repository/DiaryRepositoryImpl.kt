package app.sosocom.smallstep.data.repository

import app.sosocom.smallstep.data.Mapper
import app.sosocom.smallstep.data.data_source.DiaryDao
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.domain.repository.DiaryRepository

class DiaryRepositoryImpl(
    private val dao: DiaryDao
) : DiaryRepository {
    override suspend fun insertDiary(diary: Diary) = dao.insertDiary(Mapper.convertDiaryToEntity(diary))
    override suspend fun getAllDiary(dateTimeRange: LongRange) = dao.getAllDiary(dateTimeRange.first, dateTimeRange.last).map { Mapper.convertDiaryEntityToModel(it) }
    override suspend fun deleteDiary(diary: Diary) = dao.deleteDiary(Mapper.convertDiaryToEntity(diary))
}