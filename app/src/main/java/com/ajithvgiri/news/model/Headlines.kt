package com.ajithvgiri.news.model

import java.io.Serializable

data class Headlines(
    var status: String,
    var totalResults: Int,
    var articles: ArrayList<Articles>
) : Serializable