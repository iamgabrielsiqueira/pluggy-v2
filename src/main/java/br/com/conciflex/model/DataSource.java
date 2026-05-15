/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.model;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static final Dotenv dotenv = Dotenv.load();
    private static final BasicDataSource basicDataSource = new BasicDataSource();

    static {
        String host = dotenv.get("DB_HOST");
        String port = dotenv.get("DB_PORT");
        String database = dotenv.get("DB_DATABASE");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        String pt1 = "?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false";
        String pt2 = "&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String pt3 = "&connectionAttributes=program_name:leitura-itau";

        basicDataSource.setUrl("jdbc:mysql://" + host + ":" + port + "/" + database + pt1 + pt2 + pt3);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    private DataSource() {
    }
}
