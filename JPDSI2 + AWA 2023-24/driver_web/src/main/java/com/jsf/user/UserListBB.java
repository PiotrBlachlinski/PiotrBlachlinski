package com.jsf.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;
import driver.jsf.dao.RoleDAO;
import driver.jsf.dao.UserDAO;
import driver.jsf.entities.User;

@Named
@RequestScoped
public class UserListBB {
	private static final String PAGE_USER_EDIT = "userEdit?faces-redirect=true";
	private static final String PAGE_USERLIST = "userList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private String surname;
	
	//tylko testowa wartość
	private String logusername; 
	
	
	@Inject
	FacesContext context;
	
	@Inject
    FacesContext facesContext;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;
	
	@EJB
	RoleDAO roleDAO;
	
	
//	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
//	
//	private String logusername = (String) session.getAttribute("logusername");	
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogusername() {
		return logusername;
	}

	public void setLogusername(String logusername) {
		this.logusername = logusername;
	}
	
	
	
//	public void onLoad() throws IOException {
//		// 1. load person passed through session
//		 HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
//		 logusername = (String) session.getAttribute("logusername");
//
//		// cleaning: attribute received => delete it from session
//		if (logusername != null) {
//			// session.removeAttribute("person");
//		} else {
//			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
//			// if (!context.isPostback()) { //possible redirect
//			// context.getExternalContext().redirect("personList.xhtml");
//			// context.responseComplete();
//			// }
//		}
//
//	}
	
	

	public List<User> getFullList(){
		return userDAO.getFullList();
	}
	
	public List<User> getList(){
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		logusername = (String) session.getAttribute("logusername");
				
//		flash.put("logname", "adam");
		
//		FacesContext facesContext = FacesContext.getCurrentInstance();
//        facesContext.getExternalContext().getFlash().put("logname", "Adam");
//              
        //loguser =  flash.get("loguser");
		

		List<User> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (surname != null && surname.length() > 0){
			searchParams.put("surname", surname);
		}
		
//		surname = "Nocna";
//		searchParams.put("surname", surname);
		
		
		//2. Get list
		list = userDAO.getList(searchParams);
		
		return list;
	}
	
	public String newUser(){
		User user = new User();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("user", user);
		
		return PAGE_USER_EDIT;
	}

	public String editUser(User user){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("user", user);
		
		return PAGE_USER_EDIT;
	}

	public String deletePerson(User user){
		userDAO.remove(user);
		return PAGE_STAY_AT_THE_SAME;
	}
	
	public String setRoleUser(User user){
		user.setRole(roleDAO.find(1));
		userDAO.merge(user);
		return PAGE_USERLIST;
	}
	
	public String setRoleAdmin(User user){
		user.setRole(roleDAO.find(2));
		userDAO.merge(user);
		return PAGE_USERLIST;
	}
	
	public String setRoleDriver(User user){
		user.setRole(roleDAO.find(3));
		userDAO.merge(user);
		return PAGE_USERLIST;
	}
	
	public String setRoleWorker(User user){
		user.setRole(roleDAO.find(4));
		userDAO.merge(user);
		return PAGE_USERLIST;
	}
	
	public String setRoleBlocked(User user){
		user.setRole(roleDAO.find(5));
		userDAO.merge(user);
		return PAGE_USERLIST;
	}
	
	
	

	
	}
