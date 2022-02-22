package com.example.doctorfinder.Responses;

import com.example.doctorfinder.Models.DoctorModel;
import com.example.doctorfinder.Models.SymptomsModel;

import java.util.List;

public class GetSymptomsResponse {

    String status;
    String message;
    List<SymptomsModel> data;

    public GetSymptomsResponse(String status, String message, List<SymptomsModel> data) {
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

    public List<SymptomsModel> getData() {
        return data;
    }

    public void setData(List<SymptomsModel> data) {
        this.data = data;
    }

}
