package pt.ua.tqs.restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.tqs.restaurantes.model.PrevisaoTempo;

import java.time.LocalDate;
import java.util.Optional;

public interface PrevisaoTempoRepository extends JpaRepository<PrevisaoTempo, Long> {
    Optional<PrevisaoTempo> findByData(LocalDate data);
}
