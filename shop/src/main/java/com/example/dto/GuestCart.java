package com.example.dto;

import java.math.BigDecimal;

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
    private BigDecimal total;
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
    
    public BigDecimal getTotal() {
    	return total;
    }
    
    public void setTotal(BigDecimal total) {
    	this.total = total;
    }

}