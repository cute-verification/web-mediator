package io.github.gdrfgdrf.cuteverification.web.mediator.cache

import io.github.gdrfgdrf.cuteverification.web.mediator.event.admin.AdminLoginEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.network.HttpObserver
import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.parser.AdminLoginTokenParser
import io.github.gdrfgdrf.cuteverification.web.mediator.network.service.AdminService
import io.github.gdrfgdrf.cuteverification.web.mediator.rxjava.defaultSubscribe
import io.github.gdrfgdrf.cuteverification.web.mediator.utils.log
import io.github.gdrfgdrf.cuteverification.web.mediator.utils.logDebug
import io.github.gdrfgdrf.cuteverification.web.mediator.utils.logError
import java.util.concurrent.atomic.AtomicReference

object TokenCache {
    private var username: String? = null
    private var password: String? = null

    private val holder = AtomicReference<String>()

    fun refresh() {
        if (username.isNullOrBlank() || password.isNullOrBlank()) {
            throw IllegalArgumentException("Cannot refresh token, because one of the username or password is null")
        }

        "Refreshing token".logDebug()

        AdminLoginEvent.pre(username).post()

        AdminService.instance().login(username!!, password!!)
            .defaultSubscribe()
            .subscribe(HttpObserver.Builder()
                .error {
                    AdminLoginEvent.post(username, null).post()

                    "Network error".logError(it)
                }
                .finish { apiResult ->
                    apiResult.ifSuccess {
                        val token = AdminLoginTokenParser.parse(apiResult)
                        set(token)

                        AdminLoginEvent.post(username, apiResult).post()

                        "Token is refreshed".logDebug()
                    }
                    if (!apiResult.success()) {
                        "Refreshing token failed".log()
                        return@finish
                    }
                }
                .build().apply {
                    this.autoRefreshToken = false
                })
    }

    fun set(token: String?) {
        holder.set(token)
    }

    fun current(): String? {
        return holder.get()
    }

    fun username(username: String?) {
        this.username = username
    }

    fun password(password: String?) {
        this.password = password
    }
}