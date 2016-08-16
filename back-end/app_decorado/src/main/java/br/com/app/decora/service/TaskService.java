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
import br.com.app.decora.ejb.TaskManager;
import br.com.app.decora.ejb.UserManager;
import br.com.app.decora.model.Task;
import br.com.app.decora.model.User;

@Path("/tasks")
public class TaskService {

	
	@Path("/")
	@GET
	public Response getTasks() {
		
		TaskManager manager = (TaskManager) Lookup.lookup(TaskManager.class.getSimpleName());
		List<Task> list = manager.getAll();
			
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/{user}")
	@GET
	public Response getTask(@PathParam("user") String id) {
		
		TaskManager manager = (TaskManager) Lookup.lookup(TaskManager.class.getSimpleName());
		List<Task> list = manager.getAllFromUser(id);
		
		return Response.ok(list, MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/")
	@POST
	public Response save(Task task) {
		
		TaskManager manager = (TaskManager) Lookup.lookup(TaskManager.class.getSimpleName());
		UserManager userManager = (UserManager) Lookup.lookup(UserManager.class.getSimpleName());
		User user = userManager.get(task.getUser().getId());
		task.setUser(user);
		Task nova = manager.save(task);
				
		return Response.ok(nova, MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/")
	@PUT
	public Response edit(Task task) {
		
		TaskManager manager = (TaskManager) Lookup.lookup(TaskManager.class.getSimpleName());
		Task t = manager.save(task);
		
		return Response.ok(t, MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/")
	@DELETE
	public Response remove(Task task) {
		
		TaskManager manager = (TaskManager) Lookup.lookup(TaskManager.class.getSimpleName());
		manager.remove(task);
		
		return Response.ok().build();
	}
}
