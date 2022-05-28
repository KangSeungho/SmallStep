package app.sosocom.smallstep.model

/**
 * 날짜 기준으로 정의한 할 일 목록
 */
data class TodoList(
    val todoList: List<Todo>,
    val createAt: Long
)

/**
 * 할 일 정보
 */
data class Todo(
    // 작업 내역
    val content: String,

    // 작업 완료 상태
    val isComplete: Boolean
)
