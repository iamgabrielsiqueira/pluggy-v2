/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.classes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PluggyAccount {
    private long id;
    private String itemCreatedId;
    private Cliente cliente;

    public PluggyAccount(long id, String itemCreatedId, Cliente cliente) {
        this.id = id;
        this.itemCreatedId = itemCreatedId;
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "PluggyAccount{" +
                "id=" + id +
                ", itemCreatedId='" + itemCreatedId + '\'' +
                ", cliente=" + cliente.getNome() +
                '}';
    }
}
