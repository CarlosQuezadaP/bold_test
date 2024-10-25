package com.bold.main_weather.presentation

import org.slf4j.LoggerFactory

class LoggerClass {
    private val logger = LoggerFactory.getLogger(LoggerClass::class.java)

    fun info() {
        logger.info("Mensaje de registro")
    }
}