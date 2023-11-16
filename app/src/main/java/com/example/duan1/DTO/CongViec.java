package com.example.duan1.DTO;

public class CongViec {
private String tenCV;
private String content;
private String status;
private int id;

    public CongViec() {
    }

    public CongViec(String tenCV, String content, String status, int id) {
        this.tenCV = tenCV;
        this.content = content;
        this.status = status;
        this.id = id;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
