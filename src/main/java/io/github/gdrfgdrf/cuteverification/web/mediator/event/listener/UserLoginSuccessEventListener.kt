package io.github.gdrfgdrf.cuteverification.web.mediator.event.listener

import com.google.common.eventbus.Subscribe
import io.github.gdrfgdrf.cuteverification.web.mediator.event.bus.EventCenter
import io.github.gdrfgdrf.cuteverification.web.mediator.event.user.GetUserRestrictedErrorEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.user.UserLoginEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.event.user.UserRestrictedEvent
import io.github.gdrfgdrf.cuteverification.web.mediator.network.finish
import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.parser.UserRestrictedParser
import io.github.gdrfgdrf.cuteverification.web.mediator.network.service.UserService
import io.github.gdrfgdrf.cuteverification.web.mediator.rxjava.defaultSubscribe

object UserLoginSuccessEventListener {
    init {
        EventCenter.register(this)
    }

    @Subscribe
    fun userLoginSuccess(userLoginSuccessEvent: UserLoginEvent.Success) {
        val id = userLoginSuccessEvent.id
        val username = userLoginSuccessEvent.username
        val code = userLoginSuccessEvent.code
        val ip = userLoginSuccessEvent.ip

        UserService.instance().userRestricted(id)
            .defaultSubscribe()
            .subscribe(finish { apiResult ->
                apiResult.ifSuccess {
                    val restricted = UserRestrictedParser.parse(apiResult)
                    if (restricted) {
                        UserRestrictedEvent.main(id, username, code, ip).post()
                    }
                }
                if (!apiResult.success()) {
                    GetUserRestrictedErrorEvent.main(id, username, code, ip).post()
                }
            })
    }

}