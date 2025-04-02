package pt.ua.tqs.restaurantes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.ua.tqs.restaurantes.model.PrevisaoTempo;
import pt.ua.tqs.restaurantes.repository.PrevisaoTempoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrevisaoTempoService {

    private final PrevisaoTempoRepository previsaoTempoRepository;

    public PrevisaoTempo obterPrevisao(LocalDate data) {
        Optional<PrevisaoTempo> cached = previsaoTempoRepository.findByData(data);

        if (cached.isPresent() && !estaExpirada(cached.get())) {
            return cached.get();
        }

        // MOCK da API externa
        PrevisaoTempo nova = new PrevisaoTempo();
        nova.setData(data);
        nova.setDescricaoClima("Sol e poucas nuvens ☀️");
        nova.setUltimaAtualizacao(LocalDateTime.now());

        return previsaoTempoRepository.save(nova);
    }

    private boolean estaExpirada(PrevisaoTempo previsao) {
        return previsao.getUltimaAtualizacao().isBefore(LocalDateTime.now().minusHours(6));
    }
}
