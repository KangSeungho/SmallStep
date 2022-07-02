package app.sosocom.smallstep.domain.repository

import app.sosocom.smallstep.domain.model.Todo

interface TodoRepository {
    suspend fun insertTodo(todo: Todo)
    suspend fun getAllTodo(dateTimeRange: LongRange): List<Todo>
    suspend fun deleteTodo(todo: Todo)
}