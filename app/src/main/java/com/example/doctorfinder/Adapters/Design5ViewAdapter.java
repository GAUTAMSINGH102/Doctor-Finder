package com.example.doctorfinder.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorfinder.Activities.DashBoard;
import com.example.doctorfinder.Activities.DoctorInfoActivity;
import com.example.doctorfinder.Models.SymptomsModel;
import com.example.doctorfinder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Design5ViewAdapter extends RecyclerView.Adapter<Design5ViewAdapter.RecyclerViewHolder> {

    private List<SymptomsModel> symptomsModelArrayList;
    private Context mcontext;

    public Design5ViewAdapter(List<SymptomsModel> symptomsModelArrayList, Context mcontext) {
        this.symptomsModelArrayList = symptomsModelArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_5, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        SymptomsModel symptomsModel = symptomsModelArrayList.get(position);

        // Set the data to textview and imageview.
        SymptomsModel recyclerData = symptomsModelArrayList.get(position);
        holder.courseTV.setText(recyclerData.getIssue());
        Picasso.get().load(symptomsModel.getIssueimage())
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
                String symptoms = symptomsModelArrayList.get(position).getIssue();
                Toast.makeText(mcontext, symptoms, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext, DoctorInfoActivity.class);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return symptomsModelArrayList.size();
    }

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
}
