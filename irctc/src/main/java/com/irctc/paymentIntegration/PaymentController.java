package com.irctc.paymentIntegration;

import com.nimbusds.jose.shaded.gson.JsonObject;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PaymentController {

    @GetMapping("/initializePayment/{amount}")
    public String paymentStarted(@PathVariable int amount) throws RazorpayException {
        System.out.println("inside initialize payment");
        int amt = amount;
        System.out.println(amt);
        var client  = new RazorpayClient("rzp_test_Lt6Kiszh9cXocL","idL3FsYQ24L8RbDC4DFFbXsU");
        org.json.JSONObject ob = new JSONObject();
        ob.put("amount",amt*100);
        ob.put("currency","INR");
        ob.put("receipt", "order_rcptid_11");
        Order order = client.orders.create(ob);
        System.out.println(order);
//        we can save it to the database
        return order.toString();
    }
}
