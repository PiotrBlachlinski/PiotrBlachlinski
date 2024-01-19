package com.jsf.car;

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

import driver.jsf.dao.CarDAO;
import driver.jsf.dao.StatusDAO;
import driver.jsf.entities.Car;


@Named
@RequestScoped
public class CarListBB {
	private static final String PAGE_CAR_EDIT = "carEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private String manufacturer;
	
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	CarDAO carDAO;
	
	@EJB
	StatusDAO statusDAO;
	
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<Car> getFullList(){
		return carDAO.getFullList();
	}

	public List<Car> getList(){
		List<Car> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (manufacturer != null && manufacturer.length() > 0){
			searchParams.put("manufacturer", manufacturer);
		}
		
		//2. Get list
		list = carDAO.getList(searchParams);
		
		return list;
	}
	
	public String newCar(){
		Car car = new Car();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("car", car);
		
		return PAGE_CAR_EDIT;
	}

	public String editCar(Car car){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("car", car);
		
		return PAGE_CAR_EDIT;
	}

	public String deleteCar(Car car){
		carDAO.remove(car);
		return PAGE_STAY_AT_THE_SAME;
	}
	
}