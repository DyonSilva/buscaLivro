package com.dyon.projeto.literatura.service;

import com.dyon.projeto.literatura.model.Idioma;
import com.dyon.projeto.literatura.model.Livro;
import com.dyon.projeto.literatura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LivroService {

    private LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository repository) {
        this.livroRepository = repository;
    }

    public LivroService(){};

    public List<Livro> obterTodosOsLivros() {
        return livroRepository.findAll();
    }

    public Optional<Livro> obterLivroPorNome(String nome){
        return livroRepository.obterLivroPorNome(nome);
    }

    public List<Livro>obterLivrosPorIdioma(Idioma idioma){
        return livroRepository.obterLivrosPorIdioma(idioma);
    }

    public void guardarLivro(Livro livro){
        livroRepository.save(livro);
    }
}
