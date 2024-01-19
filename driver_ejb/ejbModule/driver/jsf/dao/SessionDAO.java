package driver.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import driver.jsf.entities.Session;


@Stateless
public class SessionDAO {
	private final static String UNIT_NAME = "driver-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

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

		Query query = em.createQuery("select r from Session r");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	
	
	
	
	}
	