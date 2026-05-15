/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.classes.pluggy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class CreditData {
    @JsonProperty("level")
    private String level;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("balanceCloseDate")
    private String balanceCloseDate;

    @JsonProperty("balanceDueDate")
    private String balanceDueDate;

    @JsonProperty("availableCreditLimit")
    private String availableCreditLimit;

    @JsonProperty("balanceForeignCurrency")
    private String balanceForeignCurrency;

    @JsonProperty("minimumPayment")
    private String minimumPayment;

    @JsonProperty("creditLimit")
    private String creditLimit;

    @JsonProperty("isLimitFlexible")
    private String isLimitFlexible;

    @JsonProperty("holderType")
    private String holderType;

    @JsonProperty("status")
    private String status;

    @JsonProperty("disaggregatedCreditLimits")
    private String disaggregatedCreditLimits;

    @JsonProperty("additionalCards")
    private String additionalCards;

    public CreditData() {
        this.level = "";
        this.brand = "";
        this.balanceCloseDate = "";
        this.balanceDueDate = "";
        this.availableCreditLimit = "";
        this.balanceForeignCurrency = "";
        this.minimumPayment = "";
        this.creditLimit = "";
        this.isLimitFlexible = "";
        this.holderType = "";
        this.status = "";
        this.disaggregatedCreditLimits = "";
        this.additionalCards = "";
    }

    @Override
    public String toString() {
        return "CreditData{" +
                "level=" + level +
                ", brand='" + brand + '\'' +
                ", balanceCloseDate='" + balanceCloseDate + '\'' +
                ", balanceDueDate='" + balanceDueDate + '\'' +
                ", availableCreditLimit=" + availableCreditLimit +
                ", balanceForeignCurrency='" + balanceForeignCurrency + '\'' +
                ", minimumPayment=" + minimumPayment +
                ", creditLimit=" + creditLimit +
                ", isLimitFlexible=" + isLimitFlexible +
                ", holderType='" + holderType + '\'' +
                ", status='" + status + '\'' +
                ", disaggregatedCreditLimits=" + disaggregatedCreditLimits +
                ", additionalCards='" + additionalCards + '\'' +
                '}';
    }
}
