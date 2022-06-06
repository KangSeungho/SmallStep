package app.sosocom.smallstep.domain.usecase

import app.sosocom.smallstep.domain.model.DayWrites
import java.util.*
import javax.inject.Inject

class DayWriteQueryUseCase @Inject constructor(
    private val diaryUseCase: DiaryQueryUseCase
) {
    suspend operator fun invoke(): Map<Int, DayWrites> {
        val diaryList = diaryUseCase()

        val response = HashMap<Int, DayWrites>()
        for(diary in diaryList) {
            val day = diary.baseDate.get(Calendar.DAY_OF_MONTH)
            val dayWrites = response.getOrDefault(day, DayWrites(day))

            dayWrites.diary = diary
            response[dayWrites.day] = dayWrites
        }

        return response
    }
}