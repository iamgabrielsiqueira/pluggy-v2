/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex;

import br.com.conciflex.controller.pluggy.AccountController;
import br.com.conciflex.controller.pluggy.AutenticacaoController;
import br.com.conciflex.model.classes.PluggyAccount;
import br.com.conciflex.model.classes.pluggy.Account;
import br.com.conciflex.model.classes.pluggy.Autenticacao;
import br.com.conciflex.model.jdbc.JDBCClienteDAO;
import br.com.conciflex.services.AccountService;
import br.com.conciflex.util.Log;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Log.info("Iniciando aplicação");

        Autenticacao autenticacao = AutenticacaoController.getInstance().getAutenticacao();

        JDBCClienteDAO.getInstance().listar();

        List<PluggyAccount> itensCriados = AccountController.getInstance().getAccounts();

        int total = itensCriados.size();

        for (PluggyAccount item : itensCriados) {
            Log.line();

            int atual = itensCriados.indexOf(item) + 1;

            Log.info("Buscando informações do item " + item.getItemCreatedId() + " (" + atual + "/" + total + ")");

            List<Account> accounts = AccountService.getInstance().getAccounts(autenticacao, item.getItemCreatedId());

            if (accounts.isEmpty()) {
                Log.warn("Nenhuma conta associada");
            } else {
                Log.info("Quantidade de contas associadas");
            }

            for (Account account : accounts) {
                switch (account.getSubtype()) {
                    case "CREDIT_CARD" ->
                            Log.info("Conta " + account.getId() + " é do tipo cartão de crédito - " + account.getName());
                    case "CHECKING_ACCOUNT" -> {
                        Log.info("Conta " + account.getId() + " é do tipo conta corrente");

                        String[] partes = account.getBankData().getTransferNumber().split("/");

                        if (partes.length == 3) {
                            String banco = partes[0];
                            String agencia = partes[1];
                            String conta = partes[2];

                            String saldo = account.getBankData().getClosingBalance();

                            Log.info("Banco " + banco + " - Ag. " + agencia + " - Conta. " + conta + " [Saldo: " + saldo + "]");
                        } else {
                            Log.error("Não foi possivel consultar dados bancários");
                        }
                    }
                    case "SAVINGS_ACCOUNT" -> {
                        Log.info("Conta " + account.getId() + " é do tipo investimento");

                        String[] partes = account.getBankData().getTransferNumber().split("/");

                        String banco = partes[0];
                        String agencia = partes[1];
                        String conta = partes[2];

                        String saldo = account.getBankData().getClosingBalance();

                        Log.info("Banco " + banco + " - Ag. " + agencia + " - Conta. " + conta + " [Saldo: " + saldo + "]");
                    }
                    default -> Log.error("Subtipo não identificado: " + account.getSubtype());
                }
            }
        }
    }
}