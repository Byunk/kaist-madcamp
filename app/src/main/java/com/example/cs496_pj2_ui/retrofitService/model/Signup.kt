package com.example.cs496_pj2_ui.retrofitService

data class SignupRequest(
    val id: String,
    val pw: String,
    val username: String,
    val kakaoid: String?
)