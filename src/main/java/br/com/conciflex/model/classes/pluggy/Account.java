/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.classes.pluggy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Account {
    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("subtype")
    private String subtype;

    @JsonProperty("name")
    private String name;

    @JsonProperty("balance")
    private double balance;

    @JsonProperty("currencyCode")
    private String currencyCode;

    @JsonProperty("itemId")
    private String itemId;

    @JsonProperty("number")
    private String number;

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("updatedAt")
    private String updatedAt;

    @JsonProperty("marketingName")
    private String marketingName;

    @JsonProperty("taxNumber")
    private String taxNumber;

    @JsonProperty("owner")
    private String owner;

    @JsonProperty("bankData")
    private BankData bankData;

    @JsonProperty("creditData")
    private CreditData creditData;

    public Account() {
        this.id = "";
        this.type = "";
        this.subtype = "";
        this.name = "";
        this.balance = 0;
        this.currencyCode = "";
        this.itemId = "";
        this.number = "";
        this.createdAt = "";
        this.updatedAt = "";
        this.marketingName = "";
        this.taxNumber = "";
        this.owner = "";
        this.bankData = new BankData();
        this.creditData = new CreditData();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", currencyCode='" + currencyCode + '\'' +
                ", itemId='" + itemId + '\'' +
                ", number='" + number + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", marketingName='" + marketingName + '\'' +
                ", taxNumber='" + taxNumber + '\'' +
                ", owner='" + owner + '\'' +
                ", bankData=" + bankData +
                ", creditData='" + creditData + '\'' +
                '}';
    }
}
