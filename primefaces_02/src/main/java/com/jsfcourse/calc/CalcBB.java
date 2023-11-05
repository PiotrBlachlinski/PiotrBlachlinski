package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CalcBB {
	private String n;
	private String i;
	private String r;
	private Double result;

	@Inject
	FacesContext ctx;

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public Double getResult() {
		return result;
	}

	public String calc() {
		try {
			double n = Double.parseDouble(this.n);
			double i = Double.parseDouble(this.i);
			double r = Double.parseDouble(this.r);
			//result = x + y;
			
			result = -(n*r)/(12*(1-((12/(12+r))*i)));
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return "showresult"; 
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return null; 
		}
				
	}

	public String info() {
		return "info"; 
	}
}
