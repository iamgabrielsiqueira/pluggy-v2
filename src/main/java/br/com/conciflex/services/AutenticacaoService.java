/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.services;

import br.com.conciflex.model.classes.pluggy.Autenticacao;
import br.com.conciflex.model.jdbc.JDBCAutenticacaoDAO;
import br.com.conciflex.util.Log;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import tools.jackson.databind.ObjectMapper;

public class AutenticacaoService {
    private static AutenticacaoService instance;

    private AutenticacaoService() {

    }

    public static AutenticacaoService getInstance() {
        if (instance == null) {
            instance = new AutenticacaoService();
        }

        return instance;
    }

    private static final Dotenv dotenv = Dotenv.load();
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public Autenticacao getAutenticacao() {
        Log.info("Buscando autenticação");

        String clientId = dotenv.get("CLIENT_ID");
        String clientSecret = dotenv.get("CLIENT_SECRET");

        MediaType mediaType = MediaType.parse("application/json");

        String content = "{\"clientId\":\"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\"}";

        RequestBody body = RequestBody.create(content, mediaType);

        String url = "https://api.pluggy.ai/auth";

        Log.info("POST: " + url);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("user-agent", "curl/8.2.1")
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .build();

        int responseCode;
        String responseOutput;

        try (Response response = client.newCall(request).execute()) {
            responseCode = response.code();
            responseOutput = response.body().string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (responseCode == 200) {
            Log.success("Response code: " + responseCode);

            Autenticacao autenticacao;

            try {
                autenticacao = mapper.readValue(responseOutput, Autenticacao.class);
            } catch (Exception e) {
                System.out.println(responseOutput);
                throw new RuntimeException(e);
            }

            JDBCAutenticacaoDAO.getInstance().salvar(autenticacao);

            return autenticacao;
        } else {
            Log.error("Response code: " + responseCode);
            throw new RuntimeException("Não foi possivel autenticar, response code: " + responseCode);
        }
    }
}
