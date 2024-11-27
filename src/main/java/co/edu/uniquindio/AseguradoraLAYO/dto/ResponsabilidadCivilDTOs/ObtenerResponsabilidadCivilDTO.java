package co.edu.uniquindio.AseguradoraLAYO.dto.ResponsabilidadCivilDTOs;

import co.edu.uniquindio.AseguradoraLAYO.modelo.enums.Aseguradora;
import java.time.LocalDateTime;

public record ObtenerResponsabilidadCivilDTO(
        String id,
        Aseguradora aseguradora,
        String nombre,
        String cedula,
        String correo,
        String telefono,
        String direccion,
        LocalDateTime fechaNacimiento,
        String ocupacion,
        String ciudad
) {}
