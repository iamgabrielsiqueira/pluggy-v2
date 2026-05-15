/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.classes.pluggy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class Autenticacao {
    @JsonProperty("apiKey")
    private String apiKey;

    @JsonIgnore
    private Date data;

    @JsonIgnore
    private Time hora;

    public Autenticacao() {
        this.apiKey = "";
    }

    @Override
    public String toString() {
        return "Autenticacao{" +
                "apiKey='" + apiKey + '\'' +
                ", data=" + data +
                ", hora=" + hora +
                '}';
    }
}
