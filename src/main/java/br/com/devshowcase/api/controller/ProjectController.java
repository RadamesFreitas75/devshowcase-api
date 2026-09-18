package br.com.devshowcase.api.controller;

import br.com.devshowcase.api.dto.FeedbackRequest;
import br.com.devshowcase.api.dto.FeedbackResponse;
import br.com.devshowcase.api.dto.ProjectRequest;
import br.com.devshowcase.api.dto.ProjectResponse;
import br.com.devshowcase.api.model.Feedback;
import br.com.devshowcase.api.model.Project;
import br.com.devshowcase.api.model.Profile;
import br.com.devshowcase.api.model.Technology;
import br.com.devshowcase.api.repository.ProjectRepository;
import br.com.devshowcase.api.repository.ProfileRepository;
import br.com.devshowcase.api.repository.TechnologyRepository;
import br.com.devshowcase.api.service.ProjectService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;
    private final ProjectService projectService;

    public ProjectController(
            ProjectRepository projectRepository,
            ProfileRepository profileRepository,
            TechnologyRepository technologyRepository,
            ProjectService projectService) {

        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
        this.technologyRepository = technologyRepository;
        this.projectService = projectService;
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
    public Page<ProjectResponse> listar(
            @RequestParam(required = false) String technology,
            @PageableDefault(size = 5) Pageable pageable) {

        return projectService
                .listarProjetos(technology, pageable)
                .map(this::toResponse);
    }

    @GetMapping("/{id}/feedbacks")
    public List<FeedbackResponse> listarFeedbacks(
            @PathVariable Long id) {

        return projectService.listarFeedbacks(id)
                .stream()
                .map(feedback -> new FeedbackResponse(
                        feedback.getId(),
                        feedback.getNota(),
                        feedback.getComentario()
                ))
                .toList();
    }

    @PostMapping("/{id}/feedbacks")
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackResponse cadastrarFeedback(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackRequest request) {

        Feedback feedback = projectService.adicionarFeedback(
                id,
                request.getNota(),
                request.getComentario()
        );

        return new FeedbackResponse(
                feedback.getId(),
                feedback.getNota(),
                feedback.getComentario()
        );
    }

    @PutMapping("/{id}/upvote")
    public ProjectResponse adicionarUpvote(@PathVariable Long id) {

        Project project = projectService.adicionarUpvote(id);

        return toResponse(project);
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
                technologyIds,
                project.getNotaMedia(),
                project.getUpvotes()
        );
    }
}
