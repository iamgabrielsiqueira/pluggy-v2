/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.classes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Banco {
    private long id;
    private String nome;

    public Banco(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
