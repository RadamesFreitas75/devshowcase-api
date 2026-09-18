package br.com.devshowcase.api.service;

import br.com.devshowcase.api.exception.ResourceNotFoundException;
import br.com.devshowcase.api.model.Feedback;
import br.com.devshowcase.api.model.Project;
import br.com.devshowcase.api.repository.FeedbackRepository;
import br.com.devshowcase.api.repository.ProjectRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final FeedbackRepository feedbackRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            FeedbackRepository feedbackRepository) {

        this.projectRepository = projectRepository;
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback adicionarFeedback(
            Long projectId,
            Integer nota,
            String comentario) {

        if (nota == null || nota < 1 || nota > 5) {
            throw new IllegalArgumentException(
                    "A nota deve estar entre 1 e 5."
            );
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Projeto não encontrado."
                        )
                );

        Feedback feedback = new Feedback();

        feedback.setNota(nota);
        feedback.setComentario(comentario);
        feedback.setProject(project);

        Feedback feedbackSalvo =
                feedbackRepository.save(feedback);

        project.getFeedbacks().add(feedbackSalvo);

        double soma = project.getFeedbacks()
                .stream()
                .mapToInt(Feedback::getNota)
                .sum();

        double media =
                soma / project.getFeedbacks().size();

        project.setNotaMedia(media);

        projectRepository.save(project);

        return feedbackSalvo;
    }

    public Project adicionarUpvote(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Projeto não encontrado."
                        )
                );

        project.setUpvotes(project.getUpvotes() + 1);

        return projectRepository.save(project);
    }

    public List<Project> listarProjetos() {
        return projectRepository.findAll();
    }

    public Page<Project> listarProjetos(
            String tecnologia,
            Pageable pageable) {

        if (tecnologia == null || tecnologia.isBlank()) {
            return projectRepository.findAll(pageable);
        }

        return projectRepository
                .findByTecnologiaContainingIgnoreCase(
                        tecnologia,
                        pageable
                );
    }

    public List<Feedback> listarFeedbacks(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Projeto não encontrado."
                        )
                );

        return project.getFeedbacks();
    }
}
