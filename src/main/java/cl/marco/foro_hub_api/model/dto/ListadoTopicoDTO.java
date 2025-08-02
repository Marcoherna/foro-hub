package cl.marco.foro_hub_api.model.dto;

import cl.marco.foro_hub_api.model.entities.StatusTopico;
import cl.marco.foro_hub_api.model.entities.Topico;

import java.time.LocalDateTime;

public record ListadoTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        StatusTopico status,
        String autor,
        String curso
) {
    public ListadoTopicoDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getNombreAutor(),
                topico.getNombreCurso()
        );
    }
}
