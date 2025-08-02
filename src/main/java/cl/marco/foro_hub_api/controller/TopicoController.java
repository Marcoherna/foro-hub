package cl.marco.foro_hub_api.controller;

import cl.marco.foro_hub_api.model.dto.ActualizarTopicoDTO;
import cl.marco.foro_hub_api.model.dto.ListadoTopicoDTO;
import cl.marco.foro_hub_api.model.dto.RegistroTopicoDTO;
import cl.marco.foro_hub_api.model.dto.RespuestaTopicoDTO;
import cl.marco.foro_hub_api.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo tópico en la base de datos")
    public ResponseEntity<?> registrarTopico(
            @RequestBody @Valid RegistroTopicoDTO datos,
            UriComponentsBuilder  uriBuilder) {
        var response = topicoService.registrar(datos);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);

    }

    @GetMapping
    @Operation(summary = "Obtiene un listado paginado de los tópicos")
    public ResponseEntity<Page<ListadoTopicoDTO>> listarTopicos(@PageableDefault(size = 10, sort = "fechaDeCreacion") Pageable paginacion) {
        var paginaDeTopicos = topicoService.listar(paginacion);
        return ResponseEntity.ok(paginaDeTopicos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene los detalles de un tópico específico por su ID")
    public ResponseEntity<RespuestaTopicoDTO> detallarTopico(@PathVariable Long id) {
        var topico = topicoService.detallar(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualiza el título y/o mensaje de un tópico existente")
    public ResponseEntity<RespuestaTopicoDTO> actualizarTopico(@PathVariable Long id, @RequestBody ActualizarTopicoDTO datos) {
        var topicoActualizado = topicoService.actualizar(id, datos);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elimina un tópico de la base de datos")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
