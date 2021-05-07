package com.jwt.repo;

import com.jwt.model.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyOrderRepo extends JpaRepository<MyOrder, Long> {   // <MyOrder, Long> means : i want to handle object to type Myorder and in Myorder table data type of id(primary key) is Long

     public MyOrder findByOrderId(String orderId);  //
}
