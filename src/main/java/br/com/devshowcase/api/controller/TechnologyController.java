package br.com.devshowcase.api.controller;

import br.com.devshowcase.api.dto.TechnologyRequest;
import br.com.devshowcase.api.dto.TechnologyResponse;
import br.com.devshowcase.api.model.Technology;
import br.com.devshowcase.api.repository.TechnologyRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    private final TechnologyRepository repository;

    public TechnologyController(TechnologyRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TechnologyResponse cadastrar(
            @Valid @RequestBody TechnologyRequest request) {

        Technology technology = new Technology();

        technology.setNome(request.getNome());

        Technology salva = repository.save(technology);

        return toResponse(salva);
    }

    @GetMapping
    public List<TechnologyResponse> listar() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private TechnologyResponse toResponse(Technology technology) {

        return new TechnologyResponse(
                technology.getId(),
                technology.getNome()
        );
    }
}