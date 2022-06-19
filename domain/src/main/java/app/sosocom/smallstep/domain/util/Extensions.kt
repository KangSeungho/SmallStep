package app.sosocom.smallstep.domain.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalDateTime.ofInstant
import java.time.OffsetDateTime
import java.util.*

fun LocalDateTime.toEpochMilli() = toInstant(OffsetDateTime.now().offset).toEpochMilli()

class LocalDateTime {
    companion object {
        fun ofEpochMilli(epochMilli: Long): LocalDateTime {
            return ofInstant(Instant.ofEpochMilli(epochMilli), TimeZone.getDefault().toZoneId())
        }
    }
}