package com.example.frame;

import java.util.List;

import com.example.dto.Cart;
import com.example.dto.MemberList;

public interface MyMapper<K,V> {
	public void insert(V v) throws Exception;
	public void delete(K k) throws Exception;
	public void update(V v) throws Exception;
	
	public V select(K k) throws Exception;
	public List<V> selectAll() throws Exception;
}
