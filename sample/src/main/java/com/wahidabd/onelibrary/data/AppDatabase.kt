package com.wahidabd.onelibrary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wahidabd.onelibrary.data.note.local.NoteDao
import com.wahidabd.onelibrary.data.note.local.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null){
                INSTANCE = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "note.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

        fun destroyDatabase(){
            INSTANCE = null
        }
    }
}