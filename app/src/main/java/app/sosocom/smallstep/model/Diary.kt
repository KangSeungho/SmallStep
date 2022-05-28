package app.sosocom.smallstep.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 일기 정보
 */
@Parcelize
data class Diary(
    val title: String,
    val content: String,
    val createAt: Long
) : Parcelable