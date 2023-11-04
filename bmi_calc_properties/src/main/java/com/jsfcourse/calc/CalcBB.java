package com.jsfcourse.calc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
	private Double x;
	private Double y;
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
	
	
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getResult() {
		return result;
	}

	public String calc() {
	    // Oblicz wynik
	    double rawResult = x / (y * y) * 10000;

	    // Zaokrąglij wynik do 2 miejsc po przecinku
	    BigDecimal roundedResult = BigDecimal.valueOf(rawResult).setScale(2, RoundingMode.HALF_UP);

	    // Przypisz zaokrąglony wynik do pola result
	    result = roundedResult.doubleValue();

	    ctx.addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_INFO, txtCalcErr.getString("calcComputationOkInfo"), null));
	    ctx.addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_WARN, txtCalc.getString("result") + ": " + result, null));

	    return null;
	}

}
