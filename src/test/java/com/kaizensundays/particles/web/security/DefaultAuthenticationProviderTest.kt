package com.kaizensundays.particles.web.security

import org.junit.Test
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import kotlin.test.assertEquals

/**
 * Created: Monday 9/7/2020, 5:19 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class DefaultAuthenticationProviderTest {

    private val provider = DefaultAuthenticationProvider()

    private val users = "nice:oops;one:eleven;seven:seventeen"

    @Test
    fun toUserMap() {

        val users = provider.toUserMap(users)

        assertEquals(3, users.size)
        assertEquals("oops", users["nice"]?.password)
        assertEquals("eleven", users["one"]?.password)
        assertEquals("seventeen", users["seven"]?.password)
    }

    @Test
    fun findUser() {

        provider.users = users
        provider.start()

        val token = UsernamePasswordAuthenticationToken("nice", "oops")

        val user = provider.findUser("nice", token)

        assertEquals("nice", user.username)
    }


}