package br.com.app.decora.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.bson.types.ObjectId;
import org.jongo.Aggregate;
import org.jongo.Aggregate.ResultsIterator;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.jongo.marshall.jackson.JongoAnnotationIntrospector;

import br.com.app.decora.model.Task;

import com.mongodb.DB;
import com.mongodb.MongoClient;

@Stateless
public class TaskManager {

	private Jongo jongo;
	private MongoCollection tasks;


	public TaskManager() {
		DB db = new MongoClient().getDB("app_decora");
		this.jongo = new Jongo(db);
		this.tasks = this.jongo.getCollection("tasks");
	}
	
	public Task save(Task entity) {
		Task task = new Task();
		task.setTitle(entity.getTitle());
		task.setDescription(entity.getDescription());
		task.setUser(entity.getUser());
		
		tasks.save(task);
		return task;
	}

	public Task get(String id) {
		Task task = tasks.findOne("{'_id':'" + new ObjectId(id)  +"'}").as(Task.class);
		return task;
	}

	public List<Task> getAllFromUser(String id) {
		ResultsIterator<Task> mongoTasks = tasks.aggregate("{$lookup:{task: user {_id:"+ new ObjectId(id) +"}}}").as(Task.class);
		List<Task> list = new ArrayList<Task>();
		mongoTasks.forEach(task->{
			list.add(task);
		});
		return list;
	}
	
	public List<Task> getAll() {
		MongoCursor<Task> mongoTasks = tasks.find().as(Task.class);
		List<Task> list = new ArrayList<Task>();
		mongoTasks.forEach(task->{
			list.add(task);
		});
		return list;
	}
	
	public void remove(Task task) {
		tasks.remove(task.getId());
	}

}
