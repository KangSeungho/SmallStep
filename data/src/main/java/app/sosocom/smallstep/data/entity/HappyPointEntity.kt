package app.sosocom.smallstep.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "happy_point")
data class HappyPointEntity(
    @PrimaryKey                                 val id: Int? = null,           // ID
    @ColumnInfo(name = "content") @NonNull      val content: String,           // 행동
    @ColumnInfo(name = "comment") @NonNull      val comment: String,           // 설명
    @ColumnInfo(name = "point") @NonNull        val point: Float,              // 점수 (0.0 ~ 5.0)
    @ColumnInfo(name = "base_date") @NonNull    val baseDate: Long,            // 기준 날짜
    @ColumnInfo(name = "created_at") @NonNull   val createdAt: Long            // 작성 시각
)
