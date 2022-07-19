package app.sosocom.smallstep.data.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey val id: Int? = null,    // ID
    @NonNull val content: String,       // 작업 내역
    @NonNull val isComplete: Boolean,   // 작업 완료 상태
    @NonNull val baseDate: Long,        // 기준 날짜
    @NonNull val createdAt: Long        // 작성 시각
)
