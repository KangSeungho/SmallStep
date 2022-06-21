package app.sosocom.smallstep.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.sosocom.smallstep.data.entity.TodoEntity

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity)

    @Query("SELECT * from todo where :startTime <= baseDate and baseDate <= :endTime")
    suspend fun getAllTodo(startTime: Long, endTime: Long): List<TodoEntity>

}