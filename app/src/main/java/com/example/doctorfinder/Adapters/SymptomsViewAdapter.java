package com.example.doctorfinder.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorfinder.Activities.DashBoard;
import com.example.doctorfinder.Activities.DoctorInfoActivity;
import com.example.doctorfinder.Api.ApiClient;
import com.example.doctorfinder.Api.ApiInterface;
import com.example.doctorfinder.Models.SymptomsModel;
import com.example.doctorfinder.R;
import com.example.doctorfinder.Responses.GetDoctorInfoResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SymptomsViewAdapter extends RecyclerView.Adapter<SymptomsViewAdapter.RecyclerViewHolder> implements Filterable{

    private List<SymptomsModel> symptomsModelArrayList;
    List<SymptomsModel> filterData;
    private Context mcontext;
    ApiInterface apiInterface;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";

    public SymptomsViewAdapter(List<SymptomsModel> symptomsModelArrayList, Context mcontext) {
        this.symptomsModelArrayList = symptomsModelArrayList;
        this.mcontext = mcontext;
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
        filterData = new ArrayList<>(symptomsModelArrayList);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symptoms_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        SymptomsModel symptomsModel = symptomsModelArrayList.get(holder.getAdapterPosition());

        holder.courseTV.setText(symptomsModelArrayList.get(holder.getAdapterPosition()).getIssue());
        // For Image
        Picasso.get().load(symptomsModelArrayList.get(holder.getAdapterPosition()).getIssueimage())
                .into(holder.courseIV, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String symptoms = symptomsModelArrayList.get(holder.getAdapterPosition()).getIssue();

                sharedPreferences = mcontext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, symptoms);
                editor.apply();

                getParticularDoctor(symptomsModelArrayList.get(holder.getAdapterPosition()).getIssue() + "", holder.getAdapterPosition());

//                String symptoms = symptomsModelArrayList.get(position).getIssue();

//                sharedPreferences = mcontext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(KEY_NAME,symptoms);
//                editor.apply();

//                Toast.makeText(mcontext, symptoms, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mcontext, DoctorInfoActivity.class);
//                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return symptomsModelArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filterSearch;
    }

    private Filter filterSearch = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<SymptomsModel> filterList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0) {
                filterList.addAll(filterData);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(SymptomsModel item: filterData) {
                    if(item.getIssue().toLowerCase().contains(filterPattern)) {
                        filterList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            symptomsModelArrayList.clear();
            symptomsModelArrayList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView courseTV;
        private ImageView courseIV;
        private CardView cardView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTV = itemView.findViewById(R.id.idTVCourse);
            courseIV = itemView.findViewById(R.id.idIVcourseIV);
            cardView = itemView.findViewById(R.id.symptoms_info_layout);
        }
    }

    private void getParticularDoctor(String issue, int pose) {
        apiInterface.getparticulardoctor(issue).enqueue(new Callback<GetDoctorInfoResponse>() {
            @Override
            public void onResponse(Call<GetDoctorInfoResponse> call, Response<GetDoctorInfoResponse> response) {
                try {
                    if (response != null) {
                        Toast.makeText(mcontext, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if (response.body().getStatus().equals("1")) {

                            Intent intent = new Intent(mcontext, DoctorInfoActivity.class);
                            mcontext.startActivity(intent);
                        }
                    }
                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<GetDoctorInfoResponse> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });
    }
}
