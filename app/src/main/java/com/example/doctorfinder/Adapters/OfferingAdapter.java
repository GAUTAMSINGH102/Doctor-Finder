package com.example.doctorfinder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorfinder.Models.OfferingModel;
import com.example.doctorfinder.Models.SpecialitiesModel;
import com.example.doctorfinder.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OfferingAdapter extends RecyclerView.Adapter<OfferingAdapter.RecyclerViewHolder>{

    private List<OfferingModel> offeringModelList;
    private Context mcontext;

    public OfferingAdapter(List<OfferingModel> offeringModelList, Context mcontext) {
        this.offeringModelList = offeringModelList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_4, parent, false);
        return new OfferingAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        OfferingModel offeringModel = offeringModelList.get(position);

        // Set the data to textview and imageview.
        OfferingModel recyclerData = offeringModelList.get(position);
        holder.courseTV.setText(recyclerData.getOffer_name());
        Picasso.get().load(recyclerData.getOffer_photo())
                .into(holder.courseIV, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        holder.card_view_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String symptoms = offeringModelList.get(position).getOffer_name();
                Toast.makeText(mcontext, symptoms, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mcontext, DoctorInfoActivity.class);
//                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return offeringModelList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView courseTV;
        private ImageView courseIV;
        private CardView card_view_1;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTV = itemView.findViewById(R.id.idTVCourse);
            courseIV = itemView.findViewById(R.id.idIVcourseIV);
            card_view_1 = itemView.findViewById(R.id.card_view_1);
        }
    }

}
