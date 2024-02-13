package com.jsf.user;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
//import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import driver.jsf.dao.RoleDAO;
import driver.jsf.dao.UserDAO;
import driver.jsf.entities.User;
import driver.jsf.entities.Role;

@Named(value = "loginBB")
@RequestScoped
public class LoginBB {
	private static final String PAGE_LOGOUT = "homepage?faces-redirect=true";
	private static final String PAGE_USER = "profile?faces-redirect=true";
	private static final String PAGE_ADMIN = "userList?faces-redirect=true";
	private static final String PAGE_WORKER = "carList?faces-redirect=true";
	//private static final String PAGE_DRIVER = "userList?faces-redirect=true";
	//private static final String PAGE_BLOCKED = "userList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String username;
	private String password;
	private Integer logid;
	private String logusername;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	
	@Inject
	ExternalContext extcontext;
	
	@Inject
    FacesContext facesContext;
	
	@EJB
	UserDAO userDAO;
	
	@EJB
	RoleDAO roleDAO;

	public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		//flash.put("logid", "adam");
		
//		FacesContext facesContext = FacesContext.getCurrentInstance();
//        facesContext.getExternalContext().getFlash().put("logid", );
		

		// 1. verify login and password - get User from "database"
		User user = userDAO.getUserFromDatabase(username, password);

		// 2. if bad login or password - stay with error info
		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Niepoprawny login lub hasło", null));
			return PAGE_STAY_AT_THE_SAME;
		}
	
		
		HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("logid", user.);
		
		if(user.getRole().getRolename().equals("user")) {
			logid = user.getIduser();
			session.setAttribute("logid", logid);
			logusername = user.getUsername();
			session.setAttribute("logusername", logusername);
			
			return PAGE_USER;			
		}
		
		if(user.getRole().getRolename().equals("admin")) {
			return PAGE_ADMIN;			
		}
		
		if(user.getRole().getRolename().equals("worker")) {
			return PAGE_WORKER;			
		}
		
		if(user.getRole().getRolename().equals("blocked")) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Konto zostało zablokowane."
					+ " Skontaktuj się z administracją", null));
			return PAGE_STAY_AT_THE_SAME;			
		}
		

		else {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"błąd roli", null));
			return PAGE_STAY_AT_THE_SAME;
		}

//	
//		//store RemoteClient with request info in session (needed for SecurityFilter)
//		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
//		client.store(request);

		// and enter the system (now SecurityFilter will pass the request)
		
		//return PAGE_LIST;
		
	
	}
	
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		//Invalidate session
		// - all objects within session will be destroyed
		// - new session will be created (with new ID)
		session.invalidate();
		return PAGE_LOGOUT;
	}

	
	
}
