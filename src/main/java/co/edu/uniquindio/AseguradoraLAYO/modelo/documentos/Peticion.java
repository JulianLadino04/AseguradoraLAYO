package co.edu.uniquindio.AseguradoraLAYO.modelo.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Peticion")
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor

public class Peticion {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String nombre;
    private String cedula;
    private String correo;
    private String telefono;
    private String asegurar;
    private String mensaje;
}
