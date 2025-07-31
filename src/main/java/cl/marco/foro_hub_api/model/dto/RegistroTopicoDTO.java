package cl.marco.foro_hub_api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroTopicoDTO(
        @NotBlank(message = "El título no puede estar en blanco")
        String titulo,

        @NotBlank(message = "El mensaje no puede estar en blanco")
        String mensaje,

        @NotNull(message = "El ID del autor es obligatorio")
        Long idAutor,

        @NotNull(message = "El ID del curso es obligatorio")
        Long idCurso
) {
}
