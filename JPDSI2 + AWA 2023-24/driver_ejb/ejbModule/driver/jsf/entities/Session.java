package driver.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sessions database table.
 * 
 */
@Entity
@Table(name="sessions")
@NamedQuery(name="Session.findAll", query="SELECT s FROM Session s")
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idsession;

	@Temporal(TemporalType.DATE)
	private Date date;

	//bi-directional many-to-one association to Car
	@ManyToOne
	@JoinColumn(name="car")
	private Car carBean;

	//bi-directional many-to-one association to Driver
	@ManyToOne
	@JoinColumn(name="driver")
	private Driver driverBean;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user")
	private User userBean;

	public Session() {
	}

	public int getIdsession() {
		return this.idsession;
	}

	public void setIdsession(int idsession) {
		this.idsession = idsession;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Car getCarBean() {
		return this.carBean;
	}

	public void setCarBean(Car carBean) {
		this.carBean = carBean;
	}

	public Driver getDriverBean() {
		return this.driverBean;
	}

	public void setDriverBean(Driver driverBean) {
		this.driverBean = driverBean;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}