package com.example.cs496_pj2_ui.retrofitService

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: String?
)

data class LoginRequest(
    val id: String?,
    val pw: String?,
    val kakaoID: String?
)