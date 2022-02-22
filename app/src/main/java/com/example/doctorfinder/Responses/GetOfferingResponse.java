package com.example.doctorfinder.Responses;

import com.example.doctorfinder.Models.OfferingModel;

import java.util.List;

public class GetOfferingResponse {

    String status;
    String message;
    List<OfferingModel> data;

    public GetOfferingResponse(String status, String message, List<OfferingModel> data) {
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

    public List<OfferingModel> getData() {
        return data;
    }

    public void setData(List<OfferingModel> data) {
        this.data = data;
    }
}
