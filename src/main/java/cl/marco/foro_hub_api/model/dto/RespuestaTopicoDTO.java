package cl.marco.foro_hub_api.model.dto;

import cl.marco.foro_hub_api.model.entities.StatusTopico;

import java.time.LocalDateTime;

public record RespuestaTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        StatusTopico status,
        String autor,
        String curso
) {
}
