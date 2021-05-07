package com.jwt.controller;

import com.jwt.model.*;
import com.jwt.repo.MyOrderRepo;
import com.jwt.repo.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.razorpay.*;  //for razorpay payment gateway

import java.security.Principal;
import java.sql.SQLOutput;

@RestController
@CrossOrigin(origins = "*")
public class Home {

    @Autowired
    UserRepository userRepository;  // for storing user data (at the time of registration)to database user table using repo->UsereRepository

    @Autowired
    MyOrderRepo myOrderRepo; // for storing user payment data (order details returned by razorpay)to database orders table using repo->UsereRepository





    @RequestMapping(value = "/registration", method =  RequestMethod.POST)
    public ResponseEntity<?> gT(@RequestBody JwtRequest jwtRequest) throws Exception
    {

        // storing data into data base
        System.out.println(jwtRequest);
        try{

            User user = new User();
            user.setId(jwtRequest.getId());
            user.setEmail(jwtRequest.getEmail());
            user.setPassword(jwtRequest.getPassword());
            user.setUsername(jwtRequest.getUsername());

            User save = userRepository.save(user);

            System.out.println(save);
            return ResponseEntity.ok("{\"Welcome to coffee shop\":\"Vimal\"}");
        }catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

    }

//     api for creating user order which will be used for payment from the razor pay server
    @RequestMapping(value = "/createorder", method =  RequestMethod.POST)
    public ResponseEntity<?> gT(@RequestBody Payment_amt payment_amt , Principal principal) throws Exception  //
    {
            System.out.println(payment_amt.getAmt());
            int amt = payment_amt.getAmt();
            // key and secret in downloaded excel file from rajorpay website
            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_3XEKL4JwzLMfot", "SozlDQdYYR3kKR2n1IcP9Oj4" );

            // create order to sent to razor pay server
            JSONObject ob = new JSONObject();
            ob.put("amount",amt*100); // we need amt in paise not in rupees
            ob.put("currency","INR");
            ob.put("receipt","txn_235435");

            Order order = razorpayClient.Orders.create(ob); // creating order
            System.out.println(order);

            //saving order information in the database
            MyOrder myOrder = new MyOrder();

            // order oject uses razorpay to get details

            myOrder.setAmount((order.get("amount")+"")); // +"" is used to convert int amount to string because in order table amount is string not integer
            myOrder.setOrderId(order.get("id"));
            myOrder.setPaymentId(order.get(null));
            myOrder.setStatus("created");
            myOrder.setUser(this.userRepository.findByUsername(principal.getName())); // to get current logged in user
            myOrder.setReceipt(order.get("receipt"));

            this.myOrderRepo.save(myOrder);

            return ResponseEntity.ok(order.toString());  //returning order to frontend
    }

    //api for storing payment status after payment is successful
    @RequestMapping(value = "/update_order", method =  RequestMethod.POST)
    public ResponseEntity<?> gT(@RequestBody Update_Payment_Data update_payment_data) throws Exception
    {
        MyOrder myOrder = myOrderRepo.findByOrderId(update_payment_data.getOrder_id());  // finding order table by order_id(primary key)  returned by front end after payment sucess
        myOrder.setPaymentId(update_payment_data.getPayment_id());
        myOrder.setStatus(update_payment_data.getStatus());

        this.myOrderRepo.save(myOrder); // order table goes updated after payment
        System.out.println(update_payment_data.getPayment_id()+update_payment_data.getOrder_id()+update_payment_data.getStatus());
        return ResponseEntity.ok( "{\"order table\":\"updated\"}");
    }
}
