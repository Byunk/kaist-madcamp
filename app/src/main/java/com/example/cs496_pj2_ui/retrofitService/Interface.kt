package com.example.cs496_pj2_ui.retrofitService

import com.example.cs496_pj2_ui.retrofitService.model.ResponseCode
import com.example.cs496_pj2_ui.retrofitService.model.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitInterface {

    @POST("/user/login")
    fun executeLogin(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/user/signup")
    fun executeSignup(@Body signupRequest: SignupRequest): Call<ResponseCode>

    @GET("/user/{id}")
    fun getUserById(@Path("id") id: String): Call<UserData>

    @GET("/user/friends/{id}")
    fun getUserFriends(@Path("id") id: String): Call<ArrayList<String>>

    //@GET("/user/schedule/{id}/{year}/{month}")
    //fun getUserMonthlySchedule(@Path("id") id: String, @Path("year") year: Int, @Path("month") month: Int): Call<ArrayList<Schedule>>

    //@GET("/user/schedule/date/{id}/{year}/{month}/{date}")
    //fun getUserDailySchedule(@Path("id") id: String, @Path("year") year: Int, @Path("month") month: Int, @Path("date") date: Int): Call<ArrayList<Schedule>>

    //@GET("/")
    //fun getChatsById(@Path("id") id: String): Call<ArrayList<Chat>>

    //@GET("/")
    //fun getBoards(): Call<ArrayList<Board>>
}