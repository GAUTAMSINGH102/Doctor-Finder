package com.example.doctorfinder.Models;

public class SymptomsModel {

    private String issue, issueimage;

    public SymptomsModel(String issue, String issueimage) {
        this.issue = issue;
        this.issueimage = issueimage;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getIssueimage() {
        return issueimage;
    }

    public void setIssueimage(String issueimage) {
        this.issueimage = issueimage;
    }
}
