package com.dyon.projeto.literatura.service;

import com.dyon.projeto.literatura.model.Autor;
import com.dyon.projeto.literatura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AutorService {

    public AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public AutorService(){};

    public Optional<Autor> obterAutorPorNome(String nome){
         return autorRepository.obterAutorPorNome(nome);
    }

    public void guardarAutor(Autor autor){
        autorRepository.save(autor);
    }

    public List<Autor> obterTodosOsAutores(){
        return autorRepository.findAll();
    }

    public List<Autor> obterAutoresVivosNoAno(int ano){
        return autorRepository.obterAutoresVivosNoAno(ano);
    }

}
