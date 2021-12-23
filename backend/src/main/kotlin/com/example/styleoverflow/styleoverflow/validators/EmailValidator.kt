package com.example.styleoverflow.styleoverflow.validators

import org.springframework.stereotype.Service
import java.util.function.Predicate

@Service
class EmailValidator : Predicate<String> {
    override fun test(t: String): Boolean {
        val mailRegex = "\\p{Graph}+@\\p{Graph}+\\.\\p{Graph}+".toRegex()
        return mailRegex.matches(t)
    }
}
