package app.sosocom.smallstep.data.data_source

import androidx.room.*
import app.sosocom.smallstep.data.entity.TodoEntity

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity): Long

    @Query("SELECT * from todo where :startTime <= base_date and base_date <= :endTime")
    suspend fun getAllTodo(startTime: Long, endTime: Long): List<TodoEntity>

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)
}