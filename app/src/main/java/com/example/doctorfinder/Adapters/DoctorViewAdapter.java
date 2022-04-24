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
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorfinder.Activities.DashBoard;
import com.example.doctorfinder.Activities.KnowMore;
import com.example.doctorfinder.Activities.MainActivity;
import com.example.doctorfinder.Models.DoctorModel;
import com.example.doctorfinder.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DoctorViewAdapter extends RecyclerView.Adapter<DoctorViewAdapter.ViewModel>{

    private List<DoctorModel> doctorInfoList;
    private Context context;

    public DoctorViewAdapter(List<DoctorModel> doctorInfoList, Context context) {
        this.doctorInfoList = doctorInfoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_info_layout, parent, false);
        return new ViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int position) {
        DoctorModel doctorModel = doctorInfoList.get(position);

        // For Doctor Image
        Picasso.get().load(doctorModel.getPhoto())
                .into(holder.doctor_image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        // For Hospital Logo
        Picasso.get().load(doctorModel.getHospitallogo())
                .into(holder.hospital_logo, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        holder.doctor_exp.setText(doctorModel.getExperience());
        holder.hospital_name.setText(doctorModel.getHospitalname());
        holder.hospital_location.setText(doctorModel.getHospitallocation());
        holder.doctor_name.setText(doctorModel.getDoctorname());
        holder.doctor_degree.setText(doctorModel.getDoctordegree());
        holder.doctor_position.setText(doctorModel.getDoctorpost());
        holder.doctor_language.setText(doctorModel.getDoctorlanguage());
        holder.price.setText(doctorModel.getDoctorprice());

        holder.knowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, doctorInfoList.get(position).getDoctor_position(), Toast.LENGTH_SHORT).show();
                String post = doctorInfoList.get(position).getDoctorpost();
//                if(post.equals("Surgeon")) {
                    Intent intent = new Intent(context, KnowMore.class);
                    intent.putExtra("position", post);
                    context.startActivity(intent);
//                } else {
//                    Toast.makeText(context, "This is not a Surgeon", Toast.LENGTH_SHORT).show();
//                }
            }
        });


        holder.info_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String place = doctorInfoList.get(position).getHospitallocation();

                Toast.makeText(context, place, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return doctorInfoList.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {

        private TextView doctor_exp, hospital_name, hospital_location, doctor_name, doctor_degree, doctor_position, doctor_language, price;
        private ImageView doctor_image, hospital_logo;
        private AppCompatButton knowMore;
        private CardView info_layout;

        public ViewModel(@NonNull View itemView) {
            super(itemView);

            doctor_exp = itemView.findViewById(R.id.doctor_exp);
            hospital_name = itemView.findViewById(R.id.hospital_name);
            hospital_location = itemView.findViewById(R.id.hospital_location);
            doctor_name = itemView.findViewById(R.id.doctor_name);
            doctor_degree = itemView.findViewById(R.id.doctor_degree);
            doctor_position = itemView.findViewById(R.id.doctor_position);
            doctor_language = itemView.findViewById(R.id.doctor_language);
            price = itemView.findViewById(R.id.price);

            doctor_image = itemView.findViewById(R.id.doctor_image);
            hospital_logo = itemView.findViewById(R.id.hospital_logo);

            knowMore = itemView.findViewById(R.id.know_more);

            info_layout = itemView.findViewById(R.id.info_layout);

        }

    }

}
