package driver.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer iduser;

	@Lob
	private String address;

	@Temporal(TemporalType.DATE)
	private Date cdate;

	private int cwho;

	@Temporal(TemporalType.DATE)
	private Date mdate;

	private int mwho;

	private String name;

	@Lob
	private String password;

	@ManyToOne
	@JoinColumn(name="role")
	private Role role;

	private String surname;

	@Lob
	private String username;

	//bi-directional many-to-one association to Driver
	@OneToMany(mappedBy="user")
	private List<Driver> drivers;

	//bi-directional one-to-one association to Role
	//@OneToOne(mappedBy="user")
	//private Role roleBean;

	//bi-directional many-to-one association to Session
	@OneToMany(mappedBy="userBean")
	private List<Session> sessions;

	public User() {
	}

	public Integer getIduser() {
		return this.iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCdate() {
		return this.cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public int getCwho() {
		return this.cwho;
	}

	public void setCwho(int cwho) {
		this.cwho = cwho;
	}

	public Date getMdate() {
		return this.mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public int getMwho() {
		return this.mwho;
	}

	public void setMwho(int mwho) {
		this.mwho = mwho;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//public int getRole() {
	//	return this.role;
	//}

	//public void setRole(int role) {
	//	this.role = role;
	//}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Driver> getDrivers() {
		return this.drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public Driver addDriver(Driver driver) {
		getDrivers().add(driver);
		driver.setUser(this);

		return driver;
	}

	public Driver removeDriver(Driver driver) {
		getDrivers().remove(driver);
		driver.setUser(null);

		return driver;
	}

////	public Role getRoleBean() {
////		return this.roleBean;
////	}
//
//	public void setRoleBean(Role roleBean) {
//		this.roleBean = roleBean;
//	}
	
	

	public List<Session> getSessions() {
		return this.sessions;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public Session addSession(Session session) {
		getSessions().add(session);
		session.setUserBean(this);

		return session;
	}

	public Session removeSession(Session session) {
		getSessions().remove(session);
		session.setUserBean(null);

		return session;
	}
	

}