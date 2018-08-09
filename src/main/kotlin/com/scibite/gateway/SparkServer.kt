package com.scibite.gateway

import io.jsonwebtoken.Jwts
import org.apache.commons.lang.StringUtils
import spark.Request
import spark.kotlin.Http
import spark.kotlin.ignite

fun main(args: Array<String>) {
    val http: Http = ignite()

    http.get("/") {
        "Hello Spark Kotlin!"
    }

    http.get("/api") {
        "Successful!"
    }

    http.get("/api/currentUser") {
        "You are succesfully authenticated with following information: ${getUserInfo(request)}"
    }
}

fun getUserInfo(request: Request): String? {
    val jwt: String = when {
        request.headers().contains("Authorization") -> request.headers("Authorization").substring(7)
        request.cookies().containsKey("jwt") -> request.cookie("jwt")
        else -> return null
    }

    return decodeJwt(jwt);
}

fun decodeJwt(jwt: String): String? {
    try {
        val secret = "my-secret-token-to-change-in-production".toByteArray()
        val jws = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt)

        return jws.body.toString();
    } catch (ex: Exception) {
        ex.printStackTrace();
        return null;
    }
}

