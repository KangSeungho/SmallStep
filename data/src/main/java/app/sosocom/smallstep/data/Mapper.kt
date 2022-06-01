package app.sosocom.smallstep.data

import app.sosocom.smallstep.data.entity.DiaryEntity
import app.sosocom.smallstep.domain.model.Diary

object Mapper {
    fun convertDiaryToEntity(diary: Diary) =
        DiaryEntity(
            id = diary.id,
            title = diary.title,
            content = diary.content,
            createAt = diary.createAt
        )
}