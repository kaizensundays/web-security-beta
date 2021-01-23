package com.kaizensundays.particles.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created: Sunday 9/6/2020, 4:07 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
@RestController
class DefaultRestController {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @RequestMapping("/ping")
    fun ping(): String {
        return "Ok"
    }

    @RequestMapping("/ok")
    fun ok(): String {
        return "Ok"
    }

    fun start() {

        logger.info("Started")
    }

    fun stop() {

        logger.info("Stopped")
    }

}