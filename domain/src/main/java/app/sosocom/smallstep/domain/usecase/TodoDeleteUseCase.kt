package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.model.Todo
import app.sosocom.smallstep.domain.repository.TodoRepository
import javax.inject.Inject

class TodoDeleteUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) = repository.deleteTodo(todo)
}