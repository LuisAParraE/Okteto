package com.example.styleoverflow.styleoverflow.validators

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.function.Predicate

@Service
class ProjectDateValidator{
    fun test(beginDate: LocalDate,endDate: LocalDate): Boolean {

        if (beginDate.isBefore(LocalDate.now()) or  endDate.isBefore(beginDate))
            return false

        return true
    }
}