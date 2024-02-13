package driver.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpSession;
import driver.jsf.entities.Session;
import driver.jsf.entities.User;


@Stateless
public class SessionDAO {
	private final static String UNIT_NAME = "driver-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	@Inject
	FacesContext context;
	
	@Inject
	ExternalContext extcontext;
	
	private Integer logid = null; //tymczasowa stała wartość,
	//zmienić na pobieranie ID z sesji

	public void create(Session session) {
		em.persist(session);
	}

	public Session merge(Session session) {
		return em.merge(session);
	}

	public void remove(Session session) {
		em.remove(em.merge(session));
	}

	public Session find(Object id) {
		return em.find(Session.class, id);
	}
	
	public List<Session> getFullList() {
		List<Session> list = null;

		Query query = em.createQuery("select p from Session p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	//pełna lista dla pracowników
	public List<Session> getList(Map<String, Object> searchParams) {
		List<Session> list = null;

		// 1. Build query string with parameters
		String select = "select p ";
		String from = "from Session p ";
		String where = "";
		String orderby = "order by p.idsession";

		
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	//lista ogranicznona, tylko dla konkretnego uzytkownika
	
	public List<Session> getSessionList(Map<String, Object> searchParams) {
		
		List<Session> list = null;

		// 1. Build query string with parameters
//		String select = "select p ";
//		String from = "from Session p ";
//		String where = "";
//		String orderby = "order by p.idsession";

		
		
		// ... other parameters ... 

		// 2. Create query object
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		logid = (Integer) session.getAttribute("logid");
		
		Query query = em.createQuery(
				"SELECT p"
				+ " FROM Session p "
				+ "WHERE p.userBean.iduser = :logid")
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
	
	
	
	
	
	
	}
	