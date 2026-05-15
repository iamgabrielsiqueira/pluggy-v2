/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.classes.pluggy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public final class GetAccountsResponse {
    @JsonProperty("total")
    private int total;

    @JsonProperty("totalPages")
    private int totalPages;

    @JsonProperty("page")
    private int page;

    @JsonProperty("results")
    private List<Account> accounts;

    public GetAccountsResponse() {
        this.total = 0;
        this.totalPages = 0;
        this.page = 0;
        this.accounts = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "GetAccountsResponse{" +
                "total=" + total +
                ", totalPages=" + totalPages +
                ", page=" + page +
                ", accounts=" + accounts +
                '}';
    }
}
