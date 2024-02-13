package driver.jsf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpSession;
import driver.jsf.entities.User;

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "driver-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	
	@Inject
	FacesContext context;
	
	@Inject
	ExternalContext extcontext;
	
	//HttpSession session = (HttpSession) extcontext.getSession(true);
	
	//HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	// loaded = (Person) session.getAttribute("person");
	
	//private Integer logid;
	private Integer logid = null;
	//private Integer logid = (Integer) session.getAttribute("logid");
	//tymczasowa stała wartość,
	//zmienić na pobieranie ID z sesji

	public void create(User user) {
		em.persist(user);
	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object id) {
		return em.find(User.class, id);
	}
	
	public List<User> getFullList() {
		List<User> list = null;

		Query query = em.createQuery("select p from User p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<User> getList(Map<String, Object> searchParams) {
		List<User> list = null;

		// 1. Build query string with parameters
		String select = "select p ";
		String from = "from User p ";
		String where = "";
		String orderby = "order by p.iduser asc, p.surname";

		// search for surname
		String surname = (String) searchParams.get("surname");
		if (surname != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.surname like :surname ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (surname != null) {
			query.setParameter("surname", surname+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public User getUserFromDatabase(String username, String password) {
		
		User u = null;
			
		Query query = em.createQuery(
				"SELECT u"
				+ " FROM User u "
				+ "WHERE u.username = :username AND u.password = :password")
		.setParameter("username", username)
		.setParameter("password", password);
		
		//u = (User)query.getSingleResult();
		
		try {
			u = (User)query.getSingleResult();
		} catch (NoResultException e) {
			u = null;
		}

		return u;
		
	}
	
	public List<User> getProfileList(Map<String, Object> searchParams) {
		
		List<User> list = null;

//		// 1. Build query string with parameters
//		String select = "select p ";
//		String from = "from User p ";
//		String where = "";
//		String orderby = "";
//
//		// search for surname
//		String surname = (String) searchParams.get("surname");
//		if (surname != null) {
//			if (where.isEmpty()) {
//				where = "where ";
//			} else {
//				where += "and ";
//			}
//			where += "p.surname like :surname ";
//		}
		
		// ... other parameters ... 

		// 2. Create query object
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		logid = (Integer) session.getAttribute("logid");
		
		Query query = em.createQuery(
				"SELECT p"
				+ " FROM User p "
				+ "WHERE p.iduser = :logid")
		.setParameter("logid", logid);
		
		

		// 3. Set configured parameters
//		if (surname != null) {
//			query.setParameter("surname", surname+"%");
//		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

//	// simulate retrieving roles of a User from DB
//	public List<String> getUserRolesFromDatabase(User user) {
//		
//		ArrayList<String> roles = new ArrayList<String>();
//		
//		if (user.getLogin().equals("user1")) {
//			roles.add("user");
//		}
//		if (user.getLogin().equals("user2")) {
//			roles.add("manager");
//		}
//		if (user.getLogin().equals("user3")) {
//			roles.add("admin");
//		}
//		
//		return roles;
//	}
	
	
	
	
	
	}
	