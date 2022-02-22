package com.example.doctorfinder.Responses;

import com.example.doctorfinder.Models.DoctorModel;

import java.util.List;

public class GetDoctorInfoResponse {

    String status;
    String message;
    List<DoctorModel> data;

    public GetDoctorInfoResponse(String status, String message, List<DoctorModel> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DoctorModel> getData() {
        return data;
    }

    public void setData(List<DoctorModel> data) {
        this.data = data;
    }
}
