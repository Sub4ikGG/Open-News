package dev.efremovkirill.opennews.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.efremovkirill.opennews.data.Article
import dev.efremovkirill.opennews.retrofit.Repository
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repository = Repository()
    val newsLiveData: MutableLiveData<List<Article>> = MutableLiveData()
    private var scope = CoroutineScope(Dispatchers.IO + CoroutineName("MainViewModel"))

    init {
        scope.launch {
            try {
                getNews()
            }
            catch (e: Exception) {
                Log.d("Bug", e.message.toString())
            }
        }
    }

    suspend fun getNews() {
        val news = repository.getNews()
        if(news.isSuccessful && news.body() != null)
            newsLiveData.postValue(news.body()!!.articles)
    }
}