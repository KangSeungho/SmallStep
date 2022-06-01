package app.sosocom.smallstep.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import app.sosocom.smallstep.data.entity.DiaryEntity

@Database(
    entities = [DiaryEntity::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "small_step_db"
    }

    abstract val diaryDao: DiaryDao
}