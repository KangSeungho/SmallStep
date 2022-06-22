package app.sosocom.smallstep.presentation.ui.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sosocom.smallstep.domain.model.DailyTodoBundle
import app.sosocom.smallstep.domain.model.Todo
import app.sosocom.smallstep.domain.usecase.TodoInsertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val insertUseCase: TodoInsertUseCase
) : ViewModel() {

    private val _dailyTodoBundle = MutableLiveData<DailyTodoBundle>()
    val dailyTodoBundle: LiveData<DailyTodoBundle> get() = _dailyTodoBundle

    fun setDailyTodoBundle(dailyTodoBundle: DailyTodoBundle) {
        _dailyTodoBundle.value = dailyTodoBundle
    }

    suspend fun insertTodo(todo: Todo) = insertUseCase(todo)

}