package app.sosocom.smallstep.data

import app.sosocom.smallstep.data.entity.DiaryEntity
import app.sosocom.smallstep.domain.model.Diary
import java.util.*

object Mapper {
    fun convertDiaryToEntity(diary: Diary) =
        DiaryEntity(
            id = diary.id,
            title = diary.title,
            content = diary.content,
            baseDate = diary.baseDate.timeInMillis,
            createdAt = diary.createdAt.timeInMillis
        )

    fun convertDiaryEntityToEntity(diaryEntity: DiaryEntity) =
        Diary(
            id = diaryEntity.id,
            title = diaryEntity.title,
            content = diaryEntity.content,
            baseDate = Calendar.getInstance().apply { timeInMillis = diaryEntity.baseDate },
            createdAt = Calendar.getInstance().apply { timeInMillis = diaryEntity.createdAt }
        )
}