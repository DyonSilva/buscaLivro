package com.dyon.projeto.literatura.model;

import jakarta.persistence.*;

import java.util.Optional;


@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer numeroDeDownlods;

    public Livro(String titulo, Idioma idioma, Integer numeroDeDownlods, String enlace) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.numeroDeDownlods = numeroDeDownlods;
    }


    public Livro(LivroRecord livro) {
        this.titulo = livro.titulo();

        Optional<AutorRecord> autor = livro.autores().stream().findFirst();
        autor.ifPresent(autorRecord -> this.autor = new Autor(autorRecord));

        Optional<String> idioma = livro.idiomas().stream().findFirst();
        idioma.ifPresent(s -> this.idioma = Idioma.stringToEnum(s));

        this.numeroDeDownlods = livro.numeroDeDownloads();
    }

    public Livro() {
    }

    ;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDownloads() {
        return numeroDeDownlods;
    }

    public void setNumeroDeDownloads(Integer numeroDeDownlods) {
        this.numeroDeDownlods = numeroDeDownlods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void imprimirInformacao() {
        System.out.println("*****Livro*****");
        System.out.println("Titulo:" + titulo);
        System.out.println("Autor: " + autor.getNome());
        System.out.println("Idioma: " + idioma.getIdiomaCompleto());
        System.out.println("Numero de Downloads: " + numeroDeDownlods);
        System.out.println("");
    }

    @Override
    public String toString() {
        return titulo;
    }
}
