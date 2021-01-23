package com.kaizensundays.particles.web

import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 * Created: Sunday 9/6/2020, 3:52 PM Eastern Time
 *
 * @author Sergey Chuykov
 */
object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        val context = ClassPathXmlApplicationContext("/jetty.xml")
        context.registerShutdownHook()
    }

}