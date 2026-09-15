package br.com.devshowcase.api.dto;

import jakarta.validation.constraints.NotBlank;

public class TechnologyRequest {

    @NotBlank(message = "Nome da tecnologia é obrigatório")
    private String nome;

    public TechnologyRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}