package io.github.gdrfgdrf.cuteverification.web.mediator.network.service

import io.github.gdrfgdrf.cuteverification.web.mediator.network.RetrofitClient
import io.github.gdrfgdrf.cuteverification.web.mediator.utils.GenericTypeResolver

open class ServiceCompanion<T> : GenericTypeResolver<T>() {
    private val instance: T = RetrofitClient.create(type)

    fun instance(): T {
        return instance
    }
}