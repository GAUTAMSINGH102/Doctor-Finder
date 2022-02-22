package com.example.doctorfinder.Api;

import com.example.doctorfinder.Responses.GetDoctorInfoResponse;
import com.example.doctorfinder.Responses.GetOfferingResponse;
import com.example.doctorfinder.Responses.GetSpecialitiesResponse;
import com.example.doctorfinder.Responses.GetSymptomsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("getdoctorinfo.php")
    Call<GetDoctorInfoResponse> getdoctorinfo();

    @GET("getsymptoms.php")
    Call<GetSymptomsResponse> getsymptoms();

    @GET("getspecialities.php")
    Call<GetSpecialitiesResponse> getspecialities();

    @GET("getoffering.php")
    Call<GetOfferingResponse> getoffering();

    @FormUrlEncoded
    @POST("getparticulardoctor.php")
    Call<GetDoctorInfoResponse> getparticulardoctor(@Field("issue") String issue);

}
