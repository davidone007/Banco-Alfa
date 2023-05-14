package com.example.alfamanager.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FinancialMovement {

    public FinancialMovement(String description, double value, Date dateFormatDate) {
        this.description = description;
        this.value = value;
        this.dateFormatDate = dateFormatDate;
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        this.date = formatDate.format(dateFormatDate);
    }

    private String type;

    private String description;

    private double value;

    private String date;
    private Date dateFormatDate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getDateFormatDate() {
        return dateFormatDate;
    }

    public void setDateFormatDate(Date dateFormatDate) {
        this.dateFormatDate = dateFormatDate;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}


