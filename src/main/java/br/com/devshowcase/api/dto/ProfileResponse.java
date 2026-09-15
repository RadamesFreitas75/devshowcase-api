package br.com.devshowcase.api.dto;

public class ProfileResponse {

    private Long id;
    private String nome;
    private String email;
    private String bio;
    private String urlPortfolio;

    public ProfileResponse() {
    }

    public ProfileResponse(Long id, String nome, String email, String bio, String urlPortfolio) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.bio = bio;
        this.urlPortfolio = urlPortfolio;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public String getUrlPortfolio() {
        return urlPortfolio;
    }
}