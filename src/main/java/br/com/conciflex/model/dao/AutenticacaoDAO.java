/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.dao;

import br.com.conciflex.model.classes.pluggy.Autenticacao;

public interface AutenticacaoDAO {
    void salvar(Autenticacao autenticacao);

    Autenticacao buscarUltima();
}
