package com.dyon.projeto.literatura.ui;

public class Menu {
    private String menu = """
    1-Buscar livro por título
    2-Listar livros registrados
    3-Listar autores registrados
    4-Listar autores vivos em um determinado ano
    5-Listar livros por idioma
    0-Sair
    
    Escolha uma opção: """;
    private String bemvindo = "Bem vindos ao literAlura";

    public String getMenu() {
        return menu;
    }

    public String getBienvenida() {
        return bemvindo;
    }
}
