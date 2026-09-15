package br.com.devshowcase.api.repository;

import br.com.devshowcase.api.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}