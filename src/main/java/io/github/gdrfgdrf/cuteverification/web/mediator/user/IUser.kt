package io.github.gdrfgdrf.cuteverification.web.mediator.user

import io.github.gdrfgdrf.cuteverification.web.mediator.enums.IdentificationPlatforms

interface IUser {
    var id: String?
    var username: String
    var code: String
    var platform: IdentificationPlatforms
    var ip: String
}