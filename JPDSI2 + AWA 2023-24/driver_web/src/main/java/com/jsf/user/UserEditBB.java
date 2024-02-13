package com.jsf.user;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import driver.jsf.dao.UserDAO;
import driver.jsf.dao.RoleDAO;
import driver.jsf.entities.User;

@Named
@ViewScoped
public class UserEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_USER_LIST = "homepage?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private User user = new User();
	private User loaded = null;

	@EJB
	UserDAO userDAO;
	
	@EJB
	RoleDAO roleDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public User getUser() {
		return user;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (User) flash.get("user");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			user = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}

	}

	public String saveData() {
		// no User object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (user.getIduser() == null) {
				// new record
				user.setAddress("");
				user.setCdate(new Date());
				user.setMdate(new Date());
				user.setCwho(1);
				user.setMwho(1);
				user.setRole(roleDAO.find(1));
				userDAO.create(user);
			} else {
				// existing record
				user.setMdate(new Date());
				userDAO.merge(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_USER_LIST;
	}
	
	public String setRoleAdmin() {
		// no User object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (user.getIduser() == null) {

			} else {
				// existing record
				user.setRole(roleDAO.find(2));
				user.setMdate(new Date());
				userDAO.merge(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_USER_LIST;
	}
}
