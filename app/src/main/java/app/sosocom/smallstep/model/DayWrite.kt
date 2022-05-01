package app.sosocom.smallstep.model

/**
 * 일별 작성 내역
 */
data class DayWrite(
    val todoList: TodoList,
    val happyPointList: HappyPointList,
    val goodAndNew: GoodAndNew,
    val diary: Diary
)
