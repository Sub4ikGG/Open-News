package dev.efremovkirill.opennews.di

import dagger.Component
import dev.efremovkirill.opennews.MainActivity
import dev.efremovkirill.opennews.fragments.MainFragment
import dev.efremovkirill.opennews.recyclerview.NewsAdapter


@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(newsAdapter: NewsAdapter)
}