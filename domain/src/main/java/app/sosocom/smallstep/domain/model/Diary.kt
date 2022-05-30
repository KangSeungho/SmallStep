package app.sosocom.smallstep.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 일기 정보
 */
@Parcelize
data class Diary(
    val title: String,
    val content: String,
    val createAt: Long
) : Parcelable