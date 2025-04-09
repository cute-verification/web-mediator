package io.github.gdrfgdrf.cuteverification.web.mediator.event.listener

import com.google.common.eventbus.Subscribe
import io.github.gdrfgdrf.cuteverification.web.mediator.event.bus.EventCenter
import io.github.gdrfgdrf.cuteverification.web.mediator.event.user.UserLoginEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.user.UserJoinEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.network.HttpObserver
import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.parser.UserLoginIdParser
import io.github.gdrfgdrf.cuteverification.web.mediator.network.service.UserService
import io.github.gdrfgdrf.cuteverification.web.mediator.rxjava.defaultSubscribe
import io.github.gdrfgdrf.cuteverification.web.mediator.user.UserLoginDTO

object UserJoinEventListener {
    init {
        EventCenter.register(this)
    }

    @Subscribe
    fun userJoin(userJoinEvent: UserJoinEvent) {
        val user = userJoinEvent.user
        val username = user.username
        val code = user.code
        val platform = user.platform
        val ip = user.ip

        val userLoginDto = UserLoginDTO(username, code, platform, ip)
        UserService.instance().userLogin(userLoginDto)
            .defaultSubscribe()
            .subscribe(HttpObserver.Builder()
                .error {
                    UserLoginEvent.Failed.main(user).post()
                }
                .finish { apiResult ->
                    println(apiResult.code)

                    apiResult.ifSuccess {
                        val userId = UserLoginIdParser.parse(apiResult)
                        user.id = userId

                        UserLoginEvent.Success.main(user).post()
                        return@ifSuccess
                    }
                    if (!apiResult.success()) {
                        UserLoginEvent.Failed.main(user).post()
                    }
                }
                .build())
    }
}