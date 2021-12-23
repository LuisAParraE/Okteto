package com.example.styleoverflow.styleoverflow.validators

import org.springframework.stereotype.Service
import java.util.function.Predicate

@Service
class NamingValidation : Predicate<String> {
    override fun test(t: String): Boolean {
        //Lenght validation
        val validNaming = "\\p{Alpha}{1,40}".toRegex()

        if (!t.matches(validNaming)) return false

        //Blank Space Validation
        if (t.contains(" "))

            if (t.startsWith(" ") or t.endsWith(" "))
                return false

            var  blankSpaces: Int = 0
            for (letter in t){
                if(letter.equals(" "))
                    blankSpaces++
            }

            if (blankSpaces > 1) return false

        return true
    }
}