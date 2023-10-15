package Database

import DAO.propertyDAO
import Data.propertyEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [propertyEntity::class], version =3)
abstract class propertyDatabase: RoomDatabase() {
        abstract fun propertyDao():propertyDAO
        companion object {
            @Volatile
            private var INSTANCE: propertyDatabase? = null
            fun getDatabase(context: Context): propertyDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        propertyDatabase::class.java,
                        "property_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }
