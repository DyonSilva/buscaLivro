package com.dyon.projeto.literatura.repository;

import com.dyon.projeto.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE LOWER(a.nome) LIKE LOWER(:nome)")
    Optional<Autor> obterAutorPorNome(String nome);

    @Query("SELECT a FROM Autor a WHERE :ano>=a.nascimento AND :ano<a.falecimento")
    List<Autor> obterAutoresVivosNoAno(int ano);
}
