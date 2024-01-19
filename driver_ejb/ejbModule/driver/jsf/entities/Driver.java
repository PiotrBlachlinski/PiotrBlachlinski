package driver.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the driver database table.
 * 
 */
@Entity
@NamedQuery(name="Driver.findAll", query="SELECT d FROM Driver d")
public class Driver implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iddriver;

	private String speciality;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="iduser")
	private User user;

	//bi-directional many-to-one association to Session
	@OneToMany(mappedBy="driverBean")
	private List<Session> sessions;

	public Driver() {
	}

	public int getIddriver() {
		return this.iddriver;
	}

	public void setIddriver(int iddriver) {
		this.iddriver = iddriver;
	}

	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Session> getSessions() {
		return this.sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public Session addSession(Session session) {
		getSessions().add(session);
		session.setDriverBean(this);

		return session;
	}

	public Session removeSession(Session session) {
		getSessions().remove(session);
		session.setDriverBean(null);

		return session;
	}

}