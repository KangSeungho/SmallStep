package app.sosocom.smallstep.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sosocom.smallstep.domain.model.*
import app.sosocom.smallstep.domain.usecase.DailyWriteQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dailyWriteQueryUseCase: DailyWriteQueryUseCase
) : ViewModel() {
    // 월별 작성 데이터
    private val _monthWrites = MutableLiveData<Map<Int, DailyWriteBundle>>()
    val monthWrites: LiveData<Map<Int, DailyWriteBundle>> = _monthWrites

    // 선택된 날의 작성 데이터
    private val _selDailyWriteBundle = MutableLiveData<DailyWriteBundle?>()
    val selDailyWriteBundle: LiveData<DailyWriteBundle?> = _selDailyWriteBundle

    suspend fun getMonthWrites(year: Int, month: Int) {
        _monthWrites.postValue(dailyWriteQueryUseCase.invoke(year, month))
    }

    fun setSelectedDate(date: LocalDate) {
        val dayWrites = monthWrites.value?.get(date.dayOfMonth)
        _selDailyWriteBundle.postValue(dayWrites)
    }
}