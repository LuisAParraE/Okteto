package com.example.styleoverflow.styleoverflow.genericExceptions

import java.lang.RuntimeException
import org.springframework.web.bind.annotation.ControllerAdvice
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import org.springframework.http.ResponseEntity
import com.example.styleoverflow.styleoverflow.genericExceptions.GenericExceptionClass
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.ZonedDateTime
import java.time.ZoneId

@ControllerAdvice
class BRequestHandler {
    @ExceptionHandler(value = [BRequestException::class])
    fun handleRequestException(e: BRequestException): ResponseEntity<Any> {
        val genericExceptionClass = GenericExceptionClass(
            e.message,
            HttpStatus.BAD_REQUEST,
            ZonedDateTime.now(ZoneId.of("Z"))
        )
        return ResponseEntity(genericExceptionClass, HttpStatus.BAD_REQUEST)
    }
}