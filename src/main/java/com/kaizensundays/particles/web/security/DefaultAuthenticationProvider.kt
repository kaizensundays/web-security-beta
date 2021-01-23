package com.kaizensundays.particles.web.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 * Created: Monday 9/7/2020, 5:11 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
class DefaultAuthenticationProvider : AbstractUserDetailsAuthenticationProvider() {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val userMap = mutableMapOf<String, User>()

    var users = "?"

    fun toUserMap(s: String): Map<String, User> {

        return s.split(";")
                .map { token -> token.split(":") }
                .filter { list -> list.size == 2 }
                .map { list -> list[0] to User(list[0], list[1], emptyList()) }
                .toMap()
    }

    override fun retrieveUser(username: String, token: UsernamePasswordAuthenticationToken): UserDetails {

        val user = userMap[username]

        @Suppress("FoldInitializerAndIfToElvis")
        if (user == null) {
            throw UsernameNotFoundException("username='$username'")
        }

        if (token.credentials == user.password) {
            return user
        }

        throw BadCredentialsException("username='$username'")
    }

    fun findUser(username: String, token: UsernamePasswordAuthenticationToken): UserDetails {
        return retrieveUser(username, token)
    }

    override fun additionalAuthenticationChecks(userDetails: UserDetails, token: UsernamePasswordAuthenticationToken) {

    }

    fun start() {

        val users = toUserMap(users)

        userMap.putAll(users)

        log.info("Started")
    }

    fun stop() {

        log.info("Stopped")
    }

}