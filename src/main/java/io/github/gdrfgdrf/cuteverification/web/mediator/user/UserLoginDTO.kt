package io.github.gdrfgdrf.cuteverification.web.mediator.user

import io.github.gdrfgdrf.cuteverification.web.mediator.enums.IdentificationPlatforms

data class UserLoginDTO(
    val username: String,
    val code: String,
    val platform: IdentificationPlatforms,
    val ip: String
) {
}