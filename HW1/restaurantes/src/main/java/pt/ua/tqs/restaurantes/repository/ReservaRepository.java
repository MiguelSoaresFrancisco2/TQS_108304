package pt.ua.tqs.restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.tqs.restaurantes.model.Reserva;

import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Optional<Reserva> findByToken(String token);
}
