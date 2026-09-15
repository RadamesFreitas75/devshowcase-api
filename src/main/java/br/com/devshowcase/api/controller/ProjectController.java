package br.com.devshowcase.api.controller;

import br.com.devshowcase.api.dto.ProjectRequest;
import br.com.devshowcase.api.dto.ProjectResponse;
import br.com.devshowcase.api.model.Project;
import br.com.devshowcase.api.model.Profile;
import br.com.devshowcase.api.model.Technology;
import br.com.devshowcase.api.repository.ProjectRepository;
import br.com.devshowcase.api.repository.ProfileRepository;
import br.com.devshowcase.api.repository.TechnologyRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectController(
            ProjectRepository projectRepository,
            ProfileRepository profileRepository,
            TechnologyRepository technologyRepository) {

        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
        this.technologyRepository = technologyRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse cadastrar(
            @Valid @RequestBody ProjectRequest request) {

        Profile profile = profileRepository.findById(request.getProfileId())
                .orElseThrow(() ->
                        new RuntimeException("Profile não encontrado"));

        Project project = new Project();

        project.setNome(request.getNome());
        project.setDescricao(request.getDescricao());
        project.setTecnologia(request.getTecnologia());
        project.setProfile(profile);

        if (request.getTechnologyIds() != null) {

            List<Technology> technologies =
                    technologyRepository.findAllById(
                            request.getTechnologyIds());

            project.setTechnologies(technologies);
        }

        Project salvo = projectRepository.save(project);

        return toResponse(salvo);
    }

    @GetMapping
    public List<ProjectResponse> listar() {

        return projectRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ProjectResponse toResponse(Project project) {

        List<Long> technologyIds = project.getTechnologies()
                .stream()
                .map(Technology::getId)
                .toList();

        Long profileId = project.getProfile() != null
                ? project.getProfile().getId()
                : null;

        return new ProjectResponse(
                project.getId(),
                project.getNome(),
                project.getDescricao(),
                project.getTecnologia(),
                profileId,
                technologyIds
        );
    }
}