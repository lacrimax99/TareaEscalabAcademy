package com.escalab.academyappbackend.service;

import java.util.List;

@SuppressWarnings("unused")
public interface ICRUD <T> {

	List <T> listar();
	
	T registrar(T obj);
	
	Boolean deleteById(int id);
	
	T modificar(T obj);
	
	T leerPorId(int id);
}
