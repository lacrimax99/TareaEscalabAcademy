package com.escalab.academyappbackend.service;

import java.util.List;

public interface ICRUD <T> {

	List <T> getAll();
	
	T save(T obj);
	
	Boolean deleteById(int id);
	
	T update(T obj);
	
	T findById(int id);
}
