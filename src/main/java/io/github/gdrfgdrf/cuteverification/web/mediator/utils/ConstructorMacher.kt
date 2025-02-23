package io.github.gdrfgdrf.cuteverification.web.mediator.utils

import java.lang.reflect.Constructor
import java.util.Arrays
import java.util.concurrent.ConcurrentHashMap

object ConstructorMacher {
    private val cache = ConcurrentHashMap<Class<*>, ConcurrentHashMap<Array<Class<out Any>?>, Constructor<*>>>()

    private fun find(type: Class<*>, vararg any: Any?): Constructor<*> {
        val constructors = type.getConstructors()
        for (constructor in constructors) {
            val parameterTypes = constructor.parameterTypes
            if (parameterTypes.size != any.size) {
                continue
            }

            var isMatch = true
            for (i in parameterTypes.indices) {
                val realParameter = any[i]
                val parameterType = parameterTypes[i]

                if (realParameter != null && !ClassRelationResolver.isImplement(
                        realParameter::class.java,
                    parameterType
                    )) {
                    isMatch = false
                    break
                }
            }

            if (isMatch) {
                return constructor
            }
        }

        throw NoSuchMethodException("No matching constructor found for parameters: " + any.contentToString())
    }

    fun cache(type: Class<*>, vararg any: Any?): Constructor<*> {
        val map = cache.computeIfAbsent(type) { _ ->
            ConcurrentHashMap()
        }

        val array = Arrays.stream(any)
            .map {
                if (it == null) {
                    null
                } else {
                    it::class.java
                }
            }
            .toList()
            .toTypedArray()
        if (map.containsKey(array)) {
            return map[array]!!
        }

        val constructor = find(type, *any)
        map[array] = constructor

        return constructor
    }
}