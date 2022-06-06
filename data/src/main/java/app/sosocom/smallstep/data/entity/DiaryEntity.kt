package app.sosocom.smallstep.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary")
data class DiaryEntity(
    @PrimaryKey val id: Int? = null,    // ID
    val title: String,                  // 제목
    val content: String,                // 내용
    val baseDate: Long,                 // 기준 날짜
    val createdAt: Long                 // 생성 시각
)
