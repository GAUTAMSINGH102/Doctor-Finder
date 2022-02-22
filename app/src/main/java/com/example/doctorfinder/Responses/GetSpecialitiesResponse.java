package com.example.doctorfinder.Responses;

import com.example.doctorfinder.Models.SpecialitiesModel;

import java.util.List;

public class GetSpecialitiesResponse {

    String status;
    String message;
    List<SpecialitiesModel> data;

    public GetSpecialitiesResponse(String status, String message, List<SpecialitiesModel> data) {
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

    public List<SpecialitiesModel> getData() {
        return data;
    }

    public void setData(List<SpecialitiesModel> data) {
        this.data = data;
    }
}
