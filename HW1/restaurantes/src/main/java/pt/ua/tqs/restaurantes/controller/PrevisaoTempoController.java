package pt.ua.tqs.restaurantes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pt.ua.tqs.restaurantes.repository.PrevisaoTempoRepository;

@RestController
@RequestMapping("/api/cache")
@RequiredArgsConstructor
public class PrevisaoTempoController {

    private final PrevisaoTempoRepository previsaoRepo;

    @GetMapping("/status")
    public String statusCache() {
        long total = previsaoRepo.count();
        return "Total previsões armazenadas em cache: " + total;
    }
}
