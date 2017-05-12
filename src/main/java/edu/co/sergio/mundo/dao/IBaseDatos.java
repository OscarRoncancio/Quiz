package edu.co.sergio.mundo.dao;

import java.util.List;

public interface IBaseDatos<T> {
	List<T> findAll();
    boolean insert(T t);
    boolean update(T t);
    boolean delete(T t);
    
}
