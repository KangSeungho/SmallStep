package app.sosocom.smallstep.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiaryEntity(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val createAt: Long
)
