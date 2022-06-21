package app.sosocom.smallstep.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 날짜 기준으로 정의한 할 일 목록
 */
@Parcelize
data class TodoList(
    val todoList: List<Todo>,       // 할 일 목록
    val baseDate: LocalDate         // 기준 날짜
) : Parcelable

/**
 * 할 일 정보
 */
@Parcelize
data class Todo(
    val id: Int? = null,
    val content: String,            // 작업 내역
    val isComplete: Boolean,        // 작업 완료 상태
    val baseDate: LocalDate,        // 기준 날짜
    val createdAt: LocalDateTime    // 작성 시각
) : Parcelable