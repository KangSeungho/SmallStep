package app.sosocom.smallstep.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 일기 정보
 */
@Parcelize
data class Diary(
    val id: Int? = null,            // ID
    val title: String,              // 제목
    val content: String,            // 내용
    val baseDate: LocalDate,        // 기준 날짜
    val createdAt: LocalDateTime    // 생성 시각
) : Parcelable