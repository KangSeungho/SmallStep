package app.sosocom.smallstep.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey val id: Int? = null,    // ID
    val content: String,                // 작업 내역
    val isComplete: Boolean,            // 작업 완료 상태
    val baseDate: Long,                 // 기준 날짜
    val createdAt: Long                  // 작성 시각
)
