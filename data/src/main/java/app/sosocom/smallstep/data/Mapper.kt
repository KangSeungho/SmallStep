package app.sosocom.smallstep.data

import app.sosocom.smallstep.data.entity.DiaryEntity
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.domain.util.LocalDateTime
import app.sosocom.smallstep.domain.util.toEpochMilli
import java.time.*

object Mapper {
    fun convertDiaryToEntity(diary: Diary) =
        DiaryEntity(
            id = diary.id,
            title = diary.title,
            content = diary.content,
            baseDate = diary.baseDate.toEpochDay(),
            createdAt = diary.createdAt.toEpochMilli()
        )

    fun convertDiaryEntityToEntity(diaryEntity: DiaryEntity) =
        Diary(
            id = diaryEntity.id,
            title = diaryEntity.title,
            content = diaryEntity.content,
            baseDate = LocalDate.ofEpochDay(diaryEntity.baseDate),
            createdAt = LocalDateTime.ofEpochMilli(diaryEntity.createdAt)
        )
}