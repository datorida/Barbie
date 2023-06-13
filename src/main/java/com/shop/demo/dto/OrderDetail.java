package com.shop.demo.dto;

import java.util.Date;

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

public class OrderDetail {
    private int order_num;
    private String all_price;
    private Date order_date;
    private String order_status;
    private String order_exchange;
    private String order_return;
    private String cancellation;
    private int order_id;
}
