/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.classes.pluggy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class BankData {
    @JsonProperty("transferNumber")
    private String transferNumber;

    @JsonProperty("closingBalance")
    private String closingBalance;

    @JsonProperty("automaticallyInvestedBalance")
    private String automaticallyInvestedBalance;

    @JsonProperty("overdraftContractedLimit")
    private String overdraftContractedLimit;

    @JsonProperty("overdraftUsedLimit")
    private String overdraftUsedLimit;

    @JsonProperty("unarrangedOverdraftAmount")
    private String unarrangedOverdraftAmount;

    public BankData() {
        this.transferNumber = "";
        this.closingBalance = "";
        this.automaticallyInvestedBalance = "";
        this.overdraftContractedLimit = "";
        this.overdraftUsedLimit = "";
        this.unarrangedOverdraftAmount = "";
    }

    @Override
    public String toString() {
        return "BankData{" +
                "transferNumber='" + transferNumber + '\'' +
                ", closingBalance=" + closingBalance +
                ", automaticallyInvestedBalance=" + automaticallyInvestedBalance +
                ", overdraftContractedLimit=" + overdraftContractedLimit +
                ", overdraftUsedLimit=" + overdraftUsedLimit +
                ", unarrangedOverdraftAmount=" + unarrangedOverdraftAmount +
                '}';
    }
}
