package com.example.styleoverflow.styleoverflow.genericExceptions

import java.lang.RuntimeException
import org.springframework.web.bind.annotation.ControllerAdvice
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import org.springframework.http.ResponseEntity
import com.example.styleoverflow.styleoverflow.genericExceptions.GenericExceptionClass
import org.springframework.http.HttpStatus
import java.time.ZonedDateTime
import java.time.ZoneId

class BRequestException : RuntimeException {
    constructor(message: String?) : super(message) {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
}