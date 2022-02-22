package com.example.doctorfinder.Models;

import javax.xml.transform.sax.SAXResult;

public class SpecialitiesModel {

    private String special_photo, special_name;

    public SpecialitiesModel(String special_photo, String special_name) {
        this.special_photo = special_photo;
        this.special_name = special_name;
    }

    public String getSpecial_photo() {
        return special_photo;
    }

    public void setSpecial_photo(String special_photo) {
        this.special_photo = special_photo;
    }

    public String getSpecial_name() {
        return special_name;
    }

    public void setSpecial_name(String special_name) {
        this.special_name = special_name;
    }
}
