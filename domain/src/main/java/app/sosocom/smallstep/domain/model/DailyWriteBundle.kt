package app.sosocom.smallstep.domain.model

/**
 * 일별 작성 내역
 */
data class DailyWriteBundle(
    val day: Int,
    var dailyTodoBundle: DailyTodoBundle? = null,
    var dailyHappyPointBundle: DailyHappyPointBundle? = null,
    var goodAndNew: GoodAndNew? = null,
    var diary: Diary? = null
)
