package com.example.styleoverflow.styleoverflow.genericExceptions

import java.lang.RuntimeException
import org.springframework.web.bind.annotation.ControllerAdvice
import com.example.styleoverflow.styleoverflow.genericExceptions.BRequestException
import org.springframework.http.ResponseEntity
import com.example.styleoverflow.styleoverflow.genericExceptions.GenericExceptionClass
import org.springframework.http.HttpStatus
import java.time.ZonedDateTime
import java.time.ZoneId

class GenericExceptionClass(
    val message: String?,
    val httpStatus: HttpStatus,
    val timestamp: ZonedDateTime
)