package com.example.styleoverflow.styleoverflow.appuser.exceptions

sealed class IOExeptions

class InputValidationException : IOExeptions() {
    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}
