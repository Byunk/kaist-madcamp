package com.example.cs496_pj2_ui.service

import com.example.cs496_pj2_ui.service.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitInterface {

    //Login
    @POST("/user/login")
    fun executeLogin(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/user/signup")
    fun executeSignup(@Body signupRequest: SignupRequest): Call<ResponseCode>

    //User Data
    @GET("/user/{id}")
    fun getUserById(@Path("id") id: String): Call<UserData>

    @GET("/user/friends/{id}")
    fun getUserFriends(@Path("id") id: String): Call<ArrayList<String>>

    @GET("/user/schedule/{id}/{year}/{month}")
    fun getUserMonthlySchedule(@Path("id") id: String, @Path("year") year: Int, @Path("month") month: Int): Call<ArrayList<ScheduleData>>

    @GET("/user/schedule/date/{id}/{year}/{month}/{date}")
    fun getUserDailySchedule(@Path("id") id: String, @Path("year") year: Int, @Path("month") month: Int, @Path("date") date: Int): Call<ArrayList<ScheduleData>>

    //@GET("/")
    //fun getChatsById(@Path("id") id: String): Call<ArrayList<Chat>>

    //Promise

    //Board
    @GET("/board")
    fun getBoards(): Call<ArrayList<Board>>

    @POST("/board/write")
    fun writeBoard(@Body writeBoardRequest: WriteBoardRequest): Call<ResponseCode>

    @GET("/board/{id}")
    fun findBoardById(@Path("id") id: String): Call<Board>

    @POST("/board/delete")
    fun deleteBoard(@Body deleteBoardRequest: DeleteBoardRequest): Call<ResponseCode>

    @POST("/board/comment")
    fun writeComment(@Body writeCommentRequest: WriteCommentRequest): Call<ResponseCode>

    @POST("/board/comment/delete")
    fun deleteComment(@Body deleteCommentRequest: DeleteCommentRequest): Call<ResponseCode>
}