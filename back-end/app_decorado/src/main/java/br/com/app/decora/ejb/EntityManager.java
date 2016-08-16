package br.com.app.decora.ejb;

import java.util.List;

public interface EntityManager<T> {

	T save(T entity);
	T get(String id);
	List<T> getAll();
}
