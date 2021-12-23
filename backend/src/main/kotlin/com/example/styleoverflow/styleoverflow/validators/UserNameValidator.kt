package com.example.styleoverflow.styleoverflow.validators

import org.springframework.stereotype.Service
import java.util.function.Predicate

@Service
class UserNameValidator : Predicate<String> {
    override fun test(t : String) : Boolean {
        val userNameRegex = "\\p{Alnum}{1,14}".toRegex()
        return (t.matches(userNameRegex))
    }

}