package br.com.devshowcase.api.dto;

public class FeedbackResponse {

    private Long id;
    private Integer nota;
    private String comentario;

    public FeedbackResponse() {
    }

    public FeedbackResponse(Long id, Integer nota, String comentario) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }
}
