package pt.ua.tqs.restaurantes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;
    private String token;
    private boolean usada;

    @ManyToOne
    @JoinColumn(name = "refeicao_id")
    private Refeicao refeicao;
}
