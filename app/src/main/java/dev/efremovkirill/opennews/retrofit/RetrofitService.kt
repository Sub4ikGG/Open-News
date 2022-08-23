package dev.efremovkirill.opennews.retrofit

import dev.efremovkirill.opennews.data.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("everything?q=news&apiKey=dfc6b47c97964b7ab682c97f72aadbf7&language=en&from=2022-08-22&language=en")
    suspend fun getNews(): Response<NewsModel>
}