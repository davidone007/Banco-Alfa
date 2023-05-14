package com.example.alfamanager.model;

import java.util.Date;

public class Income extends FinancialMovement {
    public Income(String description, double value, Date dateFormatDate) {
        super(description, value, dateFormatDate);
    }
}
