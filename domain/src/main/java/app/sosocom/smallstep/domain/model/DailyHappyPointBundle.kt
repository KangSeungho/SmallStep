package app.sosocom.smallstep.domain.model

import android.os.Parcelable
import androidx.annotation.FloatRange
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 날짜 기준으로 행복 점수 목록들
 */
@Parcelize
data class DailyHappyPointBundle(
    val happyPointList: MutableList<HappyPoint>,   // 행복 점수 리스트
    val baseDate: LocalDate                        // 기준 날짜
) : Parcelable

/**
 * 행복 점수 정보
 */
@Parcelize
data class HappyPoint(
    val id: Int? = null,
    val content: String,                            // 행동
    val comment: String,                            // 설명
    @FloatRange(from = 0.0, to = 5.0)
    val point: Float,                               // 점수 (0.0 ~ 5.0)
    val baseDate: LocalDate,                        // 기준 날짜
    val createdAt: LocalDateTime                    // 작성 시각
) : Parcelable