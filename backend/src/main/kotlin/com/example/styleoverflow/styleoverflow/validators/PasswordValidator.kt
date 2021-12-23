package com.example.styleoverflow.styleoverflow.validators

import org.springframework.stereotype.Service
import java.util.function.Predicate

@Service
class PasswordValidator : Predicate<String> {
    override fun test(t: String): Boolean {
        val passwordRegex = "\\p{Graph}{8}".toRegex()
        return (t.matches(passwordRegex))
    }
}
