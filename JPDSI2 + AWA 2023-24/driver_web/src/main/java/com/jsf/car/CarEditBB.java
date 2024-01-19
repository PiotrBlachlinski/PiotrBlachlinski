package com.jsf.car;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import driver.jsf.dao.CarDAO;
import driver.jsf.dao.StatusDAO;
import driver.jsf.entities.Car;

@Named
@ViewScoped
public class CarEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_CAR_LIST = "carList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Car car = new Car();
	private Car loaded = null;

	@EJB
	CarDAO carDAO;
	
	@EJB
	StatusDAO statusDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Car getCar() {
		return car;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Car) flash.get("car");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			car = loaded;
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
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (car.getIdcar() == null) {
				// new record
		
				car.setStatus(statusDAO.find(1));
				carDAO.create(car);
			} else {
				// existing record
				carDAO.merge(car);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_CAR_LIST;
	}
}
