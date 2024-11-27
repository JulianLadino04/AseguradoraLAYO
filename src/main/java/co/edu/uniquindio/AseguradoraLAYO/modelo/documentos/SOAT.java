package co.edu.uniquindio.AseguradoraLAYO.modelo.documentos;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.TipoVehiculo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("SOAT")
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor

public class SOAT {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private Aseguradora aseguradora;
    private String numeroPlaca;
    private String nombre;
    private String cedula;
    private String correo;
    private String telefono;
    private String direccion;
    private TipoVehiculo tipo;

}
