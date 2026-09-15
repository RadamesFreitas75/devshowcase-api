package br.com.devshowcase.api.repository;

import br.com.devshowcase.api.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}