package cl.marco.foro_hub_api.service;

import cl.marco.foro_hub_api.model.dto.RegistroTopicoDTO;
import cl.marco.foro_hub_api.model.dto.RespuestaTopicoDTO;
import cl.marco.foro_hub_api.model.entities.Topico;
import cl.marco.foro_hub_api.repository.CursoRepository;
import cl.marco.foro_hub_api.repository.TopicoRepository;
import cl.marco.foro_hub_api.repository.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public RespuestaTopicoDTO registrar(RegistroTopicoDTO datos) {
        var autor = usuarioRepository.findById(datos.idAutor())
                .orElseThrow(() -> new ValidationException("Usuario no encontrado"));
        var curso = cursoRepository.findById(datos.idCurso())
                .orElseThrow(() -> new ValidationException("Curso no encontrado"));
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidationException("Ya existe un tópico con el mismo título y mensaje.");
        }

        var nuevoTopico = new Topico(
                null,
                datos.titulo(),
                datos.mensaje(),
                null,
                null,
                autor,
                curso
        );

        topicoRepository.save(nuevoTopico);

        return new RespuestaTopicoDTO(
                nuevoTopico.getId(),
                nuevoTopico.getNombre(),
                nuevoTopico.getMensaje(),
                nuevoTopico.getFechaDeCreacion(),
                nuevoTopico.getStatus(),
                nuevoTopico.getNombreAutor(),
                nuevoTopico.getNombreCurso()
        );
    }
}
