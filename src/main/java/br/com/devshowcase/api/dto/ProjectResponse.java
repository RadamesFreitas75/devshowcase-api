package br.com.devshowcase.api.dto;

import java.util.List;

public class ProjectResponse {

    private Long id;
    private String nome;
    private String descricao;
    private String tecnologia;
    private Long profileId;
    private List<Long> technologyIds;

    public ProjectResponse() {
    }

    public ProjectResponse(
            Long id,
            String nome,
            String descricao,
            String tecnologia,
            Long profileId,
            List<Long> technologyIds) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tecnologia = tecnologia;
        this.profileId = profileId;
        this.technologyIds = technologyIds;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public Long getProfileId() {
        return profileId;
    }

    public List<Long> getTechnologyIds() {
        return technologyIds;
    }
}