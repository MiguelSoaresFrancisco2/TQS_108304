package pt.ua.tqs.restaurantes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.ua.tqs.restaurantes.model.Reserva;
import pt.ua.tqs.restaurantes.model.Refeicao;
import pt.ua.tqs.restaurantes.repository.ReservaRepository;
import pt.ua.tqs.restaurantes.repository.RefeicaoRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final RefeicaoRepository refeicaoRepository;

    public Reserva criarReserva(Long refeicaoId, String nomeCliente) {
        Optional<Refeicao> refeicao = refeicaoRepository.findById(refeicaoId);
        if (refeicao.isEmpty()) {
            throw new IllegalArgumentException("Refeição não encontrada");
        }

        Reserva reserva = new Reserva();
        reserva.setRefeicao(refeicao.get());
        reserva.setNomeCliente(nomeCliente);
        reserva.setToken(UUID.randomUUID().toString());
        reserva.setUsada(false);

        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> consultarReserva(String token) {
        return reservaRepository.findByToken(token);
    }

    public boolean marcarReservaComoUsada(String token) {
        Optional<Reserva> reserva = reservaRepository.findByToken(token);
        if (reserva.isPresent() && !reserva.get().isUsada()) {
            reserva.get().setUsada(true);
            reservaRepository.save(reserva.get());
            return true;
        }
        return false;
    }
}
