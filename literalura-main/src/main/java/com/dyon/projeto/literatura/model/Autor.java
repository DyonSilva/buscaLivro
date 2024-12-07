package com.dyon.projeto.literatura.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String nome;
    private Integer nascimento;
    private Integer falecimento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro>livros;

    public Autor(AutorRecord autor){
        this.nome=autor.nome();
        this.nascimento= autor.nascimento();
        this.falecimento= autor.falecimento();
    }

    public Autor(){};

    public String getNome() {
        return nome;
    }

    public void setNome(String nombre) {
        this.nome = nombre;
    }

    public Integer getNascimento() {
        return nascimento;
    }

    public void setNascimento(Integer nascimento) {
        this.nascimento = nascimento;
    }

    public Integer getFalecimento() {
        return falecimento;
    }

    public void setFechaFallecimiento(Integer falecimento) {
        this.falecimento = falecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nome + '\'' +
                ", nascimento=" + nascimento +
                ", falecimento=" + falecimento +
                ", livros=" + livros +
                '}';
    }

    public void imprimirInformacao() {
        System.out.println("*****Autor*****");
        System.out.println("Nome:" + nome);
        System.out.println("Nascimiento: "+nascimento);
        System.out.println("Falecimento: "+falecimento);
        System.out.println("Livros: "+livros);
        System.out.println("");
    }
}
