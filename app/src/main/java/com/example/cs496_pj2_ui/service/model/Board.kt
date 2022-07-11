package com.example.cs496_pj2_ui.service.model

import com.google.gson.annotations.SerializedName

data class Board(
    @SerializedName("username")
    val username: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("content")
    val content: String?,

    @SerializedName("votes")
    val votes: Int?,

    @SerializedName("views")
    val views: Int,

    @SerializedName("comments")
    val comments: ArrayList<Comment>?,
)

data class Comment(
    @SerializedName("id")
    val id: String,

    @SerializedName("content")
    val content: String?,

    @SerializedName("vote")
    val vote: Int?
)

data class WriteBoardRequest(
    val id: String,
    val username: String,
    val title: String,
    val content: String,
)

data class DeleteBoardRequest(
    val _id: String,
    val id: String
)

data class WriteCommentRequest(
    val _id: String,
    val id: String,
    val comment: Comment
)

data class DeleteCommentRequest(
    val id1: String, // Board id
    val id2: String, // Comment
    val id3: String // User
)