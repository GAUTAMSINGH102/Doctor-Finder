package com.example.doctorfinder.Models;

public class DoctorModel {

    private String experience, hospitalname, hospitallocation, doctorname, doctordegree, doctorpost, doctorlanguage, doctorprice,
            photo, hospitallogo;

    public DoctorModel(String experience, String hospitalname, String hospitallocation, String doctorname, String doctordegree, String doctorpost, String doctorlanguage, String doctorprice, String photo, String hospitallogo) {
        this.experience = experience;
        this.hospitalname = hospitalname;
        this.hospitallocation = hospitallocation;
        this.doctorname = doctorname;
        this.doctordegree = doctordegree;
        this.doctorpost = doctorpost;
        this.doctorlanguage = doctorlanguage;
        this.doctorprice = doctorprice;
        this.photo = photo;
        this.hospitallogo = hospitallogo;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getHospitallocation() {
        return hospitallocation;
    }

    public void setHospitallocation(String hospitallocation) {
        this.hospitallocation = hospitallocation;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDoctordegree() {
        return doctordegree;
    }

    public void setDoctordegree(String doctordegree) {
        this.doctordegree = doctordegree;
    }

    public String getDoctorpost() {
        return doctorpost;
    }

    public void setDoctorpost(String doctorpost) {
        this.doctorpost = doctorpost;
    }

    public String getDoctorlanguage() {
        return doctorlanguage;
    }

    public void setDoctorlanguage(String doctorlanguage) {
        this.doctorlanguage = doctorlanguage;
    }

    public String getDoctorprice() {
        return doctorprice;
    }

    public void setDoctorprice(String doctorprice) {
        this.doctorprice = doctorprice;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHospitallogo() {
        return hospitallogo;
    }

    public void setHospitallogo(String hospitallogo) {
        this.hospitallogo = hospitallogo;
    }
}