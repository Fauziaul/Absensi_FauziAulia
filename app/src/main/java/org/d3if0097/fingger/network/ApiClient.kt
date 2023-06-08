package org.d3if0097.fingger.network

import org.d3if0097.fingger.model.ResponseLogin
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiClient {

    @FormUrlEncoded
    @POST("absensi/chekLogin.php")
    fun login(
        @Field("username") username : String,
        @Field("password") password : String
    ): Call<ResponseLogin>
}