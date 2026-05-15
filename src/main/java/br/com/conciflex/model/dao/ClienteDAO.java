/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.dao;

import br.com.conciflex.model.classes.Cliente;

public interface ClienteDAO {
    void listar();

    Cliente buscarPorId(long id);
}
