package io.github.gdrfgdrf.cuteverification.web.mediator.utils

import java.lang.reflect.ParameterizedType

open class GenericTypeResolver<T> {
    protected val type: Class<T>
    init {
        val type = this::class.java.genericSuperclass
        if (type is ParameterizedType) {
            val rawType = type.rawType
            val actualTypeArguments = type.actualTypeArguments
            if (rawType is Class<*> && actualTypeArguments.isNotEmpty()) {
                this.type = actualTypeArguments[0] as Class<T>
            } else {
                throw UnsupportedOperationException()
            }
        } else {
            throw UnsupportedOperationException()
        }
    }
}