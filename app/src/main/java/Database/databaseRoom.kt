package com.example.learnapp


import DAO.DAOsignUp
import Data.DataEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities=[DataEntity::class],version=1, exportSchema = true)
abstract class databaseRoom:RoomDatabase(){
    abstract fun dbDao(): DAOsignUp

    companion object{
        @Volatile
        private  var INSTANCE:databaseRoom?=null

        @Synchronized
        fun getDatabase(context: Context):databaseRoom{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE=Room.databaseBuilder(
                        context.applicationContext,
                        databaseRoom::class.java,"signUpDB"
                    ).build()
                }
            }
             return INSTANCE!!
        }
    }

}