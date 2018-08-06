package com.scibite.gateway

import spark.kotlin.*

fun main(args: Array<String>) {
    val http: Http = ignite()

    http.get("/") {
        "Hello Spark Kotlin!"
    }

    http.get("/api") {
        "My Api"
    }
}
