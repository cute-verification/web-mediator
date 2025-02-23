package io.github.gdrfgdrf.cuteverification.web.mediator.event.listener

import com.google.common.eventbus.Subscribe
import io.github.gdrfgdrf.cuteverification.web.mediator.event.bus.EventCenter
import io.github.gdrfgdrf.cuteverification.web.mediator.event.user.UserLoginEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.user.UserJoinEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.network.HttpObserver
import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.parser.UserLoginIdParser
import io.github.gdrfgdrf.cuteverification.web.mediator.network.service.UserService
import io.github.gdrfgdrf.cuteverification.web.mediator.rxjava.defaultSubscribe

object UserJoinEventListener {
    init {
        EventCenter.register(this)
    }

    @Subscribe
    fun userJoin(userJoinEvent: UserJoinEvent) {
        val username = userJoinEvent.username
        val code = userJoinEvent.code
        val ip = userJoinEvent.ip

        UserService.instance().userLogin(username, code, ip)
            .defaultSubscribe()
            .subscribe(HttpObserver.Builder()
                .error {
                    UserLoginEvent.Failed.main(username, code, ip).post()
                }
                .finish { apiResult ->
                    apiResult.ifSuccess {
                        val userId = UserLoginIdParser.parse(apiResult)
                        UserLoginEvent.Success.main(userId, username, code, ip).post()
                        return@ifSuccess
                    }
                    if (!apiResult.success()) {
                        UserLoginEvent.Failed.main(username, code, ip).post()
                    }
                }
                .build())
    }
}