package co.edu.uniquindio.AseguradoraLAYO.modelo.documentos;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Rol;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.TipoVehiculo;
import co.edu.uniquindio.AseguradoraLAYO.modelo.vo.CodigoValidacion;
import co.edu.uniquindio.AseguradoraLAYO.modelo.vo.Usuario;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Autos")
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor

public class CotizacionAutos {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private Aseguradora aseguradora;
    private String numeroPlaca;
    private String nombre;
    private String cedula;
    private String email;
    private String direccion;
    private String telefono;
    private String ciudadCirculacion;
    private TipoVehiculo tipo;
    private LocalDateTime fechaNacimiento;
}
