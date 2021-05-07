package com.jwt.model;

public class Update_Payment_Data {


    String payment_id;
    String order_id;
    String status;

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Update_Payment_Data(String payment_id, String order_id, String status) {
        this.payment_id = payment_id;
        this.order_id = order_id;
        this.status = status;
    }
}
