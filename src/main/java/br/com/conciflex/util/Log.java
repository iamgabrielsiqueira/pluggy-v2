/*
 * Copyright (c) 2026. Conciflex. Autor: Gabriel Siqueira de Carvalho.
 */

package br.com.conciflex.util;

import org.jetbrains.annotations.NotNull;

public final class Log {
    private Log() {

    }

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    public static void info(String mensagem) {
        System.out.println(getString(mensagem));
    }

    public static void error(String mensagem) {
        System.out.println(ANSI_RED + getString(mensagem) + ANSI_RESET);
    }

    public static void warn(String mensagem) {
        System.out.println(ANSI_YELLOW + getString(mensagem) + ANSI_RESET);
    }

    public static void success(String mensagem) {
        System.out.println(ANSI_GREEN + getString(mensagem) + ANSI_RESET);
    }

    public static void debug(String mensagem) {
        System.out.println(ANSI_CYAN + getString(mensagem) + ANSI_RESET);
    }

    public static void test(String mensagem) {
        System.out.println(ANSI_PURPLE + getString(mensagem) + ANSI_RESET);
    }

    public static void line() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private static @NotNull String getString(String mensagem) {
        return "[" + DataHora.getData() + " " + DataHora.getHora() + "] " + mensagem;
    }
}
