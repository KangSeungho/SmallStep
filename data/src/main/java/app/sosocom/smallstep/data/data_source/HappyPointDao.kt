package app.sosocom.smallstep.data.data_source

import androidx.room.*
import app.sosocom.smallstep.data.entity.HappyPointEntity
import app.sosocom.smallstep.data.entity.TodoEntity

@Dao
interface HappyPointDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHappyPoint(happyPoint: HappyPointEntity): Long

    @Query("SELECT * from happy_point where :startTime <= base_date and base_date <= :endTime")
    suspend fun getAllHappyPoint(startTime: Long, endTime: Long): List<HappyPointEntity>

    @Delete
    suspend fun deleteHappyPoint(happyPoint: HappyPointEntity)
}