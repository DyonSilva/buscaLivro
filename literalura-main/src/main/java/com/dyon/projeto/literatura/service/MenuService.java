package com.dyon.projeto.literatura.service;

import com.dyon.projeto.literatura.api.ConsultaAPI;
import com.dyon.projeto.literatura.model.Autor;
import com.dyon.projeto.literatura.model.Idioma;
import com.dyon.projeto.literatura.model.Livro;
import com.dyon.projeto.literatura.model.LivroRecord;
import com.dyon.projeto.literatura.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuService {
    private ConsultaAPI consultaAPI;
    private Scanner sc;
    private LivroService livroService;
    private AutorService autorService;
    private JsonParser jsonParser;

    @Autowired
    public MenuService(LivroService livroService, AutorService autorService, JsonParser jsonParser) {
        this.consultaAPI = new ConsultaAPI();
        this.sc = new Scanner(System.in);
        this.livroService = livroService;
        this.autorService = autorService;
        this.jsonParser = jsonParser;
    }

    public void guardarLivro() {
        List<LivroRecord> livrosObtidos = obterLivrosApi();

        if (livrosObtidos.isEmpty()) {
            System.out.println("Não foi encontrado nenhum livro");
            return;
        }

        System.out.println("Escolha um livro para guardar[0-Cancelar]");
        for (int i = 0; i < livrosObtidos.size(); i++) {
            System.out.println((i + 1) + " - " + livrosObtidos.get(i).titulo() + " - " + livrosObtidos.get(i).idiomas().get(0) + " - " + livrosObtidos.get(i).autores().get(0).nome());
        }

        int opcao = sc.nextInt();
        sc.nextLine();
        if (opcao == 0) {
            return;
        }
        if (opcao < 1 || opcao > livrosObtidos.size()) {
            System.out.println("Error: número incorreto");
            return;
        }

        LivroRecord livroRecord = livrosObtidos.get(opcao - 1);
        Optional<Livro> livroTrazidoDoRepo = livroService.obterLivroPorNome(livroRecord.titulo());
        Optional<Autor> autorTrazidoDoRepo = autorService.obterAutorPorNome(livroRecord.autores().get(0).nome());

        if (livroTrazidoDoRepo.isPresent()) {
            System.out.println("Error: não se pode registrar duas vezes o mesmo livro");
            return;
        }

        Livro livro = new Livro(livroRecord);

        if (!autorTrazidoDoRepo.isPresent()) {
            Autor autorNovo = livro.getAutor();
            autorService.guardarAutor(autorNovo);
        }

        livroService.guardarLivro(livro);
    }

    public List<LivroRecord> obterLivrosApi() {
        System.out.print("Digite o título do livro [0-Cancelar]: ");
        String titulo = sc.nextLine();
        if (titulo.equals("0")) {
            return Collections.emptyList();
        }
        List<LivroRecord> livrosObtidos;
        livrosObtidos = jsonParser.parsearLivros(consultaAPI.obterDados(titulo));
        return livrosObtidos;
    }


    public void listarLivrosRegistrados() {
        List<Livro> livros = livroService.obterTodosOsLivros();
        livros.forEach(livro -> livro.imprimirInformacao());
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorService.obterTodosOsAutores();
        autores.forEach(autor -> autor.imprimirInformacao());
    }

    public void listarAutoresVivosNoAno() {
        try {
            System.out.print("Digite o ano: ");
            int ano = sc.nextInt();
            sc.nextLine();
            List<Autor> autores = autorService.obterAutoresVivosNoAno(ano);
            autores.forEach(autor -> autor.imprimirInformacao());
        } catch (InputMismatchException e) {
            System.out.println("Error: deve digitar um número");
        }

    }

    public void listarLivrosPorIdioma() {
        Idioma.listarIdiomas();
        System.out.print("Digite o idioma [0-Cancelar]: ");
        String idiomaBuscado = sc.nextLine();
        if (idiomaBuscado.equals("0")) {
            return;
        }
        List<Livro> livros = livroService.obterLivrosPorIdioma(Idioma.stringToEnum(idiomaBuscado));
        livros.forEach(livro -> livro.imprimirInformacao());
    }

}
