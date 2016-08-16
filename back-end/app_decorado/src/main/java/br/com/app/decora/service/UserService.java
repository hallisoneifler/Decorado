package br.com.app.decora.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.app.decora.ejb.Lookup;
import br.com.app.decora.ejb.UserManager;
import br.com.app.decora.model.User;

@Path("/users")
public class UserService {

	
	@Path("/login")
	@POST
	public Response login(User user) {
		
		UserManager manager = (UserManager) Lookup.lookup(UserManager.class.getSimpleName());
		User u = manager.login(user.getEmail(), user.getPassword());
		if (u != null) {
			return Response.status(200).entity(u).build();
		} else {
			return Response.status(204).build();
		}
	}
	
	@Path("/")
	@GET
	public Response getUsers() {
		
		UserManager manager = (UserManager) Lookup.lookup(UserManager.class.getSimpleName());
		List<User> list = manager.getAll();
			
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/{id}")
	@GET
	public Response getUser(@PathParam("id") String id) {
		
		UserManager manager = (UserManager) Lookup.lookup(UserManager.class.getSimpleName());
		User user = manager.get(id);
		
		return Response.ok(user, MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/")
	@POST
	public Response save(User user) {
		
		UserManager manager = (UserManager) Lookup.lookup(UserManager.class.getSimpleName());
		User u = manager.save(user);
				
		return Response.ok(u, MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/")
	@PUT
	public Response edit(User user) {
		
		UserManager manager = (UserManager) Lookup.lookup(UserManager.class.getSimpleName());
		User u = manager.save(user);
		
		return Response.ok(u, MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/")
	@DELETE
	public Response remove(String id) {
		
		UserManager manager = (UserManager) Lookup.lookup(UserManager.class.getSimpleName());
		manager.remove(id);
		
		return Response.ok(MediaType.APPLICATION_JSON).build();
	}
	
	public UserService() {
		UserManager manager = (UserManager) Lookup.lookup(UserManager.class.getSimpleName());
		List<User> all = manager.getAll();
		boolean hasAdmin = false;
		for (User u : all) {
			hasAdmin = u.isAdmin();
		}
		if (!hasAdmin) {
			User adminUser = new User();
			adminUser.setName("Admin");
			adminUser.setEmail("admin@gmail.com");
			adminUser.setPassword("1234");
			adminUser.setAdmin(true);
			manager.save(adminUser);
		}
	}
}
