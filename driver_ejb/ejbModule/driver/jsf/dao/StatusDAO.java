package driver.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import driver.jsf.entities.Status;


@Stateless
public class StatusDAO {
	private final static String UNIT_NAME = "driver-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Status status) {
		em.persist(status);
	}

	public Status merge(Status status) {
		return em.merge(status);
	}

	public void remove(Status status) {
		em.remove(em.merge(status));
	}

	public Status find(Object id) {
		return em.find(Status.class, id);
	}
	
	public List<Status> getFullList() {
		List<Status> list = null;

		Query query = em.createQuery("select r from Status r");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	
	
	
	
	}
	