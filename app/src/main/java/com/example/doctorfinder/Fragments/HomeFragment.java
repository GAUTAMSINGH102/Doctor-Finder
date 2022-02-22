package com.example.doctorfinder.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.doctorfinder.Activities.OnBoardingActivity;
import com.example.doctorfinder.Adapters.Design3ViewAdapter;
import com.example.doctorfinder.Adapters.Design4ViewAdapter;
import com.example.doctorfinder.Adapters.Design5ViewAdapter;
import com.example.doctorfinder.Adapters.OfferingAdapter;
import com.example.doctorfinder.Adapters.SymptomsViewAdapter;
import com.example.doctorfinder.Api.ApiClient;
import com.example.doctorfinder.Api.ApiInterface;
import com.example.doctorfinder.Models.OfferingModel;
import com.example.doctorfinder.Models.SpecialitiesModel;
import com.example.doctorfinder.Models.SymptomsModel;
import com.example.doctorfinder.R;
import com.example.doctorfinder.Responses.GetOfferingResponse;
import com.example.doctorfinder.Responses.GetSpecialitiesResponse;
import com.example.doctorfinder.Responses.GetSymptomsResponse;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView ourOfferingRecyclerView,commonSymptomsRecyclerView, commonSpecialitiesRecyclerView, inTheSpotlight;
    private List<SymptomsModel> symptomsModelArrayList = new ArrayList<>();
    ApiInterface apiInterface;

    //Drawer Menu
    ImageView menuIcon;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //Hooks for menu
        drawerLayout = rootView.findViewById(R.id.drawer_layout);
        navigationView = rootView.findViewById(R.id.navigation_view);
        menuIcon = rootView.findViewById(R.id.menu);

        navigationdrawer();


        ourOfferingRecyclerView = rootView.findViewById(R.id.ourOffering_recyclerView);
        commonSymptomsRecyclerView = rootView.findViewById(R.id.commonSymptoms_recyclerView);
        commonSpecialitiesRecyclerView = rootView.findViewById(R.id.commonSpecialities_recyclerView);
        inTheSpotlight = rootView.findViewById(R.id.in_the_spotlight);
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);

        getdata();

//        symptomsModelArrayList = new ArrayList<>();
//
//        // added data to array list
//        symptomsModelArrayList.add(new SymptomsModel("Diabetes",R.drawable.ic_launcher_background));
//        symptomsModelArrayList.add(new SymptomsModel("Migraine",R.drawable.ic_launcher_background));
//        symptomsModelArrayList.add(new SymptomsModel("Thyroid",R.drawable.ic_launcher_background));
//        symptomsModelArrayList.add(new SymptomsModel("Heart Health",R.drawable.ic_launcher_background));
//        symptomsModelArrayList.add(new SymptomsModel("Covid - 19",R.drawable.ic_launcher_background));
//        symptomsModelArrayList.add(new SymptomsModel("Malaria",R.drawable.ic_launcher_background));
//
//        SymptomsViewAdapter doctorAdapter1 = new SymptomsViewAdapter(symptomsModelArrayList, getActivity());
//        Design3ViewAdapter doctorAdapter2 = new Design3ViewAdapter(symptomsModelArrayList, getActivity());
//        Design5ViewAdapter doctorAdapter4 = new Design5ViewAdapter(symptomsModelArrayList, getActivity());
//        Design4ViewAdapter doctorAdapter3 = new Design4ViewAdapter(symptomsModelArrayList, getActivity());
//
//        GridLayoutManager layoutManager1 =new GridLayoutManager(getActivity(),3);
//        GridLayoutManager layoutManager2 =new GridLayoutManager(getActivity(),3);
//        LinearLayoutManager layoutManager4 =new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
//        GridLayoutManager layoutManager3 =new GridLayoutManager(getActivity(),3);
//
//        ourOfferingRecyclerView.setLayoutManager(layoutManager1);
//        commonSymptomsRecyclerView.setLayoutManager(layoutManager2);
//        inTheSpotlight.setLayoutManager(layoutManager4);
//        commonSpecialitiesRecyclerView.setLayoutManager(layoutManager3);
//
//        ourOfferingRecyclerView.setAdapter(doctorAdapter1);
//        commonSymptomsRecyclerView.setAdapter(doctorAdapter2);
//        inTheSpotlight.setAdapter(doctorAdapter4);
//        commonSpecialitiesRecyclerView.setAdapter(doctorAdapter3);


        return rootView;
    }

    //Navigation Drawer functions
    private void navigationdrawer() {
        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else
                    drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //to select an item

        switch (item.getItemId()) {

            case R.id.nav_all_categories:
//                startActivity(new Intent(getApplicationContext(),AllCategories.class);
                Intent intent1 = new Intent(getActivity(), OnBoardingActivity.class);
                startActivity(intent1);
                break;
        }
        //true means someitems is going to select
        return true;
    }

    private void setadapter(List<SymptomsModel> symptomsModels) {

        Design3ViewAdapter doctorAdapter2 = new Design3ViewAdapter(symptomsModels, getActivity());
        Design5ViewAdapter doctorAdapter4 = new Design5ViewAdapter(symptomsModels, getActivity());



        GridLayoutManager layoutManager2 =new GridLayoutManager(getActivity(),3);
        LinearLayoutManager layoutManager4 =new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);



        commonSymptomsRecyclerView.setLayoutManager(layoutManager2);
        inTheSpotlight.setLayoutManager(layoutManager4);



        commonSymptomsRecyclerView.setAdapter(doctorAdapter2);
        inTheSpotlight.setAdapter(doctorAdapter4);

    }

    private void setadapterspecialities(List<SpecialitiesModel> specialitiesModels) {
        Design4ViewAdapter doctorAdapter3 = new Design4ViewAdapter(specialitiesModels, getActivity());

        GridLayoutManager layoutManager3 =new GridLayoutManager(getActivity(),3);

        commonSpecialitiesRecyclerView.setLayoutManager(layoutManager3);

        commonSpecialitiesRecyclerView.setAdapter(doctorAdapter3);
    }

    private void setadapteroffering(List<OfferingModel> offeringModels) {
        OfferingAdapter offeringAdapter = new OfferingAdapter(offeringModels, getActivity());

        GridLayoutManager layoutManager1 =new GridLayoutManager(getActivity(),3);

        ourOfferingRecyclerView.setLayoutManager(layoutManager1);

        ourOfferingRecyclerView.setAdapter(offeringAdapter);
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

        // Specialities
        apiInterface.getspecialities().enqueue(new Callback<GetSpecialitiesResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetSpecialitiesResponse> call, @NonNull Response<GetSpecialitiesResponse> response) {
                try {
                    if (response != null) {
                        if (response.body().getStatus().equals("1")) {
                            setadapterspecialities(response.body().getData());
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
            public void onFailure(Call<GetSpecialitiesResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Log.e("TAG", "Failure Working");
            }
        });

        // Offering
        apiInterface.getoffering().enqueue(new Callback<GetOfferingResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetOfferingResponse> call, @NonNull Response<GetOfferingResponse> response) {
                try {
                    if (response != null) {
                        if (response.body().getStatus().equals("1")) {
                            setadapteroffering(response.body().getData());
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
            public void onFailure(Call<GetOfferingResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
                Log.e("TAG", "Failure Working");
            }
        });

    }

}