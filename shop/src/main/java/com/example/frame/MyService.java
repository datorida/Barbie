package com.example.frame;

public interface MyService<K,V> {
	public void register(V v) throws Exception;
	public void remove(K k) throws Exception;
	public void modify(V v) throws Exception;

	public V get(K k) throws Exception;
	public java.util.List<V> get() throws Exception;
	
	/* 로그인 */
	public boolean authenticate(String mem_id, String pwd) throws Exception;
	
}
