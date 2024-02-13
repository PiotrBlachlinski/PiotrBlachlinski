package driver.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import driver.jsf.entities.Car;
import driver.jsf.entities.Status;


@Stateless
public class CarDAO {
	private final static String UNIT_NAME = "driver-simplePU";
	
	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	//private Integer status = 1;
	private String status = "available";
	
	public void create(Car car) {
		em.persist(car);
	}

	public Car merge(Car car) {
		return em.merge(car);
	}

	public void remove(Car car) {
		em.remove(em.merge(car));
	}

	public Car find(Object id) {
		return em.find(Car.class, id);
	}
	
	public List<Car> getFullList() {
		List<Car> list = null;

		Query query = em.createQuery("select p from Car p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Car> getList(Map<String, Object> searchParams) {
		List<Car> list = null;

		// 1. Build query string with parameters
		String select = "select p ";
		String from = "from Car p ";
		String where = "";
		String orderby = "order by p.idcar asc, p.model";

		// search for surname
		String manufacturer = (String) searchParams.get("manufacturer");
		if (manufacturer != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.manufacturer like :manufacturer ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (manufacturer != null) {
			query.setParameter("manufacturer", manufacturer+"%");
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
	
	/////////////
	
	public List<Car> getListUser(Map<String, Object> searchParams) {
		
		List<Car> list = null;

		Query query = em.createQuery(
				"SELECT p"
				+ " FROM Car p "
				+ "WHERE p.status.statusname = :status")
		.setParameter("status", status);
		
		// 3. Set configured parameters
//		if (manufacturer != null) {
//			query.setParameter("manufacturer", manufacturer+"%");
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