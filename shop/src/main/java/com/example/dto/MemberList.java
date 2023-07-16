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

public class MemberList {
    private int membernum;
    private String mem_id;
    private String mem_name;
    private String email;
    private String phone;
    private String pwd;
    private String address;

    
    

	public MemberList(String mem_id, String pwd) {
		super();
		this.mem_id=mem_id;
		this.pwd=pwd;
	}

}
