package app.sosocom.smallstep.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.sosocom.smallstep.model.*

class MainViewModel : ViewModel() {
    // 월별 작성 데이터
    private val _monthWrites = MutableLiveData<List<DayWrites>>()
    val monthWrites: LiveData<List<DayWrites>> = _monthWrites

    // 선택된 날의 작성 데이터
    private val _selDayWrites = MutableLiveData<DayWrites>()
    val selDayWrites: LiveData<DayWrites> = _selDayWrites

    suspend fun getMonthWrites(year: Int, month: Int) {
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
                Diary("오늘은 어린이날!", "오늘은 어린이날이다. 하지만 어린이들보다 어른들이 더 좋아하는 날이다.", 12345L)
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
                Diary("오늘은 어린이날!", "오늘은 어린이날이다. 하지만 어린이들보다 어른들이 더 좋아하는 날이다.", 12345L)
            )
        )
    }

    fun setSelDay(day: Int) {
        val dayWrites = monthWrites.value?.get(day) ?: return
        _selDayWrites.value = dayWrites
    }
}