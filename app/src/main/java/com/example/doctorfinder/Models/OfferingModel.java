package com.example.doctorfinder.Models;

public class OfferingModel {

    private String offer_photo, offer_name;

    public OfferingModel(String offer_photo, String offer_name) {
        this.offer_photo = offer_photo;
        this.offer_name = offer_name;
    }

    public String getOffer_photo() {
        return offer_photo;
    }

    public void setOffer_photo(String offer_photo) {
        this.offer_photo = offer_photo;
    }

    public String getOffer_name() {
        return offer_name;
    }

    public void setOffer_name(String offer_name) {
        this.offer_name = offer_name;
    }
}
