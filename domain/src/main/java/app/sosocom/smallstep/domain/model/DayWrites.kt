package app.sosocom.smallstep.domain.model

/**
 * 일별 작성 내역
 */
data class DayWrites(
    val day: Int,
    val todoList: TodoList?,
    val happyPointList: HappyPointList?,
    val goodAndNew: GoodAndNew?,
    val diary: Diary?
)
