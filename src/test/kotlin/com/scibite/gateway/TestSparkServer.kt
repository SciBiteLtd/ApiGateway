package com.scibite.gateway

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.junit.Assert
import org.junit.Test

class TestSparkServer {
    @Test
    fun testDecodeJwt() {

        val secret = "my-secret-token-to-change-in-production".toByteArray()
        val compact = Jwts.builder().setSubject("admin").signWith(SignatureAlgorithm.HS512, secret).compact()

        println(compact)

        val bearer = compact;
        val expected = "{sub=admin}"

        Assert.assertEquals(expected, decodeJwt(bearer))
    }
}
