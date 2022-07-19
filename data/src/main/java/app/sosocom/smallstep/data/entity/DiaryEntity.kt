package app.sosocom.smallstep.data.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary")
data class DiaryEntity(
    @PrimaryKey val id: Int? = null,    // ID
    @NonNull val title: String,         // 제목
    @NonNull val content: String,       // 내용
    @NonNull val baseDate: Long,        // 기준 날짜
    @NonNull val createdAt: Long        // 생성 시각 (저장 시간은 UTC-0, 실제 표시할 때는 사용자의 시간)
)