package info.aliahmed.voxmartcountrycode.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import info.aliahmed.voxmartcountrycode.database.CountryCodeDao
import info.aliahmed.voxmartcountrycode.database.CountryCodeDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CountryCodeDatabase {
        return Room.databaseBuilder(
            appContext,
            CountryCodeDatabase::class.java,
            "CountryCode"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(countryCodeDatabase: CountryCodeDatabase): CountryCodeDao {
        return countryCodeDatabase.countryCodeDao
    }

}