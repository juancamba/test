package com.webhomecover.beans;

import org.apache.struts.action.*;
import java.text.NumberFormat;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

/**
 * WebHomeCover Insurance System.
 *
 * ClaimBean (Struts ActionForm)
 */
public class ClaimBean extends ActionForm implements Serializable {

    private String policyNumber;
    private double claimValue;
    private String claimType;
    private String description;

    public ClaimBean() {
        super();
    }

    public void setType(String type) {
        claimType = type;
    }

    public void setAmount(double amount) {
        this.claimValue = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPolicyNumber(String policynumber) {
        policyNumber = policynumber;
    }

    public String getType() {
        return claimType;
    }

    public double getAmount() {
        return claimValue;
    }

    public String getDescription() {
        return description;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getClaim() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        String valueOfClaimFormatted = currencyFormat.format(claimValue);
        return valueOfClaimFormatted;
    }
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (policyNumber == null || policyNumber.equals("")) {
            errors.add("policyNumber", new ActionMessage("error.policyNumber"));
        }
        if (claimValue == 0.0) {
            errors.add("amount", new ActionMessage("error.amount"));
        }
        if (claimType == null) {
            errors.add("type", new ActionMessage("error.type"));
        }
        if (description == null || description.equals("")) {
            errors.add("description", new ActionMessage("error.description"));
        }
        return errors;
    }
}
