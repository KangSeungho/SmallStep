package app.sosocom.smallstep.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sosocom.smallstep.domain.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    // 월별 작성 데이터
    private val _monthWrites = MutableLiveData<Map<Int, DayWrites>>()
    val monthWrites: LiveData<Map<Int, DayWrites>> = _monthWrites

    // 선택된 날의 작성 데이터
    private val _selDayWrites = MutableLiveData<DayWrites?>()
    val selDayWrites: LiveData<DayWrites?> = _selDayWrites

    fun getMonthWrites(year: Int, month: Int) {
        // TODO : 아래는 임시 데이터
        _monthWrites.value = listOf(
            DayWrites(
                3,
                TodoList(
                    listOf(
                        Todo("머리 자르기", true)
                    ),
                    1243L
                ),
                HappyPointList(
                    listOf(
                        HappyPoint("일하기", "개발 꿀잼", 4.5F),
                        HappyPoint("머리 자르기", "생각보다 머리가 이쁘게 잘 됐다", 5.0F)
                    ),
                    125124L
                ),
                GoodAndNew("퇴근하고 이발소가기 위해 전동 킥보드를 탔는데 재밌었다!", 1L, 12345L),
                Diary(title = "신난다~!", content = "내일 모레는 어린이날이다!", createAt = 12345L)
            ),
            DayWrites(
                5,
                TodoList(
                    listOf(
                        Todo("SmallStep 메인 화면 개발하기", true),
                        Todo("밀리의 서재로 책 읽기", false)
                    ),
                    123),
                null,
                null,
                Diary(title = "오늘은 어린이날!", content = "오늘은 어린이날이다. 하지만 어린이들보다 어른들이 더 좋아하는 날이다.", createAt = 12345L)
            )
        ).associateBy { it.day }
    }

    fun setSelDay(day: Int) {
        val dayWrites = monthWrites.value?.get(day)
        _selDayWrites.value = dayWrites
    }
}