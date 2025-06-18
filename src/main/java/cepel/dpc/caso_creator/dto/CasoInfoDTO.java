package cepel.dpc.caso_creator.dto;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CasoInfoDTO {
    private Long id;

    private String nomeProjeto;

    private LocalDateTime localDateTime;

    private String pathLocal;

    private String pathRemoto;

    private String nome;

    private String descricao;

    private String autor;

    public CasoInfoDTO() {
    }

    public CasoInfoDTO(Long id, String nomeProjeto, LocalDateTime localDateTime, String pathLocal, String pathRemoto, String nome, String descricao, String autor) {
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
