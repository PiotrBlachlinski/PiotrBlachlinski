package com.jsf.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import driver.jsf.dao.SessionDAO;
import driver.jsf.dao.UserDAO;
import driver.jsf.dao.DriverDAO;
import driver.jsf.dao.CarDAO;
import driver.jsf.entities.Session;

@Named
@RequestScoped
public class SessionListBB {
	private static final String PAGE_USER_EDIT = "userEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private Integer idsession;
	
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	SessionDAO sessionDAO;
	
	@EJB
	UserDAO userDAO;
	
	@EJB
	DriverDAO driverDAO;
	
	@EJB
	CarDAO carDAO;
	
	public Integer getIdsession() {
		return idsession;
	}

	public void setIdsession(Integer idsession) {
		this.idsession = idsession;
	}

	public List<Session> getFullList(){
		return sessionDAO.getFullList();
	}
	
	public List<Session> getList(){
		List<Session> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (idsession != null){
			searchParams.put("surname", idsession);
		}
		
		//2. Get list
		list = sessionDAO.getList(searchParams);
		
		return list;
	}
	
//	public String newUser(){
//		User user = new User();
//		
//		//1. Pass object through session
//		//HttpSession session = (HttpSession) extcontext.getSession(true);
//		//session.setAttribute("person", person);
//		
//		//2. Pass object through flash	
//		flash.put("user", user);
//		
//		return PAGE_USER_EDIT;
//	}
//
//	public String editUser(User user){
//		//1. Pass object through session
//		//HttpSession session = (HttpSession) extcontext.getSession(true);
//		//session.setAttribute("person", person);
//		
//		//2. Pass object through flash 
//		flash.put("user", user);
//		
//		return PAGE_USER_EDIT;
//	}

//	public String deletePerson(User user){
//		userDAO.remove(user);
//		return PAGE_STAY_AT_THE_SAME;
//	}
	
	
	
	
	
	
	
	
	
	
	
	}
