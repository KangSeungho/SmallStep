package app.sosocom.smallstep.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.sosocom.smallstep.data.entity.DiaryEntity

@Dao
interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiary(diary: DiaryEntity)

    @Query("SELECT * from diary")
    suspend fun getAllDiary(): List<DiaryEntity>

}