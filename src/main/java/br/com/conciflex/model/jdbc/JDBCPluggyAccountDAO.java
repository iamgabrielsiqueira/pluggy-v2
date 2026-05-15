/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.jdbc;

import br.com.conciflex.model.DataSource;
import br.com.conciflex.model.classes.Cliente;
import br.com.conciflex.model.classes.PluggyAccount;
import br.com.conciflex.model.dao.PluggyAccountDAO;
import br.com.conciflex.util.RetryUtil;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCPluggyAccountDAO implements PluggyAccountDAO {
    private static JDBCPluggyAccountDAO instance;

    private JDBCPluggyAccountDAO() {

    }

    public static JDBCPluggyAccountDAO getInstance() {
        if (instance == null) {
            instance = new JDBCPluggyAccountDAO();
        }

        return instance;
    }

    @Override
    public List<PluggyAccount> listar() {
        @Language("mysql")
        String sql = "SELECT pluggy_accounts.CODIGO as id, " +
                "pluggy_accounts.ITEM_CREATED_ID    as item_created_id, " +
                "pluggy_accounts.COD_CLIENTE        as id_cliente " +
                "FROM pluggy_accounts";

        return RetryUtil.executarComRetry(() -> {
            List<PluggyAccount> accounts = new ArrayList<>();

            try (Connection connection = DataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        PluggyAccount pluggyAccount = this.carregar(resultSet);
                        accounts.add(pluggyAccount);
                    }
                }
            }

            return accounts;
        });
    }

    @NotNull
    private PluggyAccount carregar(@NotNull ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String itemCreatedId = resultSet.getString("item_created_id");
        long idCliente = resultSet.getLong("id_cliente");

        Cliente cliente = JDBCClienteDAO.getInstance().buscarPorId(idCliente);

        return new PluggyAccount(id, itemCreatedId, cliente);
    }
}
