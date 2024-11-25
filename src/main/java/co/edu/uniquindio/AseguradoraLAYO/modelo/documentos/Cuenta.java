package co.edu.uniquindio.AseguradoraLAYO.modelo.documentos;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Rol;
import co.edu.uniquindio.AseguradoraLAYO.modelo.vo.CodigoValidacion;
import co.edu.uniquindio.AseguradoraLAYO.modelo.vo.Usuario;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Cuentas")
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta{
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private Rol rol;
    private String email;
    private CodigoValidacion codigoValidacionRegistro;
    private Usuario usuario;
    private LocalDateTime fechaRegistro;
    private String password;
    private EstadoCuenta estadoCuenta;
    private CodigoValidacion codigoValidacionPassword;
}
