package com.jsfcourse.bmi;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CalcBMI {
	private String x;
	private String y;
	private Double result;

	@Inject
	FacesContext ctx;

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public boolean doTheMath() {
		try {
			double x = Double.parseDouble(this.x);
			double y = Double.parseDouble(this.y);

			result = x/(y*y);

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "obliczenia wykonane poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "podaj poprawne parametry", null));
			return false;
		}
	}

	public String bmi() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
