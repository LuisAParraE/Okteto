package com.example.styleoverflow.styleoverflow.validators

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.function.Predicate

@Service
class DateValidator : Predicate<LocalDate> {
    override fun test(t : LocalDate) : Boolean {
        val str = t.toString()
        val dateRegex = "\\d{4}-\\d{2}-\\d{2}".toRegex()
        return ( str.matches(dateRegex) && !t.isAfter(LocalDate.now()) )
    }
}