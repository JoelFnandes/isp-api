package com.ufrn.isp.api.domain;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String localizacao; // Localização da câmera
    private String tipo; // Tipo da câmera (fixa, móvel, etc.)
    private String status; // Status da câmera (ativa, inativa)
    private Timestamp dataInstalacao; // Data de instalação da câmera

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDataInstalacao() {
        return dataInstalacao;
    }

    public void setDataInstalacao(Timestamp dataInstalacao) {
        this.dataInstalacao = dataInstalacao;
    }
}
