package driver.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import driver.jsf.entities.Driver;


@Stateless
public class DriverDAO {
	private final static String UNIT_NAME = "driver-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Driver driver) {
		em.persist(driver);
	}

	public Driver merge(Driver driver) {
		return em.merge(driver);
	}

	public void remove(Driver driver) {
		em.remove(em.merge(driver));
	}

	public Driver find(Object id) {
		return em.find(Driver.class, id);
	}
	
	public List<Driver> getFullList() {
		List<Driver> list = null;

		Query query = em.createQuery("select r from Driver r");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	
	
	
	
	}
	