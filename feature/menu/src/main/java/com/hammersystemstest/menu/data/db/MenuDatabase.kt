package com.hammersystemstest.menu.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hammersystemstest.menu.data.db.model.CategoryEntity
import com.hammersystemstest.menu.data.db.model.MealEntity

@Database(
    version = 1,
    entities = [
        MealEntity::class,
        CategoryEntity::class,
    ]
)
abstract class MenuDatabase : RoomDatabase() {

    abstract fun menuDao(): MenuDao

    companion object {
        private const val DB_NAME = "menu_db"
        private var instance: MenuDatabase? = null
        private val lock = Any()
        fun getInstance(application: Application): MenuDatabase {
            instance?.let { return it }
            synchronized(lock) {
                instance?.let { return it }
                val db = Room
                    .databaseBuilder(application, MenuDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                instance = db
                return db
            }
        }
    }
}