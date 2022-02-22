package com.example.doctorfinder.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.doctorfinder.Adapters.DoctorViewAdapter;
import com.example.doctorfinder.Api.ApiClient;
import com.example.doctorfinder.Api.ApiInterface;
import com.example.doctorfinder.Models.DoctorModel;
import com.example.doctorfinder.R;
import com.example.doctorfinder.Responses.GetDoctorInfoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DoctorInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DoctorModel> doctorModelArrayList = new ArrayList<>();
    ApiInterface apiInterface;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);

        initialization();

        getdata();

//        doctorModelArrayList = new ArrayList<>();
//
//        doctorModelArrayList.add(new DoctorModel("9 Years Exp", "Satyamev Hospital", "Ahmedabad", "Dr. Gautam Gulati", "MBBS, Doctor Degree", "Physician", "English, Hindi", "Price : $250", R.drawable.female, R.drawable.logo));
//        doctorModelArrayList.add(new DoctorModel("9 Years Exp", "Apollo Hospital", "Gandhinagar", "Dr. Prashant Aggarwal", "MBBS, Doctor Degree", "Surgeon", "English, Hindi", "Price : $250", R.drawable.female, R.drawable.logo));
//        doctorModelArrayList.add(new DoctorModel("9 Years Exp", "Swedish Hospital", "Sweden", "Dr. Mark Zuckerberg", "MBBS, Doctor Degree", "Dermatologist", "English, Hindi", "Price : $250", R.drawable.female, R.drawable.logo));
//        doctorModelArrayList.add(new DoctorModel("9 Years Exp", "Holland Hospital", "Holland", "Dr. Steve Smith", "MBBS, Doctor Degree", "Physician", "English, Hindi", "Price : $250", R.drawable.female, R.drawable.logo));
//        doctorModelArrayList.add(new DoctorModel("9 Years Exp", "Satyamev Hospital", "Ahmedabad", "Dr. Usha Bhasin", "MBBS, Doctor Degree", "Surgeon", "English, Hindi", "Price : $250", R.drawable.female, R.drawable.logo));
//        doctorModelArrayList.add(new DoctorModel("9 Years Exp", "Apollo Hospital", "Gandhinagar", "Dr. Gautam Gulati", "MBBS, Doctor Degree", "ENT", "English, Hindi", "Price : $250", R.drawable.female, R.drawable.logo));
//        doctorModelArrayList.add(new DoctorModel("9 Years Exp", "Switzerland Hospital", "Switzerland", "Dr. Justin Beiber", "MBBS, Doctor Degree", "Dermatologist", "English, Hindi", "Price : $250", R.drawable.female, R.drawable.logo));
//        doctorModelArrayList.add(new DoctorModel("9 Years Exp", "Newyork Hospital", "Newyork", "Dr. Ariana Grande", "MBBS, Doctor Degree", "Physician", "English, Hindi", "Price : $250", R.drawable.female, R.drawable.logo));
//        doctorModelArrayList.add(new DoctorModel("9 Years Exp", "Seoul Hospital", "South Korea", "Dr. Kim Jon Hyuk", "MBBS, Doctor Degree", "ENT", "English, Hindi", "Price : $250", R.drawable.female, R.drawable.logo));
//        doctorModelArrayList.add(new DoctorModel("9 Years Exp", "Swedish Hospital", "Sweden", "Dr. Mayank Singh", "MBBS, Doctor Degree", "Surgeon", "English, Hindi", "Price : $250", R.drawable.female, R.drawable.logo));

//        DoctorViewAdapter doctorAdapter = new DoctorViewAdapter(doctorModelArrayList, this);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(doctorAdapter);

    }

    public void initialization() {
        recyclerView = findViewById(R.id.recyclerview_doctorinfo);
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    private void setadapter(List<DoctorModel> doctorModels) {
        DoctorViewAdapter doctorViewAdapter = new DoctorViewAdapter(doctorModels, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(doctorViewAdapter);
    }

    private void getdata() {
        Log.e("TAG", "Working");

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);

//        apiInterface.getdoctorinfo().enqueue(new Callback<GetDoctorInfoResponse>() {
        apiInterface.getparticulardoctor(name).enqueue(new Callback<GetDoctorInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetDoctorInfoResponse> call, @NonNull Response<GetDoctorInfoResponse> response) {
                try {
                    if (response != null) {
                        if (response.body().getStatus().equals("1")) {
                            setadapter(response.body().getData());
                            Log.e("TAG", "try if Working");
                        } else {
                            Toast.makeText(DoctorInfoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "try else Working");
                        }
                    }
                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                    Log.e("TAG", "catch Working");
                }
            }

            @Override
            public void onFailure(Call<GetDoctorInfoResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Log.e("TAG", "Failure Working");
            }
        });

    }
}