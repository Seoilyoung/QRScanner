package com.example.administrator.qrcode.Network;

import com.example.administrator.qrcode.Model.QRData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by seoil on 2016-12-07.
 */
public interface ServerInterface {

    @GET("verifyqr")
    Call<QRData> verifyqr (@Query("USER_MAIL") String mail, @Query("HASHKEY") String hashkey, @Query("HASH_TYPE") String type);
}
