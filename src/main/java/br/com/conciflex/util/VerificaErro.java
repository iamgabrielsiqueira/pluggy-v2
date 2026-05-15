/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.util;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public final class VerificaErro {
    private VerificaErro() {

    }

    public static boolean isConnectionError(@NotNull SQLException e) {
        String sqlState = e.getSQLState();
        int errorCode = e.getErrorCode();

        // Exemplos de SQLState comuns para erro de conexão
        // 08S01: Communication link failure
        // 08001: SQL client unable to establish connection
        // 08006: Connection failure
        if ("08S01".equals(sqlState) || "08001".equals(sqlState) || "08006".equals(sqlState)) {
            return true;
        }

        // Exemplo de código de erro específico para MySQL
        // 2003: Can't connect to MySQL server on 'hostname
        if (2003 == errorCode) {
            return true;
        }

        // Pode checar pelo errorCode também, conforme seu driver/documentação
        Log.error("Erro de conexão com código: " + errorCode);

        return false;
    }
}
