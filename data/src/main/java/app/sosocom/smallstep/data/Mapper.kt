package app.sosocom.smallstep.data

import app.sosocom.smallstep.data.entity.DiaryEntity
import app.sosocom.smallstep.data.entity.HappyPointEntity
import app.sosocom.smallstep.data.entity.TodoEntity
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.domain.model.HappyPoint
import app.sosocom.smallstep.domain.model.Todo
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

    fun convertDiaryEntityToModel(entity: DiaryEntity) =
        Diary(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            baseDate = LocalDate.ofEpochDay(entity.baseDate),
            createdAt = LocalDateTime.ofEpochMilli(entity.createdAt)
        )

    fun convertTodoToEntity(todo: Todo) =
        TodoEntity(
            id = todo.id,
            content = todo.content,
            isComplete = todo.isComplete,
            baseDate = todo.baseDate.toEpochDay(),
            createdAt = todo.createdAt.toEpochMilli()
        )

    fun convertTodoEntityToModel(entity: TodoEntity) =
        Todo(
            id = entity.id,
            content = entity.content,
            isComplete = entity.isComplete,
            baseDate = LocalDate.ofEpochDay(entity.baseDate),
            createdAt = LocalDateTime.ofEpochMilli(entity.createdAt)
        )

    fun convertHappyPointToEntity(happyPoint: HappyPoint) =
        HappyPointEntity(
            id = happyPoint.id,
            content = happyPoint.content,
            comment = happyPoint.comment,
            point = happyPoint.point,
            baseDate = happyPoint.baseDate.toEpochDay(),
            createdAt = happyPoint.createdAt.toEpochMilli()
        )

    fun convertHappyPointEntityToModel(entity: HappyPointEntity) =
        HappyPoint(
            id = entity.id,
            content = entity.content,
            comment = entity.comment,
            point = entity.point,
            baseDate = LocalDate.ofEpochDay(entity.baseDate),
            createdAt = LocalDateTime.ofEpochMilli(entity.createdAt)
        )
}