package red.courses.mvvm_room_dagger.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import red.courses.mvvm_room_dagger.data.database.dao.QuoteDao
import red.courses.mvvm_room_dagger.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDataBase : RoomDatabase() {


    abstract fun getQuoteDao():QuoteDao


}