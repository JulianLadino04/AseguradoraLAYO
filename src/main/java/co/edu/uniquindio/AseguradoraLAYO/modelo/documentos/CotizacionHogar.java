package co.edu.uniquindio.AseguradoraLAYO.modelo.documentos;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Hogar")
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor

public class CotizacionHogar {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private Aseguradora aseguradora;
    private String nommbre;
    private String cedula;
    private String correo;
    private String telefono;
    private String direccion;
    private LocalDateTime fechaNacimiento;
    private float valorComercial;
    private float valorElectrico;
    private float valorMuebles;
}
