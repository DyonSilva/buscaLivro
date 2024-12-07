package com.dyon.projeto.literatura.principal;

import com.dyon.projeto.literatura.service.MenuService;
import com.dyon.projeto.literatura.ui.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    private final MenuService menuService;

    @Autowired
    public Principal(MenuService menuService) {
        this.menuService = menuService;
    }

    public void ExecutarAplicacao() {
        Menu menu = new Menu();
        Scanner teclado = new Scanner(System.in);
        System.out.println(menu.getBienvenida());
        int opcao;
        do {
            try {
                System.out.print(menu.getMenu() + " ");
                opcao = teclado.nextInt();
                teclado.nextLine();
                switch (opcao) {
                    case 1:
                        menuService.guardarLivro();
                        break;
                    case 2:
                        menuService.listarLivrosRegistrados();
                        break;
                    case 3:
                        menuService.listarAutoresRegistrados();
                        break;
                    case 4:
                        menuService.listarAutoresVivosNoAno();
                        break;
                    case 5:
                        menuService.listarLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saindo de literAlura...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: digite um número");
                opcao = -1;
                teclado.nextLine();
            }
        } while (opcao != 0);
        teclado.close();
    }
}


