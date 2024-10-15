package com.application.welearnjava.Api

import com.application.welearnjava.Model.DailyQuestion
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("daily_questions.php")
    fun getDailyQuestions(): Call<List<DailyQuestion>>

}