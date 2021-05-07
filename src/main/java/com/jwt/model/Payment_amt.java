package com.jwt.model;

public class Payment_amt {

    int amt;

    public Payment_amt()
    {

    }
    public Payment_amt(int amt) {
        this.amt = amt;
    }

    public int getAmt() {
        return amt;
    }

    @Override
    public String toString() {
        return "payment_amt{" +
                "amt=" + amt +
                '}';
    }
}

