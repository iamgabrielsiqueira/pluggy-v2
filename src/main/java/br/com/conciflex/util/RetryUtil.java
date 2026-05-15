/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.util;

import java.sql.SQLException;
import java.util.concurrent.Callable;

public final class RetryUtil {
    private RetryUtil() {

    }

    private static final int maxTentativas = 3;
    private static final long intervaloMs = 1000;

    public static <T> T executarComRetry(Callable<T> acao) {
        int tentativa = 0;

        while (tentativa < maxTentativas) {
            tentativa++;
            try {
                return acao.call();
            } catch (SQLException e) {
                Log.error("Tentativa " + tentativa + " - Erro de SQL: " + e.getMessage());

                if (VerificaErro.isConnectionError(e)) {
                    if (tentativa == maxTentativas) {
                        Log.error("Número máximo de tentativas atingido");
                        throw new RuntimeException("Falha na conexão após " + maxTentativas + " tentativas", e);
                    }
                    try {
                        Thread.sleep(intervaloMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Thread interrompida durante espera", ie);
                    }
                } else {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        throw new RuntimeException("Falha desconhecida na execução com retry");
    }

    @FunctionalInterface
    public interface VoidCallable {
        void call() throws Exception;
    }

    public static void executarComRetryVoid(VoidCallable action) {
        int tentativa = 0;
        while (tentativa < maxTentativas) {
            tentativa++;
            try {
                action.call();
                break;
            } catch (SQLException e) {
                Log.error("Tentativa " + tentativa + " - Erro de SQL: " + e.getMessage());

                if (VerificaErro.isConnectionError(e)) {
                    if (tentativa == maxTentativas) {
                        Log.error("Número máximo de tentativas atingido");
                        throw new RuntimeException("Falha na conexão após " + maxTentativas + " tentativas", e);
                    }
                    try {
                        Thread.sleep(intervaloMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Thread interrompida durante espera", ie);
                    }
                } else {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
