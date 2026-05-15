/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.classes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Cliente {
    private long id;
    private String nome;

    public Cliente(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
