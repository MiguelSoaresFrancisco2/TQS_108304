package pt.ua.tqs.restaurantes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pt.ua.tqs.restaurantes.model.Refeicao;
import pt.ua.tqs.restaurantes.model.PrevisaoTempo;
import pt.ua.tqs.restaurantes.service.RefeicaoService;
import pt.ua.tqs.restaurantes.service.PrevisaoTempoService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/refeicoes")
@RequiredArgsConstructor
public class RefeicaoController {

    private final RefeicaoService refeicaoService;
    private final PrevisaoTempoService previsaoTempoService;

    @GetMapping("/restaurante/{id}")
    public List<RefeicaoComTempoDTO> getRefeicoesPorRestaurante(@PathVariable Long id) {
        List<Refeicao> refeicoes = refeicaoService.getRefeicoesFuturasPorRestaurante(id);

        return refeicoes.stream().map(refeicao -> {
            PrevisaoTempo tempo = previsaoTempoService.obterPrevisao(refeicao.getData());
            return new RefeicaoComTempoDTO(refeicao, tempo);
        }).collect(Collectors.toList());
    }

    // DTO interno para juntar refeição + tempo
    private record RefeicaoComTempoDTO(Refeicao refeicao, PrevisaoTempo tempo) {}
}
