/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model.jdbc;

import br.com.conciflex.model.DataSource;
import br.com.conciflex.model.classes.pluggy.Autenticacao;
import br.com.conciflex.model.dao.AutenticacaoDAO;
import br.com.conciflex.util.Log;
import br.com.conciflex.util.RetryUtil;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class JDBCAutenticacaoDAO implements AutenticacaoDAO {
    private static JDBCAutenticacaoDAO instance;

    private JDBCAutenticacaoDAO() {

    }

    public static JDBCAutenticacaoDAO getInstance() {
        if (instance == null) {
            instance = new JDBCAutenticacaoDAO();
        }

        return instance;
    }

    @Override
    public void salvar(Autenticacao autenticacao) {
        Log.info("Salvando autenticação no banco de dados");

        @Language("mysql")
        String sql = "INSERT INTO pluggy_autenticacao(api_key) VALUES (?)";

        RetryUtil.executarComRetryVoid(() -> {
            try (Connection connection = DataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
                preparedStatement.setString(1, autenticacao.getApiKey());
                preparedStatement.executeUpdate();
            }
        });
    }

    @Override
    public Autenticacao buscarUltima() {
        @Language("mysql")
        String sql = "SELECT pluggy_autenticacao.api_key, " +
                "pluggy_autenticacao.data, " +
                "pluggy_autenticacao.hora " +
                "FROM pluggy_autenticacao " +
                "ORDER BY pluggy_autenticacao.id DESC " +
                "LIMIT 1";

        return RetryUtil.executarComRetry(() -> {
            Autenticacao autenticacao;

            try (Connection connection = DataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        autenticacao = this.carregar(resultSet);
                    } else {
                        autenticacao = null;
                    }
                }
            }

            return autenticacao;
        });
    }

    @NotNull
    private Autenticacao carregar(@NotNull ResultSet resultSet) throws SQLException {
        Autenticacao autenticacao = new Autenticacao();

        String apiKey = resultSet.getString("api_key");
        Date data = resultSet.getDate("data");
        Time hora = resultSet.getTime("hora");

        autenticacao.setApiKey(apiKey);
        autenticacao.setData(data);
        autenticacao.setHora(hora);

        return autenticacao;
    }
}
