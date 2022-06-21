package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.model.Todo
import app.sosocom.smallstep.domain.repository.TodoRepository
import javax.inject.Inject

class TodoInsertUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) = todoRepository.insertTodo(todo)
}