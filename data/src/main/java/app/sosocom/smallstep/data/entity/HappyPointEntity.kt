package app.sosocom.smallstep.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "happy_point")
data class HappyPointEntity(
    @PrimaryKey val id: Int? = null,        // ID
    val content: String,                    // 행동
    val comment: String,                    // 설명
    val point: Float,                       // 점수 (0.0 ~ 5.0)
    val baseDate: Long,                     // 기준 날짜
    val createAt: Long                      // 작성 시각
)
