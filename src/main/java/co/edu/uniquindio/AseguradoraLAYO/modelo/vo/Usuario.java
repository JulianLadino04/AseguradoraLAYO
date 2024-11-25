package co.edu.uniquindio.AseguradoraLAYO.modelo.vo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usuarios")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String telefono;
    private String cedula;
    private String nombre;
    private String direccion;

}
