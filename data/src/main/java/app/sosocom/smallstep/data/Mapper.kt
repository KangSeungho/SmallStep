package app.sosocom.smallstep.data

import app.sosocom.smallstep.data.entity.DiaryEntity
import app.sosocom.smallstep.domain.model.Diary
import java.time.LocalDate
import java.util.*

object Mapper {
    fun convertDiaryToEntity(diary: Diary) =
        DiaryEntity(
            id = diary.id,
            title = diary.title,
            content = diary.content,
            baseDate = diary.baseDate.toEpochDay(),
            createdAt = diary.createdAt.timeInMillis
        )

    fun convertDiaryEntityToEntity(diaryEntity: DiaryEntity) =
        Diary(
            id = diaryEntity.id,
            title = diaryEntity.title,
            content = diaryEntity.content,
            baseDate = LocalDate.ofEpochDay(diaryEntity.baseDate),
            createdAt = Calendar.getInstance().apply { timeInMillis = diaryEntity.createdAt }
        )
}