package br.com.app.decora.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class ApplicationService extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
 
	public ApplicationService() {
		singletons.add(new UserService());
		singletons.add(new TaskService());
	}
 
	public Set<Class<?>> getClasses() {    
		return empty;
	}
 
	public Set<Object> getSingletons() {
		return singletons;
	}
}
