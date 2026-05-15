/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.Time;

public final class DataHora {
    private DataHora() {

    }

    @NotNull
    @Contract(" -> new")
    public static Date getData() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    @NotNull
    @Contract(" -> new")
    public static Time getHora() {
        return new java.sql.Time(System.currentTimeMillis());
    }

    public static @NotNull String toString(@NotNull Date data) {
        String ano = data.toString().substring(0, 4);
        String mes = data.toString().substring(5, 7);
        String dia = data.toString().substring(8, 10);

        return dia + "/" + mes + "/" + ano;
    }
}
