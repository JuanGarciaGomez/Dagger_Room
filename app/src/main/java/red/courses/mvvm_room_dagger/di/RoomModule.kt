package red.courses.mvvm_room_dagger.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import red.courses.mvvm_room_dagger.data.database.QuoteDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTE_DATABASE_NAME = "quote_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, QuoteDataBase::class.java,
            QUOTE_DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideQuoteDao(db:QuoteDataBase) = db.getQuoteDao()


}