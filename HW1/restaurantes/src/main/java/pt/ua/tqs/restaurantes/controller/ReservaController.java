package pt.ua.tqs.restaurantes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ua.tqs.restaurantes.model.Reserva;
import pt.ua.tqs.restaurantes.service.ReservaService;

import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestParam Long refeicaoId, @RequestParam String nomeCliente) {
        try {
            Reserva reserva = reservaService.criarReserva(refeicaoId, nomeCliente);
            return ResponseEntity.ok(reserva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{token}")
    public ResponseEntity<Reserva> consultarReserva(@PathVariable String token) {
        Optional<Reserva> reserva = reservaService.consultarReserva(token);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{token}/checkin")
    public ResponseEntity<Void> marcarComoUsada(@PathVariable String token) {
        boolean sucesso = reservaService.marcarReservaComoUsada(token);
        return sucesso ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
