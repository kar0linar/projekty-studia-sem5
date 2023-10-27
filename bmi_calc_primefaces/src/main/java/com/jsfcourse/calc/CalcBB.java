package com.jsfcourse.calc;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Named
@RequestScoped
public class CalcBB {
    private String x;
    private String y;
    private String result; 

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

    public String getResult() {
        return result;
    }

    public String calc() {
        try {
            double x = Double.parseDouble(this.x);
            double y = Double.parseDouble(this.y);

            double rawResult = x / (y * y) * 10000;
            BigDecimal roundedResult = new BigDecimal(rawResult).setScale(2, RoundingMode.HALF_UP); // Zaokrąglamy wynik do 2 miejsc
            result = roundedResult.toString(); // Przekształcamy wynik na String

            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "obliczenia wykonane poprawnie", null));
            return "showresult";
        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "wprowadz poprawne parametry!", null));
            return null;
        }
    }

    public String info() {
        return "info";
    }
}