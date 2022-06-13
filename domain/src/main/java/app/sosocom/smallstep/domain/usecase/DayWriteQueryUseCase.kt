package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.model.DayWrites
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class DayWriteQueryUseCase @Inject constructor(
    private val diaryUseCase: DiaryQueryUseCase
) {
    suspend operator fun invoke(year: Int, month: Int): Map<Int, DayWrites> {
        val dateTimeRange = getDateTimeRange(year, month)

        val diaryList = diaryUseCase(dateTimeRange)

        val response = HashMap<Int, DayWrites>()
        for(diary in diaryList) {
            val day = diary.baseDate.dayOfMonth
            val dayWrites = response.getOrDefault(day, DayWrites(day))

            dayWrites.diary = diary
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