package pt.ua.tqs.restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.ua.tqs.restaurantes.model.Refeicao;

import java.time.LocalDate;
import java.util.List;

public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
    List<Refeicao> findByRestauranteIdAndDataAfter(Long restauranteId, LocalDate data);
}
