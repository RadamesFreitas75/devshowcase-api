package br.com.devshowcase.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ProjectRequest {

    @NotBlank(message = "Nome do projeto é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "Tecnologia é obrigatória")
    private String tecnologia;

    @NotNull(message = "O profile é obrigatório")
    private Long profileId;

    private List<Long> technologyIds;

    public ProjectRequest() {
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

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public List<Long> getTechnologyIds() {
        return technologyIds;
    }

    public void setTechnologyIds(List<Long> technologyIds) {
        this.technologyIds = technologyIds;
    }
}