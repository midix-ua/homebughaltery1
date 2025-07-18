package com.example.demo.dto;


import com.example.demo.model.Account;

import java.time.LocalDate;
import java.util.Date;

public class SpendingDTO {
    private final Account account;
    private final Integer monthly_spending;
    private final LocalDate date;


    public SpendingDTO(Account account, Integer monthly_spending,LocalDate date) {
        this.account = account;
        this.monthly_spending = monthly_spending;
       this.date = date;
    }

    public static SpendingDTO of(Account account,  Integer monthly_spending,LocalDate date) {
        return new SpendingDTO(account, monthly_spending,date);
    }

    public Account getAccount() {
        return account;
    }

    public Integer getMonthly_Spending() {
        return monthly_spending;
    }

    public LocalDate getDate() {
        return date;
    }

}
