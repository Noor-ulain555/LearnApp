package DAO

import Data.DataEntity
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAOsignUp {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(signup: DataEntity)

    @Update
    suspend fun updateData(signup: DataEntity)

    @Delete
    suspend fun deleteData(signup: DataEntity)

    @Query("SELECT * FROM SignUp")
    fun getsignUp(): List<DataEntity>

}