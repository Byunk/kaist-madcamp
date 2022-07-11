package com.example.cs496_pj2_ui.service.model

data class PromiseRequest (

    val senderId: String,

    // Should not be null
    val receiverId: String,

    val year: Int,
    val month: Int,
    val date: Int,
    val hour: Int,
    val minute: Int,

    val comment: String?
)

data class PromiseResponse (

    val promiseId: String,
    val response: Boolean,

    )

data class Promise (
    val senderName: String,
    val senderImgUrl: String?,

    val dateString: String,
    val contents: String?,
)