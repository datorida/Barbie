package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Order {
    private int order_id;
    private String shippingInfo;
    private String paymentprice;
    private String paymentway;
    private int cart_num;
    private int membernum;
    
}
