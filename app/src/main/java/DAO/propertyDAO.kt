package DAO

import Data.propertyEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface propertyDAO {
        @Insert
        suspend fun insert(property: propertyEntity)

        @Delete
        suspend fun delete(property: propertyEntity)

        @Query("SELECT * FROM properties")
        fun getAllProperties(): List<propertyEntity>

        @Query("DELETE FROM properties WHERE id = :idProperty")
        fun deleteById(idProperty: Long)

}

