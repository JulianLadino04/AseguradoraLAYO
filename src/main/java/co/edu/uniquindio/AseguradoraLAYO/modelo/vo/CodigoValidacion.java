package co.edu.uniquindio.AseguradoraLAYO.modelo.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class CodigoValidacion {

    private String codigoValidacion;
    private LocalDateTime fechaCreacion;
}