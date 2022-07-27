package app.sosocom.smallstep.data.data_source

import androidx.room.*
import app.sosocom.smallstep.data.entity.DiaryEntity

@Dao
interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiary(diary: DiaryEntity)

    @Query("SELECT * from diary where :startTime <= base_date and base_date <= :endTime")
    suspend fun getAllDiary(startTime: Long, endTime: Long): List<DiaryEntity>

    @Delete
    suspend fun deleteDiary(diary: DiaryEntity)
}