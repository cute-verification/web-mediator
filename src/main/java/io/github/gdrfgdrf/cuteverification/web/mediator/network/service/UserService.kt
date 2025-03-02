package io.github.gdrfgdrf.cuteverification.web.mediator.network.service

import io.github.gdrfgdrf.cuteverification.web.mediator.enums.IdentificationPlatforms
import io.github.gdrfgdrf.cuteverification.web.mediator.network.result.ApiResult
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("/api/v1/users/login")
    fun userLogin(
        @Query("username") username: String,
        @Query("code") code: String,
        @Query("platform") platform: IdentificationPlatforms,
        @Query("ip") ip: String
    ): Observable<ApiResult>

    @GET("/api/v1/users/restricted")
    fun userRestricted(
        @Query("id") id: String
    ): Observable<ApiResult>

    companion object : ServiceCompanion<UserService>()

}