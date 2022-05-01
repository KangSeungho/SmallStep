package app.sosocom.smallstep.model

data class TodoList(
    val todoList: List<Todo>,
    val createAt: Long
)

data class Todo(
    // 작업 내역
    val content: String,

    // 작업 완료 상태
    val isComplete: Boolean
)
