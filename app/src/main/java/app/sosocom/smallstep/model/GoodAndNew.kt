package app.sosocom.smallstep.model

/**
 * Good And New 정보
 */
data class GoodAndNew(
    // 좋은 일 혹은 새로운 일
    val content: String,

    // 타이머가 돌아가고 작성하는데 걸린 시간 (s 단위)
    val time: Long,

    // 작성 시간
    val createAt: Long
)