package app.sosocom.smallstep.data.data_source

import androidx.room.*
import app.sosocom.smallstep.data.entity.TodoEntity

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity): Long

    @Query("SELECT * from todo where :startTime <= baseDate and baseDate <= :endTime")
    suspend fun getAllTodo(startTime: Long, endTime: Long): List<TodoEntity>

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)
}