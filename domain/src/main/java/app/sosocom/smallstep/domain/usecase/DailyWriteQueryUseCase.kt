package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.model.DailyHappyPointBundle
import app.sosocom.smallstep.domain.model.DailyWriteBundle
import app.sosocom.smallstep.domain.model.DailyTodoBundle
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class DailyWriteQueryUseCase @Inject constructor(
    private val diaryUseCase: DiaryQueryUseCase,
    private val todoUseCase: TodoQueryUseCase,
    private val happyPointUseCase: HappyPointQueryUseCase
) {
    suspend operator fun invoke(year: Int, month: Int): Map<Int, DailyWriteBundle> {
        val dateTimeRange = getDateTimeRange(year, month)

        val response = HashMap<Int, DailyWriteBundle>()

        for(diary in diaryUseCase(dateTimeRange)) {
            val day = diary.baseDate.dayOfMonth
            val dailyWriteBundle = response.getOrDefault(day, DailyWriteBundle(day))

            dailyWriteBundle.diary = diary
            response[dailyWriteBundle.day] = dailyWriteBundle
        }

        for(todoList in todoUseCase(dateTimeRange).groupBy { it.baseDate }) {
            val day = todoList.key.dayOfMonth
            val dailyWriteBundle = response.getOrDefault(day, DailyWriteBundle(day))

            dailyWriteBundle.dailyTodoBundle = DailyTodoBundle(todoList.value.toMutableList(), todoList.key)
            response[dailyWriteBundle.day] = dailyWriteBundle
        }

        for(happyPointList in happyPointUseCase(dateTimeRange).groupBy { it.baseDate }) {
            val day = happyPointList.key.dayOfMonth
            val dailyWriteBundle = response.getOrDefault(day, DailyWriteBundle(day))

            dailyWriteBundle.dailyHappyPointBundle = DailyHappyPointBundle(happyPointList.value.toMutableList(), happyPointList.key)
            response[dailyWriteBundle.day] = dailyWriteBundle
        }

        return response
    }

    private fun getDateTimeRange(year: Int, month: Int): LongRange {
        val startTime: Long
        val endTime: Long

        LocalDate.of(year, month, 1).run {
            startTime = toEpochDay()
            endTime = plusMonths(1).minusDays(1).toEpochDay()
        }

        return startTime..endTime
    }
}