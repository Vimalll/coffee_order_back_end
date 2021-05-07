package com.jwt.model;

import javax.persistence.*;

@Entity
@Table(name="orders")  // make a new table named "orders"
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long myOrderId;  // primary key

    private String orderId;

    private String amount;

    private String receipt;

    private String status;

    @ManyToOne  // to make foreign key , here we are doing unidirectional mapping(same user can do many payments)
    private User user;

    private String paymentId;

    public Long getMyOrderId() {
        return myOrderId;
    }

    public void setMyOrderId(Long myOrderId) {
        this.myOrderId = myOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
