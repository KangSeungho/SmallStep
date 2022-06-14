package app.sosocom.smallstep.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sosocom.smallstep.domain.model.*
import app.sosocom.smallstep.domain.usecase.DayWriteQueryUseCase
import com.kizitonwose.calendarview.model.CalendarDay
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dayWriteQueryUseCase: DayWriteQueryUseCase
) : ViewModel() {
    // 월별 작성 데이터
    private val _monthWrites = MutableLiveData<Map<Int, DayWrites>>()
    val monthWrites: LiveData<Map<Int, DayWrites>> = _monthWrites

    // 선택된 날의 작성 데이터
    private val _selDayWrites = MutableLiveData<DayWrites?>()
    val selDayWrites: LiveData<DayWrites?> = _selDayWrites

    suspend fun getMonthWrites(year: Int, month: Int) {
        _monthWrites.postValue(dayWriteQueryUseCase.invoke(year, month))
    }

    fun addDiary(diary: Diary) {
        val day = diary.baseDate.dayOfMonth
        val dayWrites = monthWrites.value?.get(day) ?: DayWrites(day, null, null, null, null)
        dayWrites.diary = diary

        val mutableMap = monthWrites.value?.toMutableMap() ?: mutableMapOf()
        mutableMap[day] = dayWrites

        _monthWrites.value = mutableMap

        // 만약 선택된 날짜를 갱신하면 데이터 갱신
        if(selDayWrites.value?.day == day) {
            setSelectedDate(diary.baseDate)
        }
    }

    fun setSelectedDate(date: LocalDate) {
        val dayWrites = monthWrites.value?.get(date.dayOfMonth)
        _selDayWrites.postValue(dayWrites)
    }
}