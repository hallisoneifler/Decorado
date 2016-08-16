package br.com.app.decora.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Init;
import javax.ejb.Stateless;

import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import br.com.app.decora.model.User;

import com.mongodb.DB;
import com.mongodb.MongoClient;

@Stateless
public class UserManager {

	private Jongo jongo;
	private MongoCollection users;
	
	public UserManager() {
		DB db = new MongoClient().getDB("app_decora");
		this.jongo = new Jongo(db);
		this.users = this.jongo.getCollection("users");
	}
	
	public User save(User entity) {
		User user = new User();
    	user.setName(entity.getName());
    	user.setEmail(entity.getEmail());
    	
    	
    	user.setPassword(entity.getPassword());
    	user.setAdmin(entity.isAdmin());
    	
    	users.save(user);
		return user;
	}

	public User get(String id) {
		User user = (User) users.findOne(new ObjectId(id)).as(User.class);
		return user;
	}

	public User login(String email, String passaword) {
		User user = users.findOne("{'email':'" + email +"', 'password':'" + passaword + "'}").as(User.class);
		return user;
	}
	
	public List<User> getAll() {
		MongoCursor<User> mongoUsers = users.find().as(User.class);
		List<User> list = new ArrayList<User>();
		mongoUsers.forEach(user->{
			list.add(user);
		});
		return list;
	}
	
	public void remove(String id) {
		users.remove(id);
	}
}
