package app.sosocom.smallstep.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sosocom.smallstep.domain.model.*
import app.sosocom.smallstep.domain.usecase.DayWriteQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun setSelDay(day: Int) {
        val dayWrites = monthWrites.value?.get(day)
        _selDayWrites.postValue(dayWrites)
    }
}