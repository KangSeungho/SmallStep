package app.sosocom.smallstep.data.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "happy_point")
data class HappyPointEntity(
    @PrimaryKey val id: Int? = null,        // ID
    @NonNull val content: String,           // 행동
    @NonNull val comment: String,           // 설명
    @NonNull val point: Float,              // 점수 (0.0 ~ 5.0)
    @NonNull val baseDate: Long,            // 기준 날짜
    @NonNull val createdAt: Long            // 작성 시각
)
