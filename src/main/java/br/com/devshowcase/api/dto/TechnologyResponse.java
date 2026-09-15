package br.com.devshowcase.api.dto;

public class TechnologyResponse {

    private Long id;
    private String nome;

    public TechnologyResponse() {
    }

    public TechnologyResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}