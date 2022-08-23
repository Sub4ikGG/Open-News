package dev.efremovkirill.opennews.retrofit

import dev.efremovkirill.opennews.data.NewsModel
import retrofit2.Response

class Repository {
    suspend fun getNews(): Response<NewsModel> {
        return RetrofitInstance.api.getNews()
    }
}