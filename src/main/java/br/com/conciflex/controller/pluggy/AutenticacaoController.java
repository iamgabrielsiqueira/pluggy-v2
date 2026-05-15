/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.controller.pluggy;

import br.com.conciflex.model.classes.pluggy.Autenticacao;
import br.com.conciflex.model.jdbc.JDBCAutenticacaoDAO;
import br.com.conciflex.services.AutenticacaoService;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class AutenticacaoController {
    private static AutenticacaoController instance;

    private AutenticacaoController() {

    }

    public static AutenticacaoController getInstance() {
        if (instance == null) {
            instance = new AutenticacaoController();
        }

        return instance;
    }

    public Autenticacao getAutenticacao() {
        Autenticacao autenticacao = JDBCAutenticacaoDAO.getInstance().buscarUltima();

        if (autenticacao == null) {
            return AutenticacaoService.getInstance().getAutenticacao();
        } else {
            boolean expirou = this.verificarExpirou(autenticacao);

            if (expirou) {
                return AutenticacaoService.getInstance().getAutenticacao();
            } else {
                return autenticacao;
            }
        }
    }

    private boolean verificarExpirou(@NotNull Autenticacao autenticacao) {
        Date dataAutenticacao = autenticacao.getData();
        Time horaAutenticacao = autenticacao.getHora();

        LocalDateTime momentoAutenticacao = LocalDateTime.of(
                dataAutenticacao.toLocalDate(),
                horaAutenticacao.toLocalTime()
        );

        return LocalDateTime.now().isAfter(momentoAutenticacao.plusMinutes(30));
    }
}
