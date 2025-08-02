package cl.marco.foro_hub_api.service;

import cl.marco.foro_hub_api.model.dto.ActualizarTopicoDTO;
import cl.marco.foro_hub_api.model.dto.ListadoTopicoDTO;
import cl.marco.foro_hub_api.model.dto.RegistroTopicoDTO;
import cl.marco.foro_hub_api.model.dto.RespuestaTopicoDTO;
import cl.marco.foro_hub_api.model.entities.Topico;
import cl.marco.foro_hub_api.repository.CursoRepository;
import cl.marco.foro_hub_api.repository.TopicoRepository;
import cl.marco.foro_hub_api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        var nuevoTopico = new Topico(datos, autor, curso);

        topicoRepository.save(nuevoTopico);

        return new RespuestaTopicoDTO(
                nuevoTopico.getId(),
                nuevoTopico.getTitulo(),
                nuevoTopico.getMensaje(),
                nuevoTopico.getFechaDeCreacion(),
                nuevoTopico.getStatus(),
                nuevoTopico.getNombreAutor(),
                nuevoTopico.getNombreCurso()
        );
    }

    public Page<ListadoTopicoDTO> listar(Pageable paginacion) {
        // findAll devuelve una lista de entidades Topico
        // Usamos 'stream' y 'map' para convertir cada Topico a un DatosListadoTopico
        return topicoRepository.findAll(paginacion).map(ListadoTopicoDTO::new);
    }

    public RespuestaTopicoDTO detallar(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidationException("No se encontró un tópico con ese ID"));
        return new RespuestaTopicoDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getNombreAutor(),
                topico.getNombreCurso()
        );
    }

    @Transactional
    public RespuestaTopicoDTO actualizar(Long id, ActualizarTopicoDTO datos) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidationException("No se encontró un tópico con ese ID"));

        // Actualizamos los campos solo si no vienen nulos en el DTO
        if (datos.titulo() != null && !datos.titulo().isBlank()) {
            topico.setTitulo(datos.titulo());
        }
        if (datos.mensaje() != null && !datos.mensaje().isBlank()) {
            topico.setMensaje(datos.mensaje());
        }

        // No es necesario llamar a save(), @Transactional se encarga.
        // Devolvemos el DTO con los datos actualizados.
        return new RespuestaTopicoDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getNombreAutor(),
                topico.getNombreCurso()
        );
    }

    @Transactional
    public void eliminar(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ValidationException("No se encontró un tópico con ese ID");
        }
        topicoRepository.deleteById(id);
    }

}
