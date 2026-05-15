/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.controller;

import br.com.conciflex.util.Log;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;

class ConfigTest {
    @Test
    public void test() {
        Dotenv dotenv = Dotenv.load();

        String clientId = dotenv.get("CLIENT_ID");
        String clientSecret = dotenv.get("CLIENT_SECRET");

        Log.info("clientId: " + clientId);
        Log.info("clientSecret: " + clientSecret);
    }
}