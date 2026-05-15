/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.controller.pluggy;

import br.com.conciflex.model.classes.PluggyAccount;
import br.com.conciflex.model.jdbc.JDBCPluggyAccountDAO;
import br.com.conciflex.util.Log;

import java.util.List;

public class AccountController {
    private static AccountController instance;

    private AccountController() {
    }

    public static AccountController getInstance() {
        if (instance == null) {
            instance = new AccountController();
        }

        return instance;
    }

    public List<PluggyAccount> getAccounts() {
        Log.info("Listando contas conectadas");

        List<PluggyAccount> accounts = JDBCPluggyAccountDAO.getInstance().listar();

        if (accounts.isEmpty()) {
            Log.warn("Nenhuma conta encontrada");
        } else {
            Log.info("Quantidade de contas: " + accounts.size());
        }

        return accounts;
    }
}
