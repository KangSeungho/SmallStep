package app.sosocom.smallstep.domain.repository

import app.sosocom.smallstep.domain.model.Diary

interface DiaryRepository {
    suspend fun insertDiary(diary: Diary)
    suspend fun getAllDiary(dateTimeRange: LongRange): List<Diary>
    suspend fun deleteDiary(diary: Diary)
}