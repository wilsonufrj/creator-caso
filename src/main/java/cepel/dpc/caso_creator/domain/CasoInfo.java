package cepel.dpc.caso_creator.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "caso-info")
public class CasoInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome_projeto")
    private String nomeProjeto;

    @Column(name = "data")
    private LocalDateTime localDateTime;

    @Column(name = "path_local",nullable = true)
    private String pathLocal;

    @Column(name = "path_remoto",nullable = true)
    private String pathRemoto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "autor")
    private String autor;

    public CasoInfo() {
    }

    public CasoInfo(Long id, String nomeProjeto, LocalDateTime localDateTime, String pathLocal, String pathRemoto, String nome, String descricao, String autor) {
        this.id = id;
        this.nomeProjeto = nomeProjeto;
        this.localDateTime = localDateTime;
        this.pathLocal = pathLocal;
        this.pathRemoto = pathRemoto;
        this.nome = nome;
        this.descricao = descricao;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getPathLocal() {
        return pathLocal;
    }

    public void setPathLocal(String pathLocal) {
        this.pathLocal = pathLocal;
    }

    public String getPathRemoto() {
        return pathRemoto;
    }

    public void setPathRemoto(String pathRemoto) {
        this.pathRemoto = pathRemoto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
