package com.kaizensundays.particles.web.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.context.WebApplicationContext


/**
 * Created: Sunday 9/6/2020, 4:03 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
@Configuration
@EnableWebSecurity
open class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var context: WebApplicationContext

    @Autowired
    lateinit var authenticationProvider: DefaultAuthenticationProvider

    @Bean
    open fun initializer(): SecurityInitializer {
        val initializer = SecurityInitializer()
        initializer.onStartup(context.servletContext)
        return initializer
    }

    @Autowired
    open fun configureAuthManager(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider)
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests().antMatchers("/ping").permitAll()
                .and().authorizeRequests().antMatchers("/**").authenticated()
                .and().formLogin()
    }

}