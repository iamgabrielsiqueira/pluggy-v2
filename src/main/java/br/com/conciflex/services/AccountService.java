/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.services;

import br.com.conciflex.model.classes.pluggy.Account;
import br.com.conciflex.model.classes.pluggy.Autenticacao;
import br.com.conciflex.model.classes.pluggy.GetAccountsResponse;
import br.com.conciflex.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

public class AccountService {
    private static AccountService instance;

    private AccountService() {

    }

    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }

        return instance;
    }

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Account> getAccounts(@NotNull Autenticacao autenticacao, String itemCreatedId) {
        String url = "https://api.pluggy.ai/accounts?itemId=" + itemCreatedId;

        Log.info("GET: " + url);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("user-agent", "curl/8.2.1")
                .addHeader("accept", "application/json")
                .addHeader("X-API-KEY", autenticacao.getApiKey())
                .build();

        int responseCode = 0;
        String responseOutput = "";

        try (Response response = client.newCall(request).execute()) {
            responseCode = response.code();

            responseOutput = response.body().string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (responseCode == 200) {
            Log.success("Response code: " + responseCode);

            GetAccountsResponse response;

            try {
                response = mapper.readValue(responseOutput, GetAccountsResponse.class);
            } catch (Exception e) {
                System.out.println(responseOutput);
                throw new RuntimeException(e);
            }

            return response.getAccounts();
        } else {
            Log.error("Response code: " + responseCode);
            throw new RuntimeException("Não foi possivel buscar contas, response code: " + responseCode);
        }
    }
}
