package com.jsf.user;

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
import driver.jsf.entities.User;
import driver.jsf.entities.Session;


@Named
@RequestScoped
public class ProfileBB {
	private static final String PAGE_USER_EDIT = "userEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private Integer iduser;
	
	@Inject
	ExternalContext extcontext;
	
	@Inject
	FacesContext context;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;
	
	@EJB
	SessionDAO sessionDAO;

	
	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}
	
	public List<User> getList(){
		List<User> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
//		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
//		iduser = (Integer)session.getAttribute("logusername");
//		
//		if (iduser != null){
//			searchParams.put("iduser", iduser);
//		}
		
		//2. Get list
		list = userDAO.getProfileList(searchParams);
		
		return list;
	}
	
	public List<Session> getList2(){
		List<Session> list2 = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
//		if (idsession != null){
//			searchParams.put("surname", idsession);
//		}
		
		//2. Get list
		list2 = sessionDAO.getSessionList(searchParams);
		
		return list2;
	}
	

	}
