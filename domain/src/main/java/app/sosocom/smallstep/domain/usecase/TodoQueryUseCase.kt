package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.repository.TodoRepository
import javax.inject.Inject

class TodoQueryUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(dateTimeRange: LongRange) = todoRepository.getAllTodo(dateTimeRange)
}