package co.edu.uniquindio.AseguradoraLAYO.dto.ProteccionCreditoDTOs;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import java.time.LocalDateTime;

public record ObtenerProteccionCreditoDTO(
        String id,
        Aseguradora aseguradora,
        String nombre,
        String cedula,
        String correo,
        String telefono,
        String direccion,
        LocalDateTime fechaNacimiento,
        float valorDeuda
) {}

