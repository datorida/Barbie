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

public class GuestCart {
    private int guest_cart_num;
    private int product_num;
    private int counts;
    private String temporaryIdentifier;

    public Product product;
   
  
  
    // Getter and setter methods...
 
    public int getProductNum() {
    	return product_num;
    }
    
    public void setProductNum(int ProductNum) {
    	this.product_num = ProductNum;
    }
    
    public Product getProduct() {
    	return product;
    }

}
