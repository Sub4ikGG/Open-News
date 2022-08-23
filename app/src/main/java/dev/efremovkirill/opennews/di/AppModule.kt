package dev.efremovkirill.opennews.di

import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }
}