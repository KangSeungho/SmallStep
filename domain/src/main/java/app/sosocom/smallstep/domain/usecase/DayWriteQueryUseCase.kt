package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.model.DayWrites
import app.sosocom.smallstep.domain.model.TodoList
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class DayWriteQueryUseCase @Inject constructor(
    private val diaryUseCase: DiaryQueryUseCase,
    private val todoUseCase: TodoQueryUseCase
) {
    suspend operator fun invoke(year: Int, month: Int): Map<Int, DayWrites> {
        val dateTimeRange = getDateTimeRange(year, month)

        val response = HashMap<Int, DayWrites>()

        for(diary in diaryUseCase(dateTimeRange)) {
            val day = diary.baseDate.dayOfMonth
            val dayWrites = response.getOrDefault(day, DayWrites(day))

            dayWrites.diary = diary
            response[dayWrites.day] = dayWrites
        }

        for(todoList in todoUseCase(dateTimeRange).groupBy { it.baseDate }) {
            val day = todoList.key.dayOfMonth
            val dayWrites = response.getOrDefault(day, DayWrites(day))

            dayWrites.todoList = TodoList(todoList.value, todoList.key)
            response[dayWrites.day] = dayWrites
        }

        return response
    }

    private fun getDateTimeRange(year: Int, month: Int): LongRange {

        val startTime = LocalDate.of(year, month, 1).toEpochDay()
        val endTime = LocalDate.of(year, month+1, 1).toEpochDay()

        return startTime..endTime
    }
}