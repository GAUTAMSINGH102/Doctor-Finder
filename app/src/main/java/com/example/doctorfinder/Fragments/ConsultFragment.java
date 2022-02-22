package com.example.doctorfinder.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.doctorfinder.Activities.DoctorInfoActivity;
import com.example.doctorfinder.Adapters.DoctorViewAdapter;
import com.example.doctorfinder.Adapters.SymptomsViewAdapter;
import com.example.doctorfinder.Api.ApiClient;
import com.example.doctorfinder.Api.ApiInterface;
import com.example.doctorfinder.Models.DoctorModel;
import com.example.doctorfinder.Models.SymptomsModel;
import com.example.doctorfinder.R;
import com.example.doctorfinder.Responses.GetDoctorInfoResponse;
import com.example.doctorfinder.Responses.GetSymptomsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConsultFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<SymptomsModel> symptomsModelArrayList = new ArrayList<>();
    SearchView searchView;
    private SymptomsViewAdapter symptomsViewAdapter;
    ApiInterface apiInterface;

    public ConsultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_consult, container, false);

        recyclerView = rootView.findViewById(R.id.symptoms_recycler_view);
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
        searchView = rootView.findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                symptomsViewAdapter.getFilter().filter(newText);
                return false;
            }
        });

        getdata();
//
//        symptomsModelArrayList = new ArrayList<>();
//
//        // added data to array list
//        symptomsModelArrayList.add(new SymptomsModel("Diabetes","Practice"));
//        symptomsModelArrayList.add(new SymptomsModel("Migraine","Practice"));
//        symptomsModelArrayList.add(new SymptomsModel("Thyroid",R.drawable.ic_launcher_background));
//        symptomsModelArrayList.add(new SymptomsModel("Heart Health",R.drawable.covid));
//        symptomsModelArrayList.add(new SymptomsModel("Covid - 19",R.drawable.ic_launcher_background));
//        symptomsModelArrayList.add(new SymptomsModel("Malaria",R.drawable.ic_launcher_background));

//        SymptomsViewAdapter doctorAdapter = new SymptomsViewAdapter(symptomsModelArrayList, getActivity());
//
//        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),3);
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(doctorAdapter);

        return rootView;

    }

    private void setadapter(List<SymptomsModel> symptomsModels) {
        symptomsViewAdapter = new SymptomsViewAdapter(symptomsModels, getActivity());

        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),3);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(symptomsViewAdapter);
    }

    private void getdata() {
        Log.e("TAG", "Working");
        apiInterface.getsymptoms().enqueue(new Callback<GetSymptomsResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetSymptomsResponse> call, @NonNull Response<GetSymptomsResponse> response) {
                try {
                    if (response != null) {
                        if (response.body().getStatus().equals("1")) {
                            setadapter(response.body().getData());
                            Log.e("TAG", "try if Working");
                        } else {
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "try else Working");
                        }
                    }
                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                    Log.e("TAG", "catch Working");
                }
            }

            @Override
            public void onFailure(Call<GetSymptomsResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Log.e("TAG", "Failure Working");
            }
        });

    }
}