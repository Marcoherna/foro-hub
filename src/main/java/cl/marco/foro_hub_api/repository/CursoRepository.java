package cl.marco.foro_hub_api.repository;

import cl.marco.foro_hub_api.model.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
