/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex;

import br.com.conciflex.controller.pluggy.AccountController;
import br.com.conciflex.model.classes.PluggyAccount;
import br.com.conciflex.model.jdbc.JDBCClienteDAO;
import br.com.conciflex.util.Log;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Log.info("Iniciando aplicação");

        JDBCClienteDAO.getInstance().listar();

        List<PluggyAccount> accounts = AccountController.getInstance().getAccounts();

        int total = accounts.size();

        for (PluggyAccount account : accounts) {
            Log.line();

            int atual = accounts.indexOf(account) + 1;

            Log.info("Buscando informações do item " + account.getItemCreatedId() + " (" + atual + "/" + total + ")");


        }
    }
}