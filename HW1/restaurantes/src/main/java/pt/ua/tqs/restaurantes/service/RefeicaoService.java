package pt.ua.tqs.restaurantes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.ua.tqs.restaurantes.model.Refeicao;
import pt.ua.tqs.restaurantes.repository.RefeicaoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefeicaoService {

    private final RefeicaoRepository refeicaoRepository;

    public List<Refeicao> getRefeicoesFuturasPorRestaurante(Long restauranteId) {
        return refeicaoRepository.findByRestauranteIdAndDataAfter(restauranteId, LocalDate.now().minusDays(1));
    }
}
