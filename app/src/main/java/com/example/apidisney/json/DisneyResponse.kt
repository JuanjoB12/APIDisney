package com.example.apidisney.json

data class DisneyResponse(
    val count: Int,
    val `data`: List<Data>,
    val nextPage: String,
    val totalPages: Int
)