package com.blissapplications.osm.model.requestModels

import com.blissapplications.osm.errors.utils.ProjError

open class Result<out V>(val resultData: V? = null, val error: ProjError? = null) {

    companion object {
        // Factory methods
        fun <E : ProjError> error(ex: E) = Error<Nothing>(ex)

        fun <V : Any> of(value: V?, fail: (() -> Throwable) = { Throwable() }): Result<V> =
            value?.let { Success(it) } ?: error(fail())
    }

    open fun get(): V {
        when {
            resultData != null -> {
                return resultData
            }
            error != null -> {
                throw error
            }
            else -> {
                throw Exception("Empty result")
            }
        }
    }

    class Success<out V>(resultData: V) : Result<V>(resultData) {
        override fun get(): V = resultData!!

        override fun toString() = "[Success: $resultData]"

        override fun hashCode(): Int = resultData.hashCode()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Success<*> && resultData!! == other.resultData!!
        }
    }

    class Error<out V>(error: ProjError) : Result<V>(error = error) {

        override fun get(): V = throw error!!

        override fun toString() = "[Error: $error]"

        override fun hashCode(): Int = error.hashCode()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            return other is Error<*> && error!! == other.error!!
        }
    }
}