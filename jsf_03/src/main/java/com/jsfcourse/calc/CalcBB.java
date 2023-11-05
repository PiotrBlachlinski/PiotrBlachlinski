package com.jsfcourse.calc;

import java.io.Serializable;
import java.util.ResourceBundle;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class CalcBB implements Serializable {
	private Double n;
	private Double i;
	private Double r;
	private Double result;

	// Resource injected
	@Inject
	@ManagedProperty("#{txtCalcErr}")
	private ResourceBundle txtCalcErr;

	// Resource injected
	@Inject
	@ManagedProperty("#{txtCalc}")
	private ResourceBundle txtCalc;

	@Inject
	FacesContext ctx;
	
	// Resource loaded "manually"
	// (here after initialization in @PostConstruct method)
	// (locale explicitly given - here based on the view )
//	@PostConstruct
//	public void postConstruct() {
//		// loading resource (notice the full "file" name)
//		txtCalc = ResourceBundle.getBundle("resources.textCalc", ctx.getViewRoot().getLocale());
//		txtCalcErr = ResourceBundle.getBundle("resources.textCalcErr", ctx.getViewRoot().getLocale());
//	}
	
	public Double getN() {
		return n;
	}

	public void setN(Double n) {
		this.n = n;
	}

	public Double getI() {
		return i;
	}

	public void setI(Double i) {
		this.i = i;
	}

	public Double getR() {
		return r;
	}

	public void setR(Double r) {
		this.r = r;
	}
	
	public Double getResult() {
		return result;
	}

	public String calc() {

		//result = x + y;
		
		result = -(n*r)/(12*(1-((12/(12+r))*i)));

		ctx.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, txtCalcErr.getString("calcComputationOkInfo"), null));
		ctx.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, txtCalc.getString("result") + ": " + result, null));

		return null;

	}

}
