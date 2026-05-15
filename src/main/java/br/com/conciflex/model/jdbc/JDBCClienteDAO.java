/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.jdbc;

import br.com.conciflex.model.DataSource;
import br.com.conciflex.model.classes.Cliente;
import br.com.conciflex.model.dao.ClienteDAO;
import br.com.conciflex.util.Log;
import br.com.conciflex.util.RetryUtil;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCClienteDAO implements ClienteDAO {
    private static JDBCClienteDAO instance;

    private JDBCClienteDAO() {

    }

    public static JDBCClienteDAO getInstance() {
        if (instance == null) {
            instance = new JDBCClienteDAO();
        }

        return instance;
    }

    private final List<Cliente> clientes = new ArrayList<>();

    @Override
    public void listar() {
        Log.info("Listando clientes");

        clientes.clear();

        @Language("mysql")
        String sql = "SELECT clientes.CODIGO as id, clientes.NOME_FANTASIA as nome FROM clientes";

        RetryUtil.executarComRetryVoid(() -> {
            try (Connection connection = DataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Cliente cliente = this.carregar(resultSet);
                        clientes.add(cliente);
                    }
                }
            }
        });

        if (clientes.isEmpty()) {
            Log.warn("Nenhum cliente encontrado");
        } else {
            Log.info("Quantidade de clientes encontrados: " + clientes.size());
        }
    }

    @NotNull
    private Cliente carregar(@NotNull ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String nome = resultSet.getString("nome");

        return new Cliente(id, nome);
    }

    @Override
    public Cliente buscarPorId(long id) {
        Optional<Cliente> optionalCliente = clientes.stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst();

        if (optionalCliente.isPresent()) {
            return optionalCliente.get();
        } else {
            throw new RuntimeException("Cliente não encontrado, id: " + id);
        }
    }
}
