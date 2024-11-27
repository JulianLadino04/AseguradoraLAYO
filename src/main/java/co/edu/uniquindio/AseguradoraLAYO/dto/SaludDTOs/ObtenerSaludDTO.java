package co.edu.uniquindio.AseguradoraLAYO.dto.SaludDTOs;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import java.time.LocalDateTime;

public record ObtenerSaludDTO(
        String id,
        Aseguradora aseguradora,
        String nombre,
        String cedula,
        String correo,
        String telefono,
        String direccion,
        LocalDateTime fechaNacimiento
) {}
