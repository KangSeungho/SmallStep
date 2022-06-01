package app.sosocom.smallstep.di

import app.sosocom.smallstep.data.repository.DiaryRepositoryImpl
import android.content.Context
import androidx.room.Room
import app.sosocom.smallstep.data.data_source.Database
import app.sosocom.smallstep.domain.repository.DiaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            Database.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDiaryRepository(db: Database): DiaryRepository {
        return DiaryRepositoryImpl(db.diaryDao)
    }

}