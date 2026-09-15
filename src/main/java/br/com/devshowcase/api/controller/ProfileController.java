package br.com.devshowcase.api.controller;

import br.com.devshowcase.api.dto.ProfileRequest;
import br.com.devshowcase.api.dto.ProfileResponse;
import br.com.devshowcase.api.model.Profile;
import br.com.devshowcase.api.repository.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileRepository repository;

    public ProfileController(ProfileRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse cadastrar(@Valid @RequestBody ProfileRequest request) {

        Profile profile = new Profile();

        profile.setNome(request.getNome());
        profile.setEmail(request.getEmail());
        profile.setBio(request.getBio());
        profile.setUrlPortfolio(request.getUrlPortfolio());

        Profile salvo = repository.save(profile);

        return toResponse(salvo);
    }

    @GetMapping("/{id}")
    public ProfileResponse buscarPorId(@PathVariable Long id) {

        Profile profile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile não encontrado"));

        return toResponse(profile);
    }

    private ProfileResponse toResponse(Profile profile) {

        return new ProfileResponse(
                profile.getId(),
                profile.getNome(),
                profile.getEmail(),
                profile.getBio(),
                profile.getUrlPortfolio()
        );
    }
}