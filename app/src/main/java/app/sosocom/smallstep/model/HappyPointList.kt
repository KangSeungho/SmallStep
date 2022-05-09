package app.sosocom.smallstep.model

import androidx.annotation.FloatRange

data class HappyPointList(
    val happyPointList: List<HappyPoint>,
    val createAt: Long
)

data class HappyPoint(
    // 행동
    val content: String,

    // 설명
    val comment: String,

    // 점수 (0.0 ~ 5.0)
    @FloatRange(from = 0.0, to = 5.0)
    val point: Float
)