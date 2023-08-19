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

public class Cart {
    private int cart_num;
    private int productNum;
    private int counts;
    private int membernum;

    public Cart(int product_num, int counts) {
        this.productNum = product_num;
        this.counts = counts;
    }

    // Getter and setter methods...
    public int getMemberNum() {
    	return membernum;
    }
    
    public void setMemberNum(int membernum) {
    	this.membernum = membernum;
    } 
    
    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

}
