package app.sosocom.smallstep.data.repository

import app.sosocom.smallstep.data.Mapper
import app.sosocom.smallstep.data.data_source.TodoDao
import app.sosocom.smallstep.domain.model.Todo
import app.sosocom.smallstep.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {
    override suspend fun insertTodo(todo: Todo) = dao.insertTodo(Mapper.convertTodoToEntity(todo))
    override suspend fun getAllTodo(dateTimeRange: LongRange): List<Todo> = dao.getAllTodo(dateTimeRange.first, dateTimeRange.last).map { Mapper.convertTodoEntityToModel(it) }
    override suspend fun deleteTodo(todo: Todo) = dao.deleteTodo(Mapper.convertTodoToEntity(todo))
}