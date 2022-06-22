package app.sosocom.smallstep.domain.model

/**
 * 일별 작성 내역
 */
data class DayWrites(
    val day: Int,
    var dailyTodoBundle: DailyTodoBundle? = null,
    var happyPointList: HappyPointList? = null,
    var goodAndNew: GoodAndNew? = null,
    var diary: Diary? = null
)
