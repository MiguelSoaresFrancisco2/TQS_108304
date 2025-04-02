package pt.ua.tqs.restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.tqs.restaurantes.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
