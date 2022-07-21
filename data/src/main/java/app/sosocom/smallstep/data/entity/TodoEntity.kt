package app.sosocom.smallstep.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey                                 val id: Int? = null,       // ID
    @ColumnInfo(name = "content") @NonNull      val content: String,       // 작업 내역
    @ColumnInfo(name = "is_complete") @NonNull  val isComplete: Boolean,   // 작업 완료 상태
    @ColumnInfo(name = "base_date") @NonNull    val baseDate: Long,        // 기준 날짜
    @ColumnInfo(name = "create_at") @NonNull    val createdAt: Long        // 작성 시각
)
