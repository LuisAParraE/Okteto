package com.example.styleoverflow.styleoverflow.validators

import org.springframework.stereotype.Service
import java.util.function.Predicate

@Service
class ProjectDescriptionValidator: Predicate<String> {
    override fun test(t: String): Boolean {

        if ((t.length > 512) and t.isBlank()){ return false}

        return true
    }
}