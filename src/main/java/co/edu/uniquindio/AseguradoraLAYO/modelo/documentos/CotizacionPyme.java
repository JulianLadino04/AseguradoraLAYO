package co.edu.uniquindio.AseguradoraLAYO.modelo.documentos;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.TipoInmueble;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Pyme")
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor

public class CotizacionPyme {
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
    private float valorMercancia;
    private float valorMaquinaria;
    private float valorComercial;
    private float valorElectrico;
    private float valorMuebles;
    private TipoInmueble tipo;
}
