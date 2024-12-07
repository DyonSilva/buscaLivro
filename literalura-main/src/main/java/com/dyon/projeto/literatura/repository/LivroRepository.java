package com.dyon.projeto.literatura.repository;

import com.dyon.projeto.literatura.model.Idioma;
import com.dyon.projeto.literatura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    @Query("SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(:nome)")
    Optional<Livro> obterLivroPorNome(String nome);

    @Query("SELECT l FROM Livro l WHERE l.idioma=:idioma")
    List<Livro> obterLivrosPorIdioma(Idioma idioma);
}